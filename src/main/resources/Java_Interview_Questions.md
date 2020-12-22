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
