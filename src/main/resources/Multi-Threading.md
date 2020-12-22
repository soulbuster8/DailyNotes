http://tutorials.jenkov.com/java-concurrency/index.html#:~:text=Java%20Concurrency%20is%20a%20term,parallelism%20on%20the%20Java%20platform.&text=This%20Java%20concurrency%20tutorial%20covers,related%20to%20multithreading%20in%20Java.

Multithreading :- It means that we have multiple threads of execution inside the same application. A CPU will share its execution time among multiple threads, switching b/w
                    executing each of the threads for a given amount of time. 
                    
Multitasking :- It meant that computers could execute multiple programs (AKA tasks or processes) at the same time.

Note :- 
1. The first Java concurrency model assumed that multiple threads executing within the same application would also share objects. This type of concurrency model is
   typically referred to as a "shared state concurrency model".
2. The shared state concurrency model causes a lot of concurrency problems which can be hard to solve elegantly. An alternative concurrency model referred to as
    "shared nothing" or "separate state".
    
    
Good design for a server application which is listening on some port for incoming request :- 
    1. Thread which is listening on a given port will pass the request to a worker thread and it will return back to listening on that port. This way the server thread will be
     back at listening sooner. Thus more clients can send requests to the server. The server has become more responsive.


Multithreading Costs :- When a CPU switches from executing one thread to executing another, the CPU needs to save the local data, program pointer etc. of the current thread,
                        and load the local data, program pointer etc. of the next thread to execute. This switch is called a "context switch". The CPU switches from executing
                        in the context of one thread to executing in the context of another.
                        
                        
Concurrency Models :- A concurrency model specifies how threads in the the system collaborate to complete the tasks they are are given.  Different concurrency models split the 
                        tasks in different ways, and the threads may communicate and collaborate in different ways.
                        
    Shared State vs Separate State :- 
                Shared state means that the different threads in the system will share some state among them. By state is meant some data, typically one or more objects or 
                similar. When threads share state, problems like race conditions and deadlock etc may occur.       
                
                
                
Conurrency vs Parallelism :- 
    Concurrency means that an application is making progress on more than one task - at the same time or at least seemingly at the same time (concurrently). If the computer
    only has one CPU the application may not make progress on more than one task at exactly the same time, but more than one task is in progress at a time inside the 
    application.
    
    Parallel execution is when a computer has more than one CPU or CPU core, and makes progress on more than one task simultaneously. 
    
    
Creating and Starting Threads :- 
1. The first way to specify what code a thread is to run, is to create a subclass of Thread and override the run() method.
```
   public class MyThread extends Thread {
   
       public void run(){
          System.out.println("MyThread running");
       }
     }

MyThread myThread = new MyThread();
  myTread.start();

```                                                             
The start() call will return as soon as the thread is started. It will not wait until the run() method is done. The run() method will execute as if executed by a different CPU.

2. 
```
//Runnable interace
Runnable myRunnable =
    new Runnable(){
        public void run(){
            System.out.println("Runnable running");
        }
    }

//functional interface
Runnable runnable =
        () -> { System.out.println("Lambda Runnable running"); };

Thread thread = new Thread(runnable);
thread.start();
```

Note :- When having the Runnable's executed by a thread pool it is easy to queue up the Runnable instances until a thread from the pool is idle. This is a little harder to do 
with Thread subclasses.

```
Thread newThread = new Thread(MyRunnable());
  newThread.run();  //should be start();
```

At first you may not notice anything because the Runnable's run() method is executed like you expected. However, it is NOT executed by the new thread you just created.
Instead the run() method is executed by the thread that created the thread. In other words, the thread that executed the above two lines of code. To have the run() method
of the MyRunnable instance called by the new created thread, newThread, you MUST call the newThread.start() method.

http://tutorials.jenkov.com/java-concurrency/thread-pools.html

Stop a Thread :-
The Java Thread class contains a stop() method, but it is deprecated. The original stop() method would not provide any guarantees about in what state the thread was stopped.
That means, that all Java objects the thread had access to during execution would be left in an unknown state. If other threads in your application also has access to the same 
objects, your application could fail unexpectedly and unpredictably. Instead of calling the stop() method you will have to implement your thread code so it can be stopped.


Java synchronized method :- 
If we declare any method as synchronized, it is known as synchronized method. Synchronized method is used to lock an object for any shared resource. When a thread invokes a
synchronized method, it automatically acquires the lock for that object and releases it when the thread completes its task.

Putting synchronized on an instance method means that the thread has to acquire the lock (the "intrinsic lock") on the object instance that the method is called on before
the thread can start executing any code in that method. If you have two different instance methods marked synchronized and different threads are calling those methods
concurrently on the same object, those threads will be contending for the same lock. Once one thread gets the lock all other threads are shut out of all synchronized instance 
methods on that object.


