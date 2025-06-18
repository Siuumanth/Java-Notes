## Inner Classes in Java
---
#### What is an Inner Class?

An **inner class** in Java is a class declared **inside another class or method**. It is associated with the outer class and has access to its members, even if they're `private`.

---
### Why Use Inner Classes?

- To logically group classes that are only used in one place
- To improve encapsulation
- To make code more readable and maintainable

---
### Types of Inner Classes in Java

#### 1. **Non-Static Inner Class (Regular Inner Class)**

- Defined **inside** another class and **not static**.
- Has access to all members (including `private`) of the outer class.
- Requires an instance of the outer class to be created.

```java
class Outer {
    private String msg = "Hello";

    class Inner {
        void display() {
            System.out.println(msg); // Accessing outer class private field
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.display();
    }
}
```

---
#### 2. **Static Nested Class**

- Declared with the `static` keyword.
- Cannot access non-static members of the outer class directly.
- Can be instantiated without creating an instance of the outer class.
- Static can be only used on inner class.

```java
class Outer {
    static int data = 30;

    static class Nested {
        void msg() {
            System.out.println("Data is: " + data);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer.Nested obj = new Outer.Nested();
        obj.msg();
    }
}
```

---
#### 3. **Local Inner Class (Inside Method)**
- Defined **within a method** of the outer class.
- Can access final or effectively final variables of the method.

```java
class Outer {
    void outerMethod() {
        int x = 100;

        class LocalInner {
            void print() {
                System.out.println("x = " + x);
            }
        }

        LocalInner local = new LocalInner();
        local.print();
    }
}

public class Main {
    public static void main(String[] args) {
        new Outer().outerMethod();
    }
}
```

---

#### 4. **Anonymous Inner Class**

- A class with **no name**, declared and instantiated in a single expression.
    
- Used mostly for **implementing interfaces** or **abstract classes** in short-lived use cases.
    

```java
abstract class Animal {
    abstract void makeSound();
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal() {
            void makeSound() {
                System.out.println("Bark");
            }
        };

        dog.makeSound();
    }
}
```

---

### Comparison Table

|Type|Static?|Can access outer class members?|Usage Scope|
|---|---|---|---|
|Regular Inner Class|❌ No|✅ Yes (even private)|Anywhere in outer class|
|Static Nested Class|✅ Yes|❌ Only static members|Outer class level|
|Local Inner Class|❌ No|✅ Final or effectively final|Inside a method|
|Anonymous Class|❌ No|✅ Yes (within scope)|One-time usage|

---

### Summary

- Inner classes help group logic and improve encapsulation.
    
- They have access to outer class members depending on their type.
    
- They are widely used in GUI programming, event handling, and callback mechanisms.





---
---
---




### Anonymous Inner Classes in Java (Modifying Methods without Inheritance)

---
### What is an Anonymous Inner Class?

An **anonymous inner class** is a **class without a name** that is both **declared and instantiated** in a single statement.

They are used mainly to **override methods of a class or interface** without explicitly creating a subclass. This allows us to provide custom behavior on the spot without the need to extend a class or implement an interface in a separate file.

---
### Why Use Anonymous Inner Classes?

- When you want to override or implement methods temporarily
- When you don’t want to create a named class for short-lived usage
- Used in GUI event handling, threads, or callbacks

---
### Example 1: Modify a Method Without Creating a Subclass

```java
class Greeting {
    void sayHello() {
        System.out.println("Hello from Greeting");
    }
}

public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting() {
            @Override
            void sayHello() {
                System.out.println("Hello from Anonymous Class");
            }
        };

        g.sayHello();
    }
}
```

### Output:

```
Hello from Anonymous Class
```

In the above example:
- We didn't extend the `Greeting` class.
- Yet we overrode the `sayHello()` method using an **anonymous inner class**.
---
### Example 2: Extending an Abstract Class Anonymously

``` java
abstract class Animal {
    abstract void makeSound();

    void breathe() {
        System.out.println("Animal is breathing");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Dog barks");
            }
        };

        dog.makeSound();  // Calls overridden method
        dog.breathe();    // Calls concrete method from abstract class
    }
}
```

#### Output:

```
Dog barks
Animal is breathing
```
#### Explanation:

