Main function of JVM is :- 
1. Load and execute the application.

java MyApp.class -> It will create JVM instance and then it will load and execute the program.

JVM --> ClassLoader --> Runtime data areas --> Execution Engine --> Native Method Interface(JNI) --> Native Method areas

ClassLoader :-
1. Load
2. Link
3. Initialize

1. Load :- Loading bytecode into memory.
	1. BootStrap class loader(Rt.jar)
	2. Extension class loader(jre/lib/ext)
	3. Application class loader(CLASSPATH, -cp)
	
	ClassLoader follow the delegation model where on request to find a class or resource, a classLoader instance will delegate the search of the class or resource to the
	parent classLoader. ex. We have a request to load an application class into JVM. The System classLoader first delegates the loading of that class to its parent extension 
	classLoader which in turn delegates it to the bootstrap classLoader. Only if the bootstrap and then extension classLoader is unsuccessful in loading the class, the system
	classLoader tries to load the class itself.
	
2. Link :- 
	1. Verify :- Bytecode which is coming is valid or not.
	2. Prepare :- Memory allocated to static variable and default value is allocated to variable.
	3. Resolve:- Resolve other class reference.
	
3. Initialize :- Static initializer of class will run i.e. static block, static value set at memory location.



Runtime Data Areas :- 
1. Method Area
2. Heap
3. Java Stacks
4. PC Registers
5. Native Method stacks

1. Method Area :- Memory allocated to JVM, i.e. class data, static data. It is also called as permGen space. It is called metaSpace in java 8.

2. Heap :- Object data stored in heap.

3. PC Registers :- Next program counter instrction for each thread.

4. Java Stacks :- stack frame for each thread (data corresponds to each thread).

5. Native Method stacks :- Native call to OS.

Method area and heap are per JVM basis but PC Registers, Java Stacks, Native Method Stacks are per thread basis.



Execution Engine :- When the current instrction in PC registers is loaded then Execution engine will execute it.

1. Interpreter
2. JIT Compiler
3. Hotspot Profiler
4. GC

1. Interpreter :- After PC Registers loaded with instruction, Interpreter will execute that instruction. It will call Native Method Interface to execute the instrction, these 
				  interfaces are platform dependent.

2. JIT Compiler :- The source code will be converted into byteCode and that code is converted into machine language only when the application needs it. JIT complier will compile the code before which is frequently used so that interpreter can use it directly. It will improve the performance.				  

ClassNotFoundException vs NoClassDefFoundError :- 
Class loader fails to verify a class byte code we mention in Link phase of class loading subsystem we get ClassNotFoundException. Class loader fails to resolving references of
 a class in Link phase of class loading subsystem we get NoClassDefFoundError.


JVM Memory Model :- 
JVM Heap memory is physically divided into two parts – Young Generation and Old Generation. The young generation is the place where all the new objects are created. 
When the young generation is filled, garbage collection is performed. This garbage collection is called Minor GC. Young Generation is divided into three parts – Eden Memory
and two Survivor Memory spaces.

Java Garbage Collection is the process to identify and remove the unused objects from the memory and free space to be allocated to objects created in future processing.
Garbage Collector is the program running in the background that looks into all the objects in the memory and find out objects that are not referenced by any part of the program.
All these unreferenced objects are deleted and space is reclaimed for allocation to other objects. 


Basic ways of GC involves three steps :-
1. Marking :- This is the first step where garbage collector identifies which objects are in use and which ones are not in use.
2. Normal Deletion: Garbage Collector removes the unused objects and reclaim the free space to be allocated to other objects. 
3. Deletion with Compacting: For better performance, after deleting unused objects, all the survived objects can be moved to be together.
    This will increase the performance of allocation of memory to newer objects.
    
Five types of GC :-
1. Serial GC :- Serial GC uses the simple mark-sweep-compact approach for young and old generations garbage collection. It is good for small applications with low memory footprint.
2. Parallel GC :- Parallel GC is same as Serial GC except that is spawns N threads for young generation garbage collection where N is the number of CPU cores in the system. We can control the number of threads 
        using -XX:ParallelGCThreads=n JVM option.Parallel Garbage Collector is also called throughput collector because it uses multiple CPUs to speed up the GC performance. Parallel GC uses a single thread for Old
        Generation garbage collection.
3. Parallel Old GC :-  This is same as Parallel GC except that it uses multiple threads for both Young Generation and Old Generation garbage collection.
4. Concurrent Mark GC :- CMS Collector is also referred as concurrent low pause collector. It does the garbage collection for the Old generation. CMS collector tries to minimize the pauses due to garbage collection 
    by doing most of the garbage collection work concurrently with the application threads.CMS collector on the young generation uses the same algorithm as that of the parallel collector. This garbage collector is
    suitable for responsive applications where we can’t afford longer pause times. We can limit the number of threads in CMS collector using -XX:ParallelCMSThreads=n JVM option.
5. G1 GC :- The G1 collector is a parallel, concurrent, and incrementally compacting low-pause garbage collector.Garbage First Collector doesn’t work like other collectors and there is no concept of Young and Old
    generation space. It divides the heap space into multiple equal-sized heap regions. When a garbage collection is invoked, it first collects the region with lesser live data, hence “Garbage First”.