Race Condition and Critical Sections :- 
A race condition is a concurrency problem that may occur inside a critical section. A critical section is a section of code that is executed by multiple threads and where the
sequence of execution for the threads makes a difference in the result of the concurrent execution of the critical section.

When the result of multiple threads executing a critical section may differ depending on the sequence in which the threads execute, the critical section is said to contain a
race condition.

1. Race conditions can occur when two or more threads read and write the same variable according to one of these two patterns:
    1. Read-modify-write 2. Check-then-act
    
    The read-modify-write pattern means, that two or more threads first read a given variable, then modify its value and write it back to the variable. For this to cause a
    problem, the new value must depend one way or another on the previous value. The problem that can occur is, if two threads read the value (into CPU registers) then 
    modify the value (in the CPU registers) and then write the values back. This situation is explained in more detail later.
    
    The check-then-act pattern means, that two or more threads check a given condition, for instance if a Map contains a given value, and then go on to act based on that
    information, e.g. taking the value from the Map. The problem may occur if two threads check the Map for a given value at the same time - see that the value is present
    - and then both threads try to take (remove) that value. However, only one of the threads can actually take the value. The other thread will get a null value back.
    This could also happen if a Queue was used instead of a Map.     
    
2. To prevent race conditions from occurring you must make sure that the critical section is executed as an atomic instruction. That means that once a single thread is
   executing it, no other threads can execute it until the first thread has left the critical section. Race conditions can be avoided by proper thread synchronization in
   critical sections. Thread synchronization can be achieved using a synchronized block of Java code.
   

Thread Safety and Shared Resources :-

1. Code that is safe to call by multiple threads simultaneously is called thread safe. Local variables are stored in each thread's own stack. That means that local variables
    are never shared between threads. 
2. Local references to objects are a bit different. The reference itself is not shared. The object referenced however, is not stored in each threads's local stack. All objects
    are stored in the shared heap.
3. Object member variables (fields) are stored on the heap along with the object. Therefore, if two threads call a method on the same object instance and this method updates
    object member variables, the method is not thread safe.                                                                                                                                  
 

Java Synchronized Blocks :-
    http://tutorials.jenkov.com/java-concurrency/synchronized.html

1. A synchronized block in Java is synchronized on some object.
2. Only one thread per instance can execute inside a synchronized instance method. If more than one instance exist, then one thread at a time can execute inside a
    synchronized instance method per instance. One thread per instance. This is true across all synchronized instance methods for the same object (instance). only one thread 
    can execute inside any of the synchronized methods.
3. Synchronized static methods are synchronized on the class object of the class the synchronized static method belongs to. Since only one class object exists in the Java
    VM per class, only one thread can execute inside a static synchronized method in the same class.
4. 
```
      public void add(int value){
    
        synchronized(this){
           this.count += value;   
        }
      }
```    
Notice how the Java synchronized block construct takes an object in parentheses. In the example "this" is used, which is the instance the add method is called on. The
object taken in the parentheses by the synchronized construct is called a monitor object. The code is said to be synchronized on the monitor object. A synchronized 
instance method uses the object it belongs to as monitor object. Only one thread can execute inside a Java code block synchronized on the same monitor object.

5. Without the use of the synchronized keyword (or the Java volatile keyword) there is no guarantee that when one thread changes the value of a variable shared with other
 threads (e.g. via an object all threads have access to), that the other threads can see the changed value. There are no guarantees about when a variable kept in a CPU 
 register by one thread is "committed" to main memory, and there is no guarantee about when other threads "refresh" a variable kept in a CPU register from main memory.
   
 The synchronized keyword changes that. When a thread enters a synchronized block it will refresh the values of all variables visible to the thread. When a thread exits a
 synchronized block all changes to variables visible to the thread will be committed to main memory. This is similar to how the volatile keyword works.

6. The Java compiler and Java Virtual Machine are allowed to reorder instructions in your code to make them execute faster, typically by enabling the reordered instructions
   to be executed in parallel by the CPU.

   Instruction reordering could potentially cause problems in code that is executed by multiple threads at the same time. For instance, if a write to a variable happening
   inside of a synchronized block was reordered to happen outside of the synchronized block.
   
   To fix this problem the Java synchronized keyword places some restrictions on reordering of instructions before, inside and after synchronized blocks. This is similar to 
   the restrictions placed by the volatile keyword.
   
