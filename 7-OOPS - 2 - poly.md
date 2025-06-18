## 2. Polymorphism

### Polymorphism in Java

**Polymorphism** means "many forms." In Java, it allows one interface to be used for a general class of actions. It helps in writing flexible and maintainable code.

---
### Types of Polymorphism

#### 1. **Compile-Time Polymorphism (Static Binding)**

- Achieved using **method overloading**.
- Method call is resolved at **compile time**.
- **Same method name**, different parameter list.

```java
class MathOps {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

---
#### 2. **Run-Time Polymorphism (Dynamic Binding)**

- Achieved using **method overriding**.
- Method call is resolved at **runtime** using the object's type.

```java
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Reference of parent, object of child
        a.sound(); // Calls Dog's sound() method
    }
}
```

---

### Method Overloading vs Overriding

|Feature|Overloading|Overriding|
|---|---|---|
|Binding Type|Compile-time|Runtime|
|Parameters|Must differ|Must be same|
|Inheritance Needed|No|Yes|
|Access Modifier|Any|Cannot reduce visibility|
|Return Type|Can be different|Must be same or subtype|

---
### Why Polymorphism?
- Improves **code reusability** and **readability**
- Enables **dynamic behavior** at runtime
- Makes systems **extensible and maintainable**

### Real-life Analogy

- A person can act as a **student**, **employee**, or **player** based on context.
- Similarly, one interface can be implemented in different ways.

### Key Takeaway

Polymorphism = One name, many forms. Use overloading for compile-time flexibility, and overriding for runtime dynamic behavior.



---
---
---


![[Casting.png]]


### Reference Types and Object Instances in Java (Detailed Explanation)

In Java, objects are created in memory using the `new` keyword, and reference variables are used to access them. Understanding how different reference types and object instances interact is essential for mastering **polymorphism** and **runtime method dispatch**.

---
### What Is a Reference Variable?

A **reference variable** does not hold the object itself. It holds a reference (memory address) to where the object is stored in heap memory.

```java
Animal a = new Dog();
```
                      ## IMP
Here:
- `Animal` is the **reference type** (static type).
- `new Dog()` is the **object instance** (dynamic type).
- The variable `a` can **only access methods and fields defined in the Animal class**, but overridden methods of `Dog` will be called if they exist.

---
### What Happens Internally?

```java
class Animal {
    void sound() {
        System.out.println("Generic animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
    void sniff() {
        System.out.println("Dog sniffing...");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound(); // Bark (Dog's method)
        // a.sniff(); // Error: sniff() is not in Animal's reference type
    }
}
```

*******************
###### This allows **dynamic method dispatch**. At **runtime**, Java checks the actual object type (`Dog`) and calls the correct version of `sound()`, even though the variable `a` is of type `Animal`.

---
#### Why Use `Parent ref = new Child();`?

1. **Runtime polymorphism**: You can decide which subclass to use at runtime.
    
2. **Generic programming**: You can write code that works on the superclass/interface and plug in different subclasses later.
    

```java
void makeSound(Animal a) {
    a.sound();
}

makeSound(new Dog());  // Output: Bark
makeSound(new Animal()); // Output: Generic animal sound
```

---
#### Upcasting and Downcasting

- **Upcasting**: Child object is assigned to a parent reference. Always safe.
    
    ```java
    Animal a = new Dog();
    ```
    
- **Downcasting**: Parent reference is cast back to child type. Needs explicit cast and runtime check.
    
    ```java
    Dog d = (Dog) a; // OK only if 'a' actually refers to a Dog
    ```
    

---
#### Extended Example

```java
class Shape {
    void draw() {
        System.out.println("Drawing shape");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing circle");
    }
    void radius() {
        System.out.println("Radius is 5");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw(); // Calls Circle's draw()

        // s.radius(); // Error: Shape reference doesn't know radius()

        Circle c = (Circle) s; // Downcasting
        c.radius(); // Now accessible
    }
}
```

---

### Real-World Analogy

Imagine you have a **remote** (reference type `TVRemote`) that works with many different brands (`SonyTV`, `SamsungTV`, etc.):

- The **remote** (reference) can only press buttons it knows (`powerOn()`, `volumeUp()`).
    
- The **TV brand** (object) actually performs the behavior depending on its internal design.
    
---
### Summary

| Term           | Meaning                                                             |
| -------------- | ------------------------------------------------------------------- |
| Reference Type | Type of variable (e.g., `Animal`)                                   |
| Object Type    | Type of instance created (e.g., `Dog`)                              |
| Upcasting      | Assigning subclass object to superclass reference                   |
| Downcasting    | Recasting to original subclass to access subclass-specific features |

- Use `Parent ref = new Child();` to gain **flexibility and extensibility**.
- Java uses the **actual object type at runtime** to decide which method to run.
- Methods available are limited by the **reference type**, not object type.

This principle is the core of **runtime polymorphism** in Java.


---
---
---





### Type Casting in Java OOPs: Upcasting and Downcasting

---

### What is Type Casting?

Type casting in Java is converting an object or variable of one type into another. In OOPs, we commonly use type casting with object references in an inheritance hierarchy.

---

### 1. **Upcasting** (Widening Casting)

- **Subclass object is referenced by a superclass type.**
- Happens **implicitly**.
- Used when we want to generalize behavior.
    

```java
class Animal {
    void sound() {
        System.out.println("Generic sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog(); // Upcasting
        a.sound();            // Valid
        // a.bark();          // Not accessible
    }
}
```

#### Why Use Upcasting?

- To write **polymorphic code** (same interface, different implementations).
- Enables dynamic method dispatch.
    
---

### 2. **Downcasting** (Narrowing Casting)

- **Superclass reference is explicitly cast back to a subclass type.**
- Requires **explicit** casting.
- Can throw `ClassCastException` at runtime if not done safely.
    

```java
Animal a = new Dog();      // Upcasting
Dog d = (Dog) a;           // Downcasting

d.sound();                 // Valid
```

#### Unsafe Downcasting:

```java
Animal a = new Animal();
Dog d = (Dog) a; // Throws ClassCastException at runtime
```

To avoid this, use `instanceof`:

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.bark();
}
```

---
### Summary Table

|Casting Type|Direction|Explicit?|Example|
|---|---|---|---|
|Upcasting|Subclass → Super|No|`Animal a = new Dog();`|
|Downcasting|Super → Subclass|Yes|`Dog d = (Dog) a;`|

---
### Final Thoughts

- **Upcasting** is safe and common.
- **Downcasting** should be done carefully and ideally guarded with `instanceof`.
- Both are key to leveraging polymorphism in OOP.

Let me know if you'd like visual diagrams or more examples involving method overriding with casting.




----
-----
---

### `final` Keyword in Java

The `final` keyword is used to declare constants, prevent method overriding, and inheritance. It can be applied to **variables**, **methods**, and **classes**.

---
#### 1. `final` Variable

- A `final` variable cannot be reassigned once initialized.
- It must be initialized **only once**, either at the time of declaration or inside a constructor.
    

```java
final int x = 10;
x = 20; // Error
```

For objects:

Fields can be changed but not reference.

```java
final StringBuilder sb = new StringBuilder("Hi");
sb.append(" there"); // Allowed (object state changes)
sb = new StringBuilder("Hello"); // Error (reference can't change)
```

---
#### 2. `final` Method

- A `final` method **cannot be overridden** by subclasses, no overriding
- Useful when you want to prevent alteration of method logic.
    
```java
class A {
    final void display() {
        System.out.println("A's method");
    }
}

class B extends A {
    void display() { // Error
        System.out.println("B's method");
    }
}
```

---
#### 3. `final` Class

- A class marked `final` **cannot be extended** or inherited.
- Useful for security or when you want to create immutable classes.
    
```java
final class Constants {
    static final double PI = 3.14159;
}

class Derived extends Constants { // Error
}
```

---
### Use Cases

- Enforcing immutability (`final` + `private` + no setters)
- Preventing inheritance in critical utility/helper classes
- Avoiding unintended overriding in core class hierarchies

---
### Summary Table

| Applied To | Meaning                                     |
| ---------- | ------------------------------------------- |
| Variable   | Value can't be changed after initialization |
| Method     | Can't be overridden in subclasses           |
| Class      | Can't be extended/inherited                 |



---
---
---





### Method Overriding, `@Override`, `equals()` and `hashCode()` in Java

---

### Method Overriding in Java

Method overriding lets a subclass provide a specific implementation of a method defined in its superclass.

```java
class Animal {
    void sound() {
        System.out.println("Generic sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```

Here, the `sound()` method is overridden in `Dog`.

---

### `@Override` Annotation

The `@Override` annotation tells the compiler that the method is overriding a superclass method.

```java
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```

#### Benefits:

- Produces compile-time error if the method doesn't correctly override
- Increases readability and helps prevent bugs

**Without `@Override`**:

```java
class Dog extends Animal {
    void Sound() { // Incorrect name, not overriding anything
        System.out.println("Bark");
    }
}
```

This won't throw an error, but will not override `sound()` either.

---
#### `equals()` and `hashCode()` Methods

These are inherited from the `Object` class and are used to compare objects and work properly in hash-based collections.

#### Default Behavior

- `equals()` in `Object` checks **reference equality** (memory address).
- `hashCode()` returns a unique integer identifier (based on memory address).
    

```java
Object obj1 = new Object();
Object obj2 = new Object();
System.out.println(obj1.equals(obj2)); // false
System.out.println(obj1.hashCode());   // different from obj2.hashCode()
```

#### Why Override `equals()`?

To compare **contents** of two objects.

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return this.name.equals(p.name);
    }
}
```

#### Why Override `hashCode()`?

So that two equal objects (as per `equals()`) return the **same hash code** — required for collections like `HashMap`, `HashSet`, etc.

```java
@Override
public int hashCode() {
    return name.hashCode();
}
```

If you override `equals()` but not `hashCode()`, two equal objects might go into different hash buckets.

---

### Example: Proper `equals()` and `hashCode()` Implementation

```java
class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id == s.id && name.equals(s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

Now two `Student` objects with same `id` and `name` are considered equal and will have the same hash code.

---

### Real-World Example with HashSet

```java
Set<Student> set = new HashSet<>();
set.add(new Student(1, "John"));
set.add(new Student(1, "John"));
System.out.println(set.size()); // 1 if equals & hashCode are correctly overridden
```

---

### Summary Table

|Concept|Meaning/Usage|
|---|---|
|Method Overriding|Subclass redefines a method from parent class|
|`@Override`|Ensures correctness of overriding and catches typos|
|`equals()`|Compares object content; default checks reference equality|
|`hashCode()`|Returns integer used in hashing structures|
|Important Rule|If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()`|

---

Let me know if you want to move to interfaces, abstract classes, or more examples on collections.