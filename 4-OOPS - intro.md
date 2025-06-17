###  What is OOPs?

**Object-Oriented Programming (OOP)** is a programming approach where the focus is on creating reusable, self-contained units called **objects**. These objects represent components of a system — like users, files, or messages — and each object groups or encapsulates together its own **data** and the **actions** that can be performed on that data. Instead of writing code as a list of functions, OOP structures your program like a system of interacting elements, making it easier to model real-world systems, reduce repetition, and manage complexity.

Imagine you’re designing a simulation of a school. You wouldn’t write separate functions like `assign_marks`, `track_attendance` randomly. Instead, you'd create objects like `Student`, `Teacher`, and `Classroom`, each with their own data and actions. That’s OOP.

---

### 🔑 Core Concepts of OOP

#### 1. **Encapsulation** – _"Put related things together"_

It means wrapping data and methods that work on that data into a single unit (object), and restricting direct access to some of the object's components. Think of it as creating a black box: you interact with the object using public methods, but its internal state is hidden and protected.

Example analogy: You use a washing machine by pressing buttons — you don’t (and shouldn’t) mess with its wires and internal parts.

---
#### 2. **Abstraction** – _"Hide unnecessary details"_

You should only expose relevant parts of an object to the outside world and hide the rest. The idea is to reduce complexity by focusing on what an object **does**, not **how** it does it.

Analogy: When you drive a car, you know how to steer and use pedals. You don’t care how the engine processes fuel — that’s abstracted away.

---
#### 3. **Inheritance** – _"Is-a" relationship_

This allows one class or object to inherit properties and behaviors from another. It helps with **code reuse** and creating a natural hierarchy.

Analogy: A Dog **is a** type of Animal. So Dog can inherit general behaviors like `eat` or `sleep` from Animal and also have specific ones like `bark`.

---
#### 4. **Polymorphism** – _"Same action, different behaviors"_

Polymorphism lets you use the same action or method name but have different outcomes depending on the object involved.

Analogy: When you press a button labeled “Start” on different machines — washing machine vs microwave — the result is different, but the action looks the same to you.

---

### 🧠 Why is OOP useful?

- **Organizes code** to be modular, clean, and easier to understand.
- **Mimics real-world systems**, so it's easier to model complex behavior.
- **Reduces redundancy** through inheritance and reuse.
- **Improves scalability and maintenance**.

---
### 🔒 Java Access Specifiers – Focus on Methods and Variables

In Java, **access specifiers** (also called access modifiers) **control the visibility/scope** of _classes, variables (fields), methods,_ and _constructors_. The four main types are:

| Modifier    | Class | Package | Subclass | Outside |
| ----------- | ----- | ------- | -------- | ------- |
| `private`   | ✅     | ❌       | ❌        | ❌       |
| (default)   | ✅     | ✅       | ❌        | ❌       |
| `protected` | ✅     | ✅       | ✅        | ❌       |
| `public`    | ✅     | ✅       | ✅        | ✅       |

---

#### 1. `private` – Strict Encapsulation

- **Visible only inside the same class.**
- Common for **class fields**, to enforce _data hiding_.
- Not accessible in subclasses or even in the same package.
    

```java
public class BankAccount {
    private double balance;

    private void updateBalance(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;  // access through public getter
    }
}
```

> ✅ Use `private` for internal logic and sensitive data  
> ❌ Cannot be inherited or accessed outside

---

#### 2. _(default)_ – Package-Private (No keyword)

- If no access modifier is written, it's **package-private**.
- **Accessible only within the same package**.
- Not accessible in subclasses from different packages.

```java
class Student {
    int marks; // default access

    void printMarks() {
        System.out.println(marks);
    }
}
```

> Useful for **helper classes or methods** that are used only within the same module/package.

---

#### 3. `protected` – Inheritance-Oriented Access

- **Accessible within the same package**, and **in subclasses (even outside the package)**
- Good for methods meant to be **overridden**.

```java
public class Animal {
    protected String name;

    protected void speak() {
        System.out.println("Animal speaks");
    }
}

public class Dog extends Animal {
    void bark() {
        speak();  // ✅ allowed
        System.out.println(name);  // ✅ allowed
    }
}
```

