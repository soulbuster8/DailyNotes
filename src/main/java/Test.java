import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.CompletableFuture.allOf;

public class Test {

    public static void main(String[] args) throws InterruptedException,ExecutionException {
        LogClient logClient = new LogClientImpl(10);

        logClient.start("1", 1);
        logClient.start("2", 2);
        logClient.start("3", 3);

        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        tasks.add(CompletableFuture.runAsync(() -> logClient.end("3")));
        tasks.add(CompletableFuture.runAsync(() -> logClient.end("2")));

        tasks.add(CompletableFuture.runAsync(logClient::poll));
        tasks.add(CompletableFuture.runAsync(logClient::poll));

        Thread.sleep(10000);

        tasks.add(CompletableFuture.runAsync(logClient::poll));

//        allOf(tasks.toArray(CompletableFuture[]::new)).get();
    }
}

interface LogClient {
    /**
     * When a process starts, it calls 'start' with processId.
     */
    void start(String processId, long timestamp);

    /**
     * When the same process ends, it calls 'end' with processId.
     */
    void end(String processId);

    /**
     * Polls the first log entry of a completed process sorted by the start time of processes in the below format
     * {processId} started at {startTime} and ended at {endTime}
     * <p>
     * process id = 1 --> 12, 15
     * process id = 2 --> 8, 12
     * process id = 3 --> 7, 19
     * <p>
     * {3} started at {7} and ended at {19}
     * {2} started at {8} and ended at {12}
     * {1} started at {12} and ended at {15}
     */
    String poll();
}

class LogClientImpl implements LogClient {

    private Map<String, Process> mp;
    private Queue<Process> queue;
    private Lock lock;
    private List<CompletableFuture<String>> futures;
    private ExecutorService[] taskScheduler;

    public LogClientImpl(int threads) {
        this.mp = new ConcurrentHashMap<>();
        this.queue = new PriorityBlockingQueue<>(16, (o1, o2) -> {
            if(o1.getStartTime()<o2.getStartTime()) return -1;
            else if(o1.getStartTime() == o2.getStartTime()) return 0;
            return 1;
        });
        this.lock = new ReentrantLock();
        this.futures = new ArrayList<>();
        this.taskScheduler = new ExecutorService[threads];
        for(int i=0;i<10;i++) {
            taskScheduler[i] = Executors.newSingleThreadExecutor();
        }
    }

    @Override
    public void start(String processId, long timestamp) {
        taskScheduler[processId.hashCode() % taskScheduler.length].execute(() -> {
            final Process process = new Process(processId, timestamp);
            mp.put(processId, process);
            queue.add(process);
        });
    }

    @Override
    public void end(String processId) {
        taskScheduler[processId.hashCode() % taskScheduler.length].execute(() -> {
            lock.lock();//1
            try {
                final long now = System.currentTimeMillis();//1
                mp.get(processId).setEndTime(now);//1
                if (!futures.isEmpty() && queue.peek().getId().equals(processId)) {
                    pollNow();
                    CompletableFuture<String> result = futures.remove(0);
                    result.complete(null);
                }
            } finally {
                lock.unlock();
            }
        });
    }

    @Override
    public String poll() {

        lock.lock();
        try {
            CompletableFuture<String> future = new CompletableFuture<>();
            if(!queue.isEmpty() && queue.peek().getEndTime() != -1) {
                Process peekElement = queue.poll();
                mp.remove(peekElement.getId());
                System.out.println("Top element's start time is " + peekElement.getStartTime() + " and end time is " + peekElement.getEndTime());
            }
            else {
                futures.add(future);
            }
            try {
                return future.get(3, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        } finally {
            lock.unlock();
        }
    }

    private String pollNow() {
        final Process process = queue.poll();
        String logStatement = process.getId() + " started at " + process.getStartTime() + " and ended at " + process.getEndTime();
        System.out.println(logStatement);
        mp.remove(process.getId());
        return logStatement;
    }

}

class Process {
    private final String id;
    private final long startTime;
    private long endTime;

    public Process(final String id, final long startTime) {
        this.id = id;
        this.startTime = startTime;
        endTime = -1;
    }

    public String getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}

