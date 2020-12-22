Note :- Callable is same as Runnable but it can return any type of Object if we want to get a result or status from work (callable).

Callable is used whenever we want some output from the different thread. We can use submit method of ExecutorService and it will return Future. Future.get() will return the
actual value and that's why it is blocking call, It will be blocked until future does not have the actual value.

Future is placeholder of a value which will arrive sometime in future. This time depends upon how much time call method will take.(Below example) 
```

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return 3;
    }
}

public static void main(String args[]) throws Exception {

        //create the pool
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //submit the tasks for execution.
        Future<Integer> future = executorService.submit(new Task());

        //perform some unrelated operation.
        // 1 sec

        Integer result = future.get();  //blocking


    }
```

Note :- Each java thread corresponds to a JVM thread which will reside in OS. JVM threads are called Kernel thread/Native thread. Native thread are heavy, that's why java introduced
    new feature called Java Fibers.
    
1. CompletableFuture.allOf() method is the one which can combine the other completable future.
2. CountDownLatch is used to make sure that a task waits for other threads before it starts. When we create an object of CountDownLatch, we specify the number of threads it 
    should wait for, all such thread are required to do count down by calling CountDownLatch.countDown() once they are completed or ready to the job. As soon as count reaches 
    zero, the waiting task starts running.
3. The Condition class provides the ability for a thread to wait for some condition to occur while executing the critical section. This can occur when a thread acquires the
    access to the critical section but doesn't have the necessary condition to perform its operation.
```
ReentrantLock lock = new ReentrantLock();
Condition stackEmptyCondition = lock.newCondition();
Condition stackFullCondition = lock.newCondition();

stackFullCondition.await();
stackEmptyCondition.await();
```
4. ThreadPool shutDown kill the thread :- 
    1. shutDown() :- No New tasks accepted. Previously submitted tasks are executed.
    2. shutDownNow() :- No new tasks accepted. Previously submitted tasks waiting in the queue are returned. Tasks being run by the thread(s) are attempted to stop(It will not 
                        give any gurantee whether it will stop or not)
                        
                        
Que:- A task is running in a seperate thread. Stop the task if it exceeds 10 mins.

Ans:- We don't have any api on thread which can stop the thread(stop() is deprecated) so that means we don't hav any straight forward way to handle it. We can use ThreadPool to
      do that(ExecutorService). ThreadPool have shutDown() and shutDownNow() methods but they have their own limitations. 
 
```      
ExecutorService threadPool = Executors.newFixedThreadPool(2);
Task task = new Task();
Future<?> future = threadPool.submit(task);

try {
    future.get(10, TimeUnit.MINUTES);
} catch(TimeoutException e) {
    future.cancel(true);
    task.stop();
}

threadPool.shutdownNow();
```


Note :- 
Interrupting a Thread :- 
1. If any thread is in sleeping or waiting state (i.e. sleep() or wait() is invoked), calling the interrupt() method on the thread, breaks out the sleeping or waiting state 
throwing InterruptedException. If the thread is not in the sleeping or waiting state, calling the interrupt() method performs normal behaviour and doesn't interrupt the
thread but sets the interrupt flag to true. 
2. That means if thread is not in sleeping or waiting state, calling thread can not stop the called thread but it can ask politely to stop it by setting the interrupt flag. It
    is programmers reponsibility to handle this scenario in the code. It is an indication, it will not forcefully stop the thread.
  
```
Ex. 1:- 
class TestInterruptingThread2 extends Thread{  
public void run(){  
try{  
Thread.sleep(1000);  
System.out.println("task");  
}catch(InterruptedException e){  
System.out.println("Exception handled "+e);  
}  
System.out.println("thread is running...");  
}  
  
public static void main(String args[]){  
TestInterruptingThread2 t1=new TestInterruptingThread2();  
t1.start();  
  
t1.interrupt();  
  
}  
}

O/P :- In this scenario, interrupted exception will be called and then it will come out of that loop. 


Ex. 2:-

class TestInterruptingThread3 extends Thread{  
  
public void run(){  
for(int i=1;i<=5;i++)  
System.out.println(i); 

 if(Thread.currentThread().isInterrupted()) {
    System.out.println("Stopping the task.. Interrupt by some other thread");
    return;
    }
}  
  
public static void main(String args[]){  
TestInterruptingThread3 t1=new TestInterruptingThread3();  
t1.start();  
  
t1.interrupt();  
  
}  
}  

O/P :- In this scenraio, interrupt flag will set to true and then it will check there and then it will print the statement and then it will come out of the new thread. 
```
3. Runnable does not allow checked exceptions to be thrown. Callable allows checked exception.
4. There are two methods isInterrupted() vs interrupted(). Both have different purpose.
    isInterrupted() :- check the interrupt flag.
    interrupted() :- check the flag and reset it.(Recommended when throwing InterruptedException.)
    


Note :- Thread class provides the join() method which allows one thread to wait until another thread completes its execution. If t is a Thread object whose thread is
        currently executing, then t.join() will make sure that t is terminated before the next instruction is executed by the program.
        
        join(): It will put the current thread on wait until the thread on which it is called is dead. If thread is interrupted then it will throw InterruptedException.