> ✅ Use `protected` when you want subclasses to **inherit and modify behavior**  
> ❌ Not accessible by unrelated classes in other packages

---

#### 4. `public` – Fully Open

- **Accessible from anywhere**, including outside the package.
- Typically used for **APIs, utility methods, main methods**.

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

> ✅ Use for code meant to be reused publicly  
> Be cautious with data fields—**avoid public fields** unless constants.

---

### 🔁 Summary: Best Practices for Fields and Methods

- **Fields (Variables):**
    - `private` + `public` getter/setter ⇒ **Encapsulation**
    - Avoid `public` fields unless `static final` constants
    - `protected` fields are okay in inheritance-heavy OOP
        
- **Methods:**
    - `public` for services, API methods, entry points (`main`)
    - `protected` for extensible or override-able methods
    - `private` for internal logic only
        
---
### ❗ Special Notes:
- `private` methods cannot be overridden.
- Constructors can also be made `private` (used in **Singleton pattern**).
- Top-level classes can only be `public` or default (no `protected/private` allowed).

---

### Getters and Setters:

Used to get and update values of private variables, it is a normal method which can be used, a good practice is ` get + varname`, example  `getAge() and setAge()`.

---

Got it man — here's the same note with **cleaner formatting** and **smaller heading sizes** for better readability.

---

### `this` Keyword in Java

#### What is `this`?

`this` is a **reference variable** in Java that refers to the **current object** — the object whose method or constructor is executing.

You typically use it to:

- Differentiate between instance variables and parameters.
- Call other constructors from a constructor.
- Pass the current object as an argument.
- Return the current object from a method.

---

#### 1. Resolving Variable Shadowing (Instance vs Local)

When a method or constructor has a parameter with the same name as an instance variable, `this` clears the ambiguity.

```java
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;  // 'this.name' is instance variable, 'name' is parameter
    }

    public void display() {
        System.out.println(this.name);
    }
}
```

Without `this`, `name = name` would just assign the parameter to itself.

---
#### 2. Calling Another Constructor

You can use `this()` to invoke another constructor of the same class — must be the **first statement** in the constructor.

```java
public class Rectangle {
    int width, height;

    Rectangle() {
        this(10, 20);  // calls the other constructor
    }

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
```

This is useful to avoid repeating code across constructors.

---
#### 3. Passing Current Object as an Argument

Sometimes you need to pass the current object to another method or constructor — for example, in callbacks or chaining.

```java
class A {
    void show(A obj) {
        System.out.println("Object received");
    }

    void callShow() {
        show(this);  // pass current object
    }
}
```

---
#### 4. Returning Current Object

Useful in **method chaining** — you return `this` so that multiple calls can be chained together.

```java
class Builder {
    String value = "";

    Builder add(String str) {
        this.value += str;
        return this;
    }
}

Builder b = new Builder();
b.add("Hello").add(" World").add("!");
```

---

## 🔹 Constructors in Java

A **constructor** is a special method used to **initialize objects**. It's called automatically when an object is created using `new`.

#### Key Points:
- **Same name** as the class.
- **No return type** (not even `void`).
- Can be **parameterized** or **no-arg**.
- If no constructor is written, Java provides a **default constructor**.

---

#### ✅ Example: No-arg Constructor

```java
class Student {
    Student() {
        System.out.println("Student created");
    }
}
```

---

#### ✅ Example: Parameterized Constructor

```java
class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}
```

---

#### 🔄 Constructor Overloading

You can define multiple constructors with different parameters.

```java
class Box {
    int length, width;

    Box() { this.length = this.width = 0; }
    Box(int l, int w) { this.length = l; this.width = w; }
}
```

---

#### 🔁 Calling One Constructor from Another

Use `this()` to call another constructor in the same class.

```java
Box() {
    this(10, 10);  // calls parameterized constructor
}
```

---

### Reference vs Anonymous Object

- **Reference Object**: Created and stored in a variable.

```java
Student s = new Student("John");
s.toString(); // can be reused
```

- **Anonymous Object**: Created without assigning to a variable, used immediately.

```java
new Student("John").toString(); // cannot be reused
```

Anonymous objects are useful for one-time use, like passing to a method.

---
