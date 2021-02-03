1. Can we override final method  :- No 
2. Can final class can be override :- No
2. Can we override static method and can we call static method from object
3. Can we have two main method in java
4. Can singleton class be overrided and if yes how many objects will be created
5. How can we create thread and which one is better Runnable or new Thread()
6. Can we make constructor final, static and abstract.
7. Comparator vs Comparable :- https://www.javatpoint.com/difference-between-comparable-and-comparator
8. ArrayList vs LinkedList 
9. Thread vs Runnable Interface
10. Can I override and overload static methods in Java. Ans: No
11. ClassNotFoundException vs NoClassDefFoundError
12. Why wait notify and notifyAll called from synchronized block or method in Java



**Note** :-
1. If subclass is having same method signature as base class then it is known as method
overriding. Its execution decided at runtime.<br/>

Static methods belong to class. In method overriding, which method will be executed, it has 
decided at run time while Static method is resolved at compile time.<br/>
```
class  Display
{
	public static void hello()    //static method 
	{
		System.out.println("Hello...Good morning");
	}
}
class  DisplayMessage extends Display
{
	public static void hello()      //redefining of base class static method in derived class
	{
		System.out.println("Hello...everyone");
	}
}
public class DisplayMsg
{
	public static void main(String args[])
	{
		Display d=new Display();     //creation of base class object
		d.hello();  //Hello…Good morning
 
		d=new DisplayMessage();    
		//base class reference is referring to derived class object and as per overriding rules it should call DisplayMessage hello()
 
		d.hello();       //but it calls Display class hello(). O/P:- Hello…Good morning
 
		DisplayMessage ds=new DisplayMessage();   //creation of derived class object
		ds.hello(); //Hello…everyone
	}
} 
```

2. Imp method from Object class :- hashcode, equals, clone, toString, finalize, wait, notify, notifyAll
3. Transient variable :- Transient variable is the variable whose value is not serialized during
    serialization. We will get default value for these variables when you deserialize it. 
4. String is immutable :- String is immutable. They are thread-safe, it also helps in security, 
cached hash value.
5. When we put final before method that means I don't want to override this method or class. But in Java Specification, **Constructor declarations are not members.  They are
never inherited and therefore are not subject to hiding or overriding.** That means constructor is implicitly final, that's why we don't need it as final and compiler will
throw an error for that.
6. We can not define constructor as static, because static means class level and constructor is used to create a new instance of that class.
7. 
    Comparable vs Comparator Interface :- Both are interfaces and can be used to sort collection elements.
    1. Comparable affects the original class, i.e. the actual class is modified. Comparator doesn't affect the original class. **Comparable provides a single sorting sequence**,
       we can sort the collection on the basis of a single element such as id, name, price. **Comparator provides muliple sorting sequence.**, we can sort the collection on the
       basis of multiple elements such as id, name and price.
       
```
     class Student implements Comparable<Student>{  
     int rollno;  
     String name;  
     int age;  
     Student(int rollno,String name,int age){  
         this.rollno=rollno;  
         this.name=name;  
         this.age=age;  
     }  
     public int compareTo(Student st){  
         if(age==st.age)  
         return 0;  
         else if(age>st.age)  
         return 1;  
         else  
         return -1;  
     }

    public static void main(String args[]){  
        ArrayList<Student> al=new ArrayList<Student>();  
        al.add(new Student(101,"Vijay",23));  
        al.add(new Student(106,"Ajay",27));  
        al.add(new Student(105,"Jai",21));  
          
        Collections.sort(al);  

        ArrayList<Student> al=new ArrayList<Student>();  
        al.add(new Student(101,"Vijay",23));  
        al.add(new Student(106,"Ajay",27));  
        al.add(new Student(105,"Jai",21));  
          
        System.out.println("Sorting by Name");  
        //Using NameComparator to sort the elements  
        Collections.sort(al,new NameComparator());  
    }
    }  

    class AgeComparator implements Comparator<Student>{  
        public int compare(Student s1,Student s2){  
            if(s1.age==s2.age)  
            return 0;  
            else if(s1.age>s2.age)  
            return 1;  
            else  
            return -1;  
        }  
    }

    
    
```

