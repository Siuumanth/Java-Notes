## 1. Inheritance 
Inheritance is a mechanism where one class (child/subclass) inherits properties and methods from another class (parent/superclass). It enables code reuse, promotes modularity, and allows polymorphism.

```java
class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}
```

Here, `Dog` inherits `speak()` from `Animal`.

---

# Types of Inheritance (Java Classes)

| Type         | Supported in Java? | Description                    |
| ------------ | ------------------ | ------------------------------ |
| Single       | Yes                | One class inherits another     |
| Multilevel   | Yes                | Chain: A → B → C               |
| Hierarchical | Yes                | A is parent of B and C         |
| Multiple     | No (with classes)  | Achievable via interfaces only |



---
---
---



### `super` Keyword

`super` refers to the immediate superclass object.
` this()` executes the const of the same class and `super()` executes the constructor of the parent class.

##### Uses of `super`:
1. Call parent class constructor: `super(...)`
2. Access parent’s field: `super.var`
3. Call parent’s method: `super.method()`

```java
class Animal {
    String name = "Animal";

    Animal() {
        System.out.println("Animal constructor");
    }

    void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    String name = "Dog";

    Dog() {
        super(); // calls Animal()
        System.out.println("Dog constructor");
    }

    void showNames() {
        System.out.println(super.name); // Animal
        System.out.println(this.name);  // Dog
    }

    @Override
    void speak() {
        super.speak();  // call Animal's speak
        System.out.println("Dog says Bark");
    }
}
```

---
### Constructor Overloading

When a class has multiple constructors with different parameter sets.

```java
class Student {
    String name;
    int age;

    Student() {
        this("Unknown", 0); // calls other constructor
    }

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---
### Inheritance + Constructor Overloading

Both parent and child can overload constructors. Child class must explicitly call the parent constructor using `super(...)`.

```java
class Person {
    String name;

    Person() {
        this("NoName");
        System.out.println("Person no-arg constructor");
    }

    Person(String name) {
        this.name = name;
        System.out.println("Person param constructor");
    }
}

class Employee extends Person {
    int id;

    Employee() {
        super();
        System.out.println("Employee no-arg constructor");
    }

    Employee(String name, int id) {
        super(name);
        this.id = id;
        System.out.println("Employee param constructor");
    }
}
```

#### Constructor Execution Order:

1. Superclass constructor always runs before subclass constructor.
2. If `super()` is not written, Java adds it automatically (no-arg only).

---
### "Every Constructor Has super()" - Explained

If you don't write `super()` explicitly in a constructor, Java automatically inserts it **as the first statement**. This only works if the parent class has a **no-arg constructor**. Otherwise, a compilation error occurs.

```java
class A {
    A() {
        System.out.println("A's constructor");
    }
}

class B extends A {
    B() {
        // super() is implicitly inserted here
        System.out.println("B's constructor");
    }
}
```

But if `A` has only a **parameterized constructor**, then `B` must explicitly call it:

```java
class A {
    A(String msg) {
        System.out.println(msg);
    }
}

class B extends A {
    B() {
        super("From A's constructor");
        System.out.println("B's constructor");
    }
}
```

---
### Method Overriding (Recap)

If a subclass redefines a method with the same signature as the parent, it's called overriding.

```java
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Cat extends Animal {
    @Override
    void eat() {
        System.out.println("Cat eats fish");
    }
}
```

Overriding enables runtime polymorphism.

---
### Summary

- Use `extends` to inherit one class from another.
- Constructors follow top-down execution using `super(...)`.
- You can overload constructors in both parent and child.
- `super` is used for calling parent methods/constructors/fields.
- Every constructor calls `super()` implicitly unless manually defined.
- Method overriding allows subclass to change inherited behavior.




---
---
---



### Why Multiple Inheritance Doesn’t Work with Classes in Java

Java **does not support multiple inheritance with classes** (i.e., a class cannot extend more than one class). This is primarily to avoid **ambiguity and complexity**, especially the **Diamond Problem**.

---

#### The Diamond Problem (Ambiguity Example)

```java
class A {
    void show() {
        System.out.println("From A");
    }
}

class B extends A {
    void show() {
        System.out.println("From B");
    }
}

class C extends A {
    void show() {
        System.out.println("From C");
    }
}

// Now what if Java allowed this?
// class D extends B, C {}  // Not allowed in Java
```

In the above case, if `D` inherited both `B` and `C`, and both override `show()`, **which `show()` should `D` inherit?** This is **ambiguous**, and Java avoids this by **not allowing multiple inheritance with classes**.

Same problem, if 2 classes have a function with same name, the sub class cannot now which function exactly to inherit.

---
### Summary

- **Multiple inheritance with classes is disallowed** in Java to avoid ambiguity (diamond problem).
- **Multiple inheritance via interfaces is allowed**, since the implementing class defines the final behavior.
    
Let me know if you want this added to your notes or explained using interfaces and `default` methods next.


---
---
---


### Every Class in Java Extends `Object`

In Java, **every class implicitly extends the `Object` class**, unless it explicitly extends another class. This means all Java classes inherit the methods of `Object`, even if you don’t write `extends Object` in your code.

```java
class MyClass {
    int value;
}
```

This is internally equivalent to:

```java
class MyClass extends Object {
    int value;
}
```

---
### Why is `Object` Important?

`Object` is the **root class** of the Java class hierarchy. It provides a set of **basic methods** that are common to all Java objects:

| Method               | Purpose                                              |
| -------------------- | ---------------------------------------------------- |
| `toString()`         | Returns string representation of object (printing)   |
| `equals(Object obj)` | Checks logical equality                              |
| `hashCode()`         | Returns hash code (used in hashing, like in HashMap) |
| `getClass()`         | Returns runtime class info                           |
| `clone()`            | Makes a field-by-field copy (shallow copy)           |
| `finalize()`         | Cleanup before garbage collection (deprecated)       |
| `wait()`, `notify()` | Used for thread communication (in concurrency)       |
hashCode returns a hash of all your members.

---
#### Example: `toString()` and `equals()`

```java
class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student name: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student s = (Student) obj;
        return this.name.equals(s.name);
    }
}

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student("Alice");
        Student s2 = new Student("Alice");

        System.out.println(s1); // Calls toString()
        System.out.println(s1.equals(s2)); // Calls overridden equals()
    }
}
```

---
### Key Takeaways

- All Java classes inherit from `Object`, either directly or indirectly.
- This gives a base set of functionalities that all objects share.
- Overriding `toString()`, `equals()`, and `hashCode()` is common in custom classes.
- You can safely treat any object as an `Object` type, enabling polymorphism and generic code.

```java
Object obj = new Student("Bob");
System.out.println(obj.toString()); // Works, because Student extends Object
```
