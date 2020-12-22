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