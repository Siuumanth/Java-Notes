Objects are created by JVM, we just need to worry about designing the class.

```java
Class obj = new Class();

obj.variable;
obj.function();

```

### How JVM & JRE help implement OOP in Java:

- **JVM (Java Virtual Machine)** loads `.class` files and **creates objects** at runtime using `new`. It manages **memory (heap, stack)** where objects and method calls live.
    
- **JRE (Java Runtime Environment)** includes the JVM + core libraries that provide OOP features like **classes, inheritance, polymorphism, interfaces**, etc.
    
- When you write `new ClassName()`, the **JVM allocates memory** for the object, sets up the **constructor**, and handles **method dispatch** (including overriding and dynamic binding).
    
- JVM ensures **encapsulation** (via access modifiers), **inheritance** (by linking class hierarchies), and **polymorphism** (via virtual method tables at runtime).


---

Every method will have its own stack, main, class method.
### Stack in Java (per-thread call stack):

Each method call in Java gets its own **stack frame**, which contains:
1. **Method parameters**
2. **Local variables**
3. **Return address**
4. **Intermediate values (during expression evaluation)**

Each thread has its own **call stack**, and **each method call** adds a new **frame** to the top of that stack.

Only the call stack of methods is FIFO, but not the stack meant for all individual methods.e Variables in a method are **not stored in the stack like LIFO data**, but they live in a **stack frame**, which is part of the **call stack** — and **that** call stack _is_ LIFO.

---

## 🔷 Types of Variables in Java

### 1. **Local Variables**
- **Declared inside methods, constructors, or blocks**
- Exist only **during method execution**
- Stored in: **Stack**
- Managed inside the **stack frame** of the method
- Accessed via **local variable table (indexed slots)**
#### Example:

```java
void foo() {
    int a = 10;  // Local variable
}
```

- `a` is stored in the **stack frame** for `foo()`, and is destroyed when `foo()` ends.
    
---

### 2. **Instance Variables (a.k.a. Non-static fields)**

- **Declared inside a class but outside any method**
- Exist as long as the **object** exists
- Stored in: **Heap**
- Each object has its own copy
#### Example:

```java
class MyClass {
    int x;  // instance variable
}
```

- When you create an object with `new MyClass()`, space for `x` is allocated on the **heap**.

---
### 3. **Static Variables (Class variables)**

- Declared with the `static` keyword
- Belong to the **class**, not objects
- Stored in: **Method Area (part of JVM memory)**
- Only one copy per class, shared among all objects
#### Example:

```java
class MyClass {
    static int count = 0; // static variable
}
```

---
#### 📌 Summary Table

|Variable Type|Declared In|Lifetime|Stored In|Scope|
|---|---|---|---|---|
|Local Variable|Inside methods/blocks|Until method returns|Stack (frame)|Method/block only|
|Instance Variable|Inside class (non-static)|As long as object lives|Heap|Per object|
|Static Variable|Inside class (static)|Entire program run|Method Area|Shared in class|

#### 🔸 Static Variables, Methods, and Blocks in Java Classes

In Java, the `static` keyword is used for **class-level members** — meaning they belong to the **class itself**, not to any specific object.

---

### 🔹 1. Static Variables (Class Variables)

- Declared with the `static` keyword inside a class but **outside methods**.
- Shared by **all instances** of the class.
- Useful when a value is **common across all objects** (e.g., an object counter).
    

```java
class Student {
    static int count = 0;   // static variable, all objects will share same

    Student() {
        count++;
    }
}
```

Each time a new `Student` is created, `count` increments. All objects share the same `count`.

---
### 🔹 2. Static Methods

- Can be called **without creating an object**.
- Can **only access static variables and other static methods** directly.
- Cannot use `this` or access non-static (instance) members.

```java
class MathUtil {
    static int square(int x) {
        return x * x;
    }
}

// Usage:
int result = MathUtil.square(5);
```

---

### 🔹 3. Static Blocks (Static Initialization Block)

- Executes **once** when the class is **loaded into memory**, before any object is created.
- Used to **initialize static variables** or run class-level setup.
- Used to initialize static variables.
    

