We can create string in following ways :-
1. String literal :- In this case, JVM will first check into the String pool. If that string value
is found in the string pool then it will not create the new string object. New string will point to
the existing string in the string pool.

```
String s1="Hello";
String s2="Hello";
System.out.println(s1==s2); //true
```0


2. new String :- 
```
String s = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1==s2); //false


String s = new String("Hello").intern();
String s2 = new String("Hello").intern();
System.out.println(s1==s2); //true

```

**Note** :- String interning
String s = new String("Hello").intern(); // Now it is interned. In case of interned, instead of creating new string value, it will look into the String pool.
1. From Java 1.7, String pool is stored in heap area(till java 1.6, it was stored in permgen space). 
2. String is immutable, it is a thread-safe. It caches hashCode so in case of very big string we don't have to calculate its hash again and again. We can use the cache and do all
the operation.


**Note** :- 
1. String s = "This " + 20 + " is " + Boolean.valueOf(true);
   System.out.println(s); //print This 20 is true.
   
   Internally it will create StringBuilder, and on every + operator, it will append these values and finally it will return .toString()
   

StringBuffer/StringBuilder :- StringBuilder in java represents a mutable sequence of characters. String class in java is immutable. StringBuilder class does not provide any
gurantee of synchronization whereas the stringBuffer class does. StringBuilder is fast than the StringBuffer. 