8. ArrayList vs LinkedList :- ArrayList is implemented using array as internal data structure, it can be dynamically resized. LinkedList is implemented using doubly linked 
    list as internal data structures. For insertion and deletion, it will take O(n) but for linkedList, it will take O(1) time. Search is O(1) in arrayList and O(n) in linked
    list. 
9. Vector vs ArrayList :- Vector and ArrayList both have implemented List interface meaning both are ordered collection and allow duplicates. Both are index based collection and
    we can get(index) to retrieve objects. Vector is synchronized and thread-safe while ArrayList is neither synchronized nor thread-safe that's why vector is slow and arrayList
    is fast.
10. Checked Exception vs Unchecked Exception :- Checked exception are those excetion which are checked at compile time. If we don't handle it, we will get compilation error.
    We can use try-catch, throws to solve that problem. Unchecked exceptions are those which are not handled at compile time and java won't throw error   if we didn't handle it.
    ex.:- NullPointerException, IndexOutOfBound.     
11. Volatile variable :- It is a special variable which is used to signal threads, a compiler that this particular variables are going to be updated by multiple threads inside
    java application. That means its value should always be read from main memory and thread should not use cached value of that variable from its own stack.
12. ClassNotFoundException and NoClassDefFoundError occur when a particular class is not found at runtime. ClassNotFoundException is an exception that occurs when you try 
    to load a class at run time using Class.forName() or loadClass() methods and mentioned classes are not found in the classpath. NoClassDefFoundError is an error that occurs
    when a particular class is present at compile time, but was missing at run time.
13. If we don't call wait() or notify() method from synchronized context we will receive IllegalMonitorStateException in Java.
    Que :- Why this exception will occur ?
    Ans :- We use wait(), notify(), or notifyAll() method mostly for inter-thread communication in Java. One thread is waiting after checking a condition e.g. In the classic
    Producer-Consumer problem, the Producer thread waits if the buffer is full and Consumer thread notify Producer thread after it creates a space in the buffer by consuming 
    an element. We understand from this :-
    1. The Producer thread tests the condition (buffer is full or not) and confirms that it must wait (after finding buffer is full).
    2. The Consumer thread sets the condition after consuming an element from a buffer.
    3. The Consumer thread calls the notify () method; this goes unheard since the Producer thread is not yet waiting.
    4. The Producer thread calls the wait () method and goes into waiting state.
    
    So due to race condition here we potential lost a notification and if we use buffer or just one element Produce thread will be waiting forever and your program will hang.
    
14. MetaSpace vs Permgen :- In java 8, PermGen is completely removed and replaced with MetaSpace. 
PermGen is the memory area in Heap that is used by the JVM to store class and method objects. If your application loads lots of classes, PermGen utilization will be high. 
PermGen also holds ‘interned’ Strings.  It is not unusal to see the error “java.lang.OutOfMemoryError: PermGen space“ if you are loading unusual number of classes.

Metaspace is NOT part of Heap. Rather Metaspace is part of Native Memory (process memory) which is only limited by the Host Operating System.

Que :- what is the significance of this change?
Ans :- While you will NOT run out of PermGen space anymore (since there is NO PermGen), you may consume excessive Native memory making the total process size large. 
    The issue is, if your application loads lots of classes (and/or interned strings), you may actually bring down the Entire Server (not just your application).
    Why ? Because the native memory is only limited by the Operating System. This means you can literally take up all the memory on the Server. Not good. It is 
    critical that you add the new option -XX:MaxMetaspaceSize  which sets the Maximum Metaspace size for your application.  Note that it is no longer sufficient to just
    monitor the Heap Size. You must also monitor the Metaspace which you can do by just keeping an eye on the ‘process size’ using your Operating System utilities
    (Example: ‘top’ in Unix/Linux, ‘Task Manager’ in Windows).
    
15. The string constant pool is a small cache that resides within the heap. Java stores all the values inside the string constant pool on direct allocation. This way, 
    if a similar value needs to be accessed again, a new string object created in the stack can reference it directly with the help of a pointer.