7. It is recommended that you do not synchronize on String objects, or any primitive type wrapper objects, as the compiler might optimize those, so that you are using the 
   same instances in different places in your code where you thought you were using different instance. 
   
   ```
    synchronized("Hey") {
       //do something in here.
    }
    ``` 
   If you have more than one synchronized block that is synchronized on the literal String value "Hey", then the compiler might actually use the same String object behind the 
   scenes. The result being, that both of these two synchronized blocks are then synchronized on the same object. That might not be the behaviour you were looking for.
   
8. Synchronized blocks in Java have several limitations. For instance, a synchronized block in Java only allows a single thread to enter at a time. However, what if two 
   threads just wanted to read a shared value, and not update it? That might be safe to allow. As alternative to a synchronized block you could guard the code with a
    Read / Write Lock which as more advanced locking semantics than a synchronized block. Java actually comes with a built in ReadWriteLock class you can use.
    
9. The performance overhead of entering and exiting a synchronized block is mostly something to worry about if you enter and exit a synchronized block lots of times 
    within a tight loop or so.

10. Once a thread has entered a synchronized block the thread is said to "hold the lock" on the monitoring object the synchronized block is synchronized on. If the thread
    calls another method which calls back to the first method with the synchronized block inside, the thread holding the lock can reenter the synchronized block. It is not
    blocked just because a thread (itself) is holding the lock. Only if a differen thread is holding the lock.
    
11. Keep in mind that a synchronized block only blocks threads within the same Java VM from entering that code block. If you have the same Java application running on
 multiple Java VMs - in a cluster - then it is possible for one thread within each Java VM to enter that synchronized block at the same time.
    
    If you need synchronization across all Java VMs in a cluster you will need to use other synchronization mechanisms than just a synchronized block.


Volatile Keyword :-
1. In a multithreaded application where the threads operate on non-volatile variables, each thread may copy variables from main memory into a CPU cache while working on
   them, for performance reasons. If your computer contains more than one CPU, each thread may run on a different CPU. That means, that each thread may copy the variables
   into the CPU cache of different CPUs. With non-volatile variables there are no guarantees about when the Java Virtual Machine (JVM) reads data from main memory into CPU
   caches, or writes data from CPU caches to main memory. This can cause several problems.
2. The Java volatile keyword is intended to address variable visibility problems. By declaring the counter variable volatile all writes to the counter variable will be 
    written back to main memory immediately. Also, all reads of the counter variable will be read directly from main memory.   
3. Actually, the visibility guarantee of Java volatile goes beyond the volatile variable itself. The visibility guarantee is as follows:
   
   If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then all variables visible to Thread A before writing the volatile 
   variable, will also be visible to Thread B after it has read the volatile variable.
   If Thread A reads a volatile variable, then all all variables visible to Thread A when reading the volatile variable will also be re-read from main memory.
4. if two threads are both reading and writing to a shared variable, then using the volatile keyword for that is not enough. You need to use a synchronized in that case to 
   guarantee that the reading and writing of the variable is atomic. Reading or writing a volatile variable does not block threads reading or writing. For this to happen
   you must use the synchronized keyword around critical sections.   


ThreadLocal :-
1. The Java ThreadLocal class enables you to create variables that can only be read and written by the same thread. Thus, even if two threads are executing the same code,
    and the code has a reference to the same ThreadLocal variable, the two threads cannot see each other's ThreadLocal variables.
2. We can create a ThreadLocal subclass that overrides the initialValue() method by this we have initial value set in the thread. 
3. The InheritableThreadLocal class is a subclass of ThreadLocal. Instead of each thread having its own value inside a ThreadLocal, the InheritableThreadLocal grants access
 to values to a thread and all child threads created by that thread. 
 
 
Thread Signaling :-
1. The purpose of thread signaling is to enable threads to send signals to each other. Additionally, thread signaling enables threads to wait for signals from other threads.
2. A simple way for threads to send signals to each other is by setting the signal values in some shared object variable. Thread A may set the boolean member variable 
    hasDataToProcess to true from inside a synchronized block, and thread B may read the hasDataToProcess member variable, also inside a synchronized block.
3. Busy waiting is not a very efficient utilization of the CPU in the computer running the waiting thread, except if the average waiting time is very small. Else, it would
   be smarter if the waiting thread could somehow sleep or become inactive until it receives the signal it is waiting for. A thread that calls wait() on any object becomes
   inactive until another thread calls notify() on that object. In order to call either wait() or notify the calling thread must first obtain the lock on that object. In 
   other words, the calling thread must call wait() or notify() from inside a synchronized block.
    
   As you can see both the waiting and notifying thread calls wait() and notify() from within a synchronized block. This is mandatory! A thread cannot call wait(), notify()
   or notifyAll() without holding the lock on the object the method is called on. If it does, an IllegalMonitorStateException is thrown.