```java
class Demo {
    static int x;

    static {
        x = 100;
        System.out.println("Static block executed");
    }
}
```

When the `Demo` class is loaded, the static block is executed exactly once.

---

####  Summary Table

| Feature               | Static Variable            | Static Method | Static Block                  |
| --------------------- | -------------------------- | ------------- | ----------------------------- |
| Belongs to            | Class                      | Class         | Class                         |
| Access via            | Class name or object       | Class name    | Automatically runs on load    |
| Access instance vars? | ❌ No (without object)      | ❌ No          | ❌ No                          |
| When runs             | When class loaded          | When called   | Once when class loads         |
| Usage example         | Shared data across objects | Utility logic | Complex static initialization |

---

### 🔹4. Static Methods and Their Behavior in Java

Static methods are **class-level functions** — they are not tied to any object and can be called **without creating an instance**.

##### Why It Is Used

- Can be called **without creating an object**.
- Used for **utility or helper functions** (e.g., `Math.sqrt()`).
- Saves memory by avoiding object creation.
- Can only access **static variables and static methods** directly.

---
### 🔹 Key Properties of Static Methods

1. **Belongs to the Class**
    - Declared using the `static` keyword.
    - Called using the class name (e.g., `MyClass.myMethod()`).
        
2. **Cannot Access Non-Static Members Directly**
    - Static methods **cannot use instance variables or instance methods** directly.
    - Because static methods run without any object context (`this` is not available).
        
3. **Can Access Only Static Members Directly**
    - They can read or modify only:
        - Static variables
        - Other static methods
            
4. **Main Method is Static**
    ```java
    public static void main(String[] args) { ... }
    ```
    - This is because Java needs to run `main()` without creating an object of your class.
    
---
##### 🔹 Example of Static Method Rules

```java
class Test {
    int a = 10;
    static int b = 20;

    static void show() {
        // System.out.println(a);  ❌ Error: 'a' is non-static
        System.out.println(b);    // ✅ OK: 'b' is static
    }
}
```

---
#### 🔹 Why Static Methods Exist

- For **utility methods** that don’t need object data.
- For **shared behavior**, like math  functions or configuration access.
- Avoids unnecessary object creation.

---
#### 🔹 Summary Table: Static Members Recap

|Aspect|Static Variable|Static Method|Static Block|
|---|---|---|---|
|Belongs to|Class|Class|Class|
|Memory allocation|Once (shared)|Once|Once|
|Object required?|❌ No|❌ No|❌ No|
|Can access instance data?|❌ No|❌ No|❌ No|
|When it runs|On class load|When called|On class load|

---
### 🧠 Bonus Notes:

- **Stack memory** is fast and small. Holds method calls and local data.
- **Heap memory** is bigger, slower, and used for dynamic allocation (`new` objects).
- Local variables are thread-safe because each thread gets its **own stack**.
- Instance/static variables must be synchronized explicitly for thread-safety.




---
---
---



### How are classes and objects handled in memory.

Objects in Java are created in **heap memory**.  
Each object **contains only instance fields (data)**.  
**Methods are not stored inside each object** — they are stored **once** in the **Method Area** as part of the class definition.

When you create an object:

```java
MyClass obj = new MyClass();
```

- In the **main method's stack frame**, a **reference variable (`obj`)** is created.
- This reference holds the **memory address** of the object in the **heap**.

---

#### 🔄 Accessing Object Attributes:

When you access something like `obj.x`:
1. JVM looks up `obj` in the **stack**, finds the **heap address**.
2. It **jumps to that address** in the heap.
3. It reads the **value of the field `x`** from that object's instance field section.
4. If you call `obj.method()`, it uses the **reference to the class metadata** (in the Method Area) to execute the method.

---
#### 🔍 Summary:

|Part|Stored In|Description|
|---|---|---|
|Object instance fields|Heap|Unique data per object|
|Object reference (`obj`)|Stack (main frame)|Points to object in heap|
|Methods|Method Area|One shared copy per class, not per object|

---

This is how Java efficiently separates **object data (heap)** and **code logic (method area)**, while using the **stack** for tracking references and local variables.