- We have an abstract class `Animal` with one abstract method `makeSound()` and one concrete method `breathe()`.
- Using an anonymous inner class, we override `makeSound()` directly inside `main()` without creating a separate subclass.
- This is useful when you need a **one-time implementation** of an abstract class.
- It helps reduce boilerplate code.

---
### Features of Anonymous Inner Class
- Can only be used **once** (single-use class)
- Cannot have a constructor
- Automatically extends the class or implements the interface it is based on
- Must override **all abstract methods** (if implementing an interface)

---

### ✅ Core Rule:

You **cannot instantiate an abstract class directly**.

```java
Animal a = new Animal(); // ❌ Not allowed
```

However, in this line:

```java
Animal dog = new Animal() {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
};
```

You're **not directly creating an instance of `Animal`**.
Instead, you're doing something sneaky and powerful:

> You're creating an **anonymous subclass** of `Animal` **right there on the spot**, and then creating an object of that **subclass**.

##### Breakdown:

- `new Animal() { ... }` = Define an unnamed subclass of `Animal` and override abstract methods.
    
- `{ ... }` = Body of that anonymous subclass (must implement all abstract methods).
    
- `Animal dog =` = The reference type is `Animal`, but it actually points to an object of this unnamed subclass.

This is **completely valid**, because you're creating an object of a **concrete (non-abstract)** class — just one that you defined anonymously.

---
### Analogy:

Think of abstract class as a **template**. You're not using the template directly. Instead, you're saying:

> “I need one object of a class based on this template — but I’ll fill in the missing methods right now and never use it again.”

---
### Summary:

|Concept|Valid?|Explanation|
|---|---|---|
|`new Animal()`|❌|Abstract classes can't be instantiated directly|
|`new Animal() { ... }`|✅|Creates an **anonymous subclass** and object of it|

Let me know if you want a visualization of this flow.

### Limitations
- Cannot define a named constructor
- Cannot define static members
- Cannot be reused across multiple contexts

---
### Summary Table

|Feature|Supports?|
|---|---|
|Name|❌ No|
|Override Methods|✅ Yes|
|Reusability|❌ No (one-time use)|
|Implements Abstract Class|✅ Yes|
|Implements Interface|✅ Yes|
|Static Members Allowed|❌ No|

---

### Final Words

Anonymous inner classes are a powerful tool for **quick modifications** of behavior without inheritance. They are commonly used in **event-driven programming**, especially with Java GUI frameworks like Swing and Android.






---
---
---







# Enums 

### Java Enums - Notes and Use Cases

---
### What is an Enum?

An `enum` in Java is a special data type or standalone class that represents a **fixed set of constants**.
- Enums are implicitly `final` and `static`.
- Can have constructors, methods, and fields.
- Enums cannot be instantiated using `new`.

Enum class extends enum class, and not Object class.

---
### Basic Enum Example

```java
enum Direction {
    NORTH, SOUTH, EAST, WEST;
}

public class Main {
    public static void main(String[] args) {
        Direction d = Direction.NORTH;
        System.out.println(d);
    }
}
```

---
### Enum with Fields and Methods

```java
enum Status {
    SUCCESS(200),   // these are undefined constructors
    ERROR(500),
    NOT_FOUND(404),
    UNAUTHORIZED;    // Needs an unparameterized constructor, or its error

    private final int code;

    Status(int code) {     // this constructor defines what happes 
        this.code = code;    // when user does the below
    }


    public int getCode() {   // getCode
        return code;
    }
}

public class Main {
    public static void main(String[] args) {
        Status s = Status.ERROR;
        System.out.println("Code: "+ s + s.getCode());
    }
}


// Output : Code: ERROR 500

```

---
### Why Use Enums?

- Improves code readability and safety (avoids magic strings/integers)
- Used in switch statements
- Great for representing states, choices, status codes, etc.

---

### Common Use Cases of Enums

1. **Status Codes / Responses**

```java
enum HttpStatus {
    OK(200), BAD_REQUEST(400), UNAUTHORIZED(401), NOT_FOUND(404);
    // ...
}
```

2. **Workflow States**

```java
enum OrderState {
    PLACED, SHIPPED, DELIVERED, CANCELLED;
}
```

3. **User Roles / Access Levels**
    
```java
enum Role {
    ADMIN, MODERATOR, USER, GUEST;
}
```

---
#### Enums in Switch Statement

```java
Day day = Day.SATURDAY;
switch(day) {
    case SATURDAY:
    case SUNDAY:
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Weekday");
}
```