4. But, how is this possible? Wouldn't the waiting thread keep the lock on the monitor object (myMonitorObject) as long as it is executing inside a synchronized block? Will
   the waiting thread not block the notifying thread from ever entering the synchronized block in doNotify()? The answer is no. Once a thread calls wait() it releases the 
   lock it holds on the monitor object. This allows other threads to call wait() or notify() too, since these methods must be called from inside a synchronized block.
   
   Once a thread is awakened it cannot exit the wait() call until the thread calling notify() has left its synchronized block. In other words: The awakened thread must
   reobtain the lock on the monitor object before it can exit the wait() call, because the wait call is nested inside a synchronized block. If multiple threads are awakened
   using notifyAll() only one awakened thread at a time can exit the wait() method, since each thread must obtain the lock on the monitor object in turn before exiting wait().   


Thread Deadlock :- http://tutorials.jenkov.com/java-concurrency/deadlock.html
A deadlock is when two or more threads are blocked waiting to obtain locks that some of the other threads in the deadlock are holding. Deadlock can occur when multiple
threads need the same locks, at the same time, but obtain them in different order.

In some situations it is possible to prevent deadlocks. 1. Lock Ordering 2. Lock Timeout 3. Deadlock Detection

Starvation and Fairness :- If a thread is not granted CPU time because other threads grab it all, it is called "starvation". The thread is "starved to death" because
    other threads are allowed the CPU time instead of it. The solution to starvation is called "fairness" - that all threads are fairly granted a chance to execute.

Causes of Starvation in Java :- 
1. Threads with high priority swallow all CPU time from threads with lower priority.
2. Threads are blocked indefinately waiting to enter a synchronized block, because other threads are constantly allowed access before it.
3. Threads waiting on an object (called wait() on it) remain waiting indefinitely because other threads are constantly awakened instead of it. 
   
Locks :- 
1. A lock is a thread synchronization mechanism like synchronized blocks except locks can be more sophisticated than Java's synchronized blocks. 
2. Synchronized blocks in Java are reentrant. This means, that if a Java thread enters a synchronized block of code, and thereby take the lock on the monitor object the block
    is synchronized on, the thread can enter other Java code blocks synchronized on the same monitor object. 
3. Java's synchronized blocks makes no guarantees about the sequence in which threads trying to enter them are granted access. Therefore, if many threads are constantly
    competing for access to the same synchronized block, there is a risk that one or more of the threads are never granted access - that access is always granted to other 
    threads. This is called starvation.
4. To increase the fairness of waiting threads first we will change the code block to be guarded by a lock rather than a synchronized block.
5.  Fair Lock vs Unfair lock :- Fair lock has equal chance for all the threads, it is slower in compare to unfair lock which is faster(more throughput) and have a possible
        thread starvation.
6. If any thread has locked the resource other locks will be blocked. So if we don't want this type of thing then in that scenario, we have tryLock method which will return 
    boolean value which tells us that if the lock is acquired or not. If it is not acquired(meaning return value is false) then we can try other things as well.   
  

Locks vs Synchronized :- 
1. Locks are explicit and synchronized is implicit.
2.  Synchronized must be write in same method but in case of lock, it can happen that thread is locked in one method but will unlock in another thread.    
3. Synchronized can not define order of threads in which they will execute but in locks we can define the order of it, this is called fairness.


Parallelism vs Concurrency :- 
1. Parallelism is about doing lot of things at once.
2. Concurrency is about dealing with lot of things at once. When shared resource is to be accessed or updated. 

Tools to deal with concurrency :- 
1. Locks/Synchronized
2. Atomic classes
3. Concurrernt data structures(eg. ConcurrentHashMap, BlockingQueue)
4. Completable Future
5. Semaphore 


Executor Service :- Executor service has a thread pool of fixes size(meaning number of threads are fixed) and all the task will come sequentially that's why all the tasks are
    put in the blocking queue(which can handle concurrent operation).
    If task is CPU extensive task then ideal size is CPU core count, else if task is IO extensive then ideal pool size should be more.
    
Note :- Java provides four kinds of thread pools :-
    1. FixedThreadPool :- Fixed number of threads and we will keed submitting the task to it(blocking queue). All these threads fetch task from the queue.
    2. CachedThreadPool :- It will create new threads as needed, but will reuse previously constructed threads when they are available. If the thread is idle for 60 sec then 
                            it will kill the thread.
    3. ScheduledThreadPool :- Schedule the tasks to run based on time delay(and retrigger for fixedRate/fixedDelay). More threads will be created if required.
    4. SingleThreadedExecutor :- There will be only one thread and that thread will be used to fetch the next task from queue and execute it. It will recreate the thread if it
                    gets killed because of the task.
                                       


    
                           

                   
 


















