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

### Example 2: Implementing an Interface Anonymously

```java
interface ButtonClickListener {
    void onClick();
}

public class Main {
    public static void main(String[] args) {
        ButtonClickListener listener = new ButtonClickListener() {
            @Override
            public void onClick() {
                System.out.println("Button clicked!");
            }
        };

        listener.onClick();
    }
}
```

---

### Features of Anonymous Inner Class

- Can only be used **once** (single-use class)
    
- Cannot have a constructor
    
- Automatically extends the class or implements the interface it is based on
    
- Must override **all abstract methods** (if implementing an interface)
    

---

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