---
### Why Enums Are “Light”:

- **Singleton-like nature**: Each enum constant is created **once** and reused. So `Status.SUCCESS` is a single instance — memory-efficient and safe.
    
- **No manual instantiation**: You don’t create them with `new`, which avoids unnecessary object creation.
    
- **Fixed set of instances**: The enum constants are created at class load time, making them predictable and fast to access.
    
- **Immutable**: Like constants, they don’t change after being created — this makes them thread-safe and low-overhead.
    
### Internally:

Each enum constant is actually an instance of the enum **class**, so technically it’s an object. But because Java optimizes the lifecycle and memory allocation for enums, they behave as **lightweight, efficient objects**.

---

### Java Enums - Accessing All Enum Values

---
#### Using `values()` Method

Every enum in Java gets a built-in static method called `values()` which returns an array of all constants in the order they were declared.

#### Example:

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}

public class Main {
    public static void main(String[] args) {
        for (Day d : Day.values()) {
            System.out.println(d);
        }
    }
}
```

#### Output:

```
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
SUNDAY
```

---
### Getting Name and Ordinal

- `name()` — returns the exact name of the constant as a String
- `ordinal()` — returns the position (index) of the constant starting from 0
    

```java
Day today = Day.WEDNESDAY;
System.out.println(today.name());    // WEDNESDAY
System.out.println(today.ordinal()); // 2
```









---
---
---






# Annotations

### Java Annotations - Notes

---
### What Are Annotations?

Annotations are **metadata** added to Java code — classes, methods, variables, etc. They do **not affect program logic directly**, but are used by the **compiler or frameworks/tools** for additional processing. It can be used to solve compile time issues during development only.

---
### Built-in Java Annotations

#### 1. `@Override`

Indicates that a method is **overriding** a superclass method.

```java
class Parent {
    void greetThePerson() {
        System.out.println("Hello from parent");
    }
}

class Child extends Parent {
    @Override
    void greetThePerson() {
        System.out.println("Hello from child");
    }
}

// If the method name in subclass was different from what was there above, @override would help us by throwing error, if @override was not there then a new method would be created instead of overriding.
```

**Output:**

```
Hello from child
```

- Ensures that a method with the same signature exists in the parent. If not, compiler throws an error.
    
---
#### 2. `@Deprecated`

Marks a method/class as **obsolete** or **not recommended**.
Marks that a method, class, or field should **not be used** anymore and might be removed in future.

```java
class Legacy {
    @Deprecated
    void oldMethod() {
        System.out.println("Old logic");
    }
}

class Main {
    public static void main(String[] args) {
        new Legacy().oldMethod(); // Will compile but show a warning
    }
}
```

**Output:**

```
Old logic
```

- Helps developers move away from outdated APIs.
- IDEs and compilers show warnings to discourage usage.

---
#### 3. `@SuppressWarnings`

Tells the compiler to **ignore warnings**. Tells the compiler to **suppress specific warnings** like unchecked operations, deprecation, etc.

```java
class Demo {
    @SuppressWarnings("unchecked")
    void example() {
        java.util.List list = new java.util.ArrayList();
        list.add("test");
    }
}
```

- Common values: `"unchecked"`, `"deprecation"`, `"rawtypes"`
- Helps clean up compiler output, especially when using legacy code.

---
### Additional Common Annotations

##### 4. `@FunctionalInterface`

Used to indicate that an interface is meant to be a **functional interface** (i.e., only one abstract method).

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

- Compiler error if you try to add more than one abstract method.
- Essential for lambda expressions and streams.
    
---
##### 5. `@SafeVarargs`

Applied to methods with **generic varargs**, to suppress unsafe operation warnings.

``` java
@SafeVarargs
public static <T> void printAll(T... items) {
    for (T item : items) {
        System.out.println(item);
    }
}
```

- Useful in utility methods using generics

---
### Custom Annotations

You can define your own annotations using `@interface`

```java
@interface MyAnnotation {
    String value();
}

@MyAnnotation(value = "Example")
class Demo {}
```

> Custom annotations are often used in frameworks like Spring, JUnit, etc., and processed using **reflection**.

---
### Use Cases

- **Frameworks** (Spring, Hibernate, JUnit): inject dependencies, configure beans, test cases
- **Compile-time checks**: like `@Override`
- **Code documentation**: like `@Deprecated`

---


