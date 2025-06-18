## Functional Interfaces and Lambda Expressions in Java

---
### What is a Functional Interface?

A **functional interface** in Java is an interface that has **exactly one abstract method**. It can have multiple **default** or **static** methods, but only **one abstract method**.

> Functional interfaces are the foundation of **lambda expressions** in Java.

**Example:**

```java
@FunctionalInterface
interface Greetable {
    void greet(String name);
}
```

- The annotation `@FunctionalInterface` is **optional**, but it helps the compiler enforce the rule.
- If you try to add a second abstract method, the compiler will throw an error.
    
---
### What is a Lambda Expression?

A **lambda expression** is a shorthand way to write an **implementation of a functional interface**. It only works with functional interaface.

**Syntax:**

```java
(parameter) -> { method body }
```

**Example:**

```java
Greetable g = (name) -> {   
    System.out.println("Hello, " + name);   // can put in 1 line only
};

Greetable g = name ->  System.out.println("Hello, " + name);   // or this

g.greet("Alice"); // Output: Hello, Alice
```

- Lambda expressions make the code more concise and readable.
- No need to use anonymous inner classes.

---
### Why Use Lambda Expressions?

- Reduce boilerplate code
- Improve readability
- Enable functional programming in Java (Streams, higher-order functions, etc.)

---
### Lambda Example Using Threads (Runnable)

```java
Runnable r = () -> System.out.println("Running thread via lambda");
new Thread(r).start();
```

This is equivalent to:

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running thread via lambda");
    }
};
```

---
### Real-Life Example: Calculator

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("Add: " + add.operate(3, 5));      // Add: 8
        System.out.println("Multiply: " + multiply.operate(3, 5)); // Multiply: 15
    }
}
```

- The `Calculator` interface is a functional interface.
- We assign lambda expressions to implement the `operate` method.

---

### Functional Interfaces in Java API

Java already provides many functional interfaces in the `java.util.function` package:

| Interface        | Abstract Method | Description                  |
| ---------------- | --------------- | ---------------------------- |
| `Predicate<T>`   | `test(T t)`     | Returns boolean              |
| `Function<T, R>` | `apply(T t)`    | Takes input, returns result  |
| `Consumer<T>`    | `accept(T t)`   | Takes input, returns nothing |
| `Supplier<T>`    | `get()`         | Takes nothing, returns value |

---
### Summary

- A **functional interface** has one abstract method.
- A **lambda expression** provides a clean way to implement that method.
- They are heavily used in **Java Streams, Collections, and concurrency APIs**.
- Java provides many built-in functional interfaces (Predicate, Function, etc.)






---
---
---









# Types of Interfaces in Java

---
#### 1. **Normal Interfaces**

- Interfaces that declare **one or more methods**.
- Classes that implement these interfaces must **implement all declared methods**.

**Example:**

```java
interface Animal {
    void eat();
    void sleep();
}

class Dog implements Animal {
    public void eat() {
        System.out.println("Dog is eating");
    }
    public void sleep() {
        System.out.println("Dog is sleeping");
    }
}
```

---
### 2. **Functional Interfaces**
- Have **only one abstract method**.
- Can have multiple default or static methods.
- Used primarily for **lambda expressions**.

**Example:**
```java
@FunctionalInterface
interface Greetable {
    void greet(String name);
}
```

---
### 3. **Marker Interfaces**

- Contain **no methods or fields**.
- Used to **tag classes** to convey metadata or signal a special behavior to the JVM or frameworks.

Marker interfaces are often used in conjunction with runtime checks, such as the `instanceof` operator, to determine if a class has a particular "tag" and apply specific logic or operations accordingly. They provide a lightweight mechanism for categorization and can be useful in frameworks and APIs to signal special handling requirements for certain classes.

**Purpose:**
- Helps in **runtime decision making** using reflection.
- Signals to Java libraries that a class is eligible for a certain behavior (e.g., serialization).

**Example:**

```java
interface Serializable {}

class MyData implements Serializable {
    int id;
    String name;
}
```

**Common Marker Interfaces:**

- `Serializable` – Enables serialization of the class.
- `Cloneable` – Allows objects of the class to be cloned using `clone()`.
- `Remote` – Used in RMI (Remote Method Invocation) to indicate remote communication.

**Internal Working:**
- JVM uses **`instanceof` checks** or **reflection** to see if the object implements the marker interface and behaves accordingly.

---
## Serialization and Deserialization in Java

- **Serialization:** Converting an object into a byte stream to save to a file or transmit over a network.
- **Deserialization:** Reconstructing the object from the byte stream.
### Requirements:

- Class must implement `Serializable` interface.
- Use `transient` to **skip** fields that shouldn't be saved.
- The class should have a **no-arg constructor** (recommended but not mandatory).

**Example:**

```java
import java.io.*;

class Student implements Serializable {
    int id;
    String name;
    transient int age; // Will not be saved

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

public class TestSerialize {
    public static void main(String[] args) throws Exception {
        Student s = new Student(1, "Alex", 21);

        // Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
        out.writeObject(s);
        out.close();

        // Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
        Student deserialized = (Student) in.readObject();
        in.close();

        System.out.println(deserialized.id + " " + deserialized.name + " " + deserialized.age);
    }
}
```

**Output:**

```
1 Alex 0
```

- The `age` is printed as `0` because `transient` fields are **ignored** during serialization.

### Additional Notes on Serialization:
- Serialization saves only the **current state** of the object (not methods or static members).
- Fields marked `static` or `transient` are **not serialized**.
- Can be customized using `writeObject()` and `readObject()` methods in the class.

**Example use case:**
- Saving the game state
- HTTP session data persistence
- Sending objects between microservices (with RMI, Kafka, etc.)

---
### Summary

- **Normal interfaces** define method contracts.
- **Functional interfaces** support lambda expressions.
- **Marker interfaces** signal metadata (no methods).
- **Serialization** uses the `Serializable` marker to convert objects to byte streams for storage or transfer.

Let me know if you'd like to add examples for custom serialization, `Externalizable`, or versioning using `serialVersionUID`.



