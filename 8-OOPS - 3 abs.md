## 3. Abstraction

### What is Abstraction?

**Abstraction** is the process of hiding internal implementation details and showing only the essential features to the user.

In Java, abstraction is achieved through:
- **Abstract classes**
- **Interfaces**

Abstraction helps reduce complexity by hiding unnecessary details and exposing only what is relevant.

---
### Real-Life Analogy: Car

When you drive a car, you use:
- **Steering wheel**
- **Accelerator**
- **Brakes**

But you don’t need to understand the engine mechanics, internal wiring, or fuel injection system. This is **abstraction** in real life — you interact with only the necessary controls.

---
### Abstract Class in Java

An **abstract class**:
- Cannot be instantiated (no objects can be created directly)
- Can have both **abstract methods** (without body) and **concrete methods** (with implementation)
- Must be extended by a subclass that implements the abstract methods
    

```java
abstract class Car {   // abstract class
    abstract void drive();         // no implementation
    abstract void playMusic();      // no implementation

    void startEngine() {
        System.out.println("Engine started");
    }
}

class Tesla extends Car {      // concrete class
    @Override
    void drive() {
        System.out.println("Tesla is driving autonomously");
    }

    @Override
    void playMusic() {
        System.out.println("Playing music from Spotify");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Tesla(); // Upcasting
        myCar.startEngine();
        myCar.drive();
        myCar.playMusic();
    }
}
```

### Output:

```
Engine started
Tesla is driving autonomously
Playing music from Spotify
```


The methods `drive()` and `playMusic()` have **no implementation here** — they’re abstract. This is the **abstraction**:

- You’re **declaring what must be done**, not **how** it’s done.

- You are **hiding the internal logic** of these actions and deferring their actual behavior to subclasses.

---
### Key Rules of Abstract Classes

- You **cannot create objects** of an abstract class.
    
    ```java
    Car c = new Car(); // Error: Cannot instantiate the type Car
    ```
    
- A class becomes abstract if it contains at least one abstract method.
- ##### Subclasses must **override all abstract methods** unless they are also abstract.
- Abstract classes can have **constructors**, but they are only called when an object of a subclass is created.

---
### Why Use Abstraction?

- To **hide implementation logic** and expose only necessary operations.
- To **enforce a contract** for subclasses.
- To reduce complexity and **improve maintainability**.
- Helps achieve **loose coupling** and **high cohesion**.

---
### Summary Table

|Concept|Description|
|---|---|
|Abstraction|Hides internal logic; shows only essential details|
|Abstract Class|Can have both abstract and concrete methods|
|Cannot Instantiate|Cannot create objects of abstract class|
|Interface|100% abstraction (only method declarations, no body)|
|Purpose|Security, flexibility, cleaner code, enforce standardization|

---

If you’re working in a team:
- Senior devs define **abstract base classes** with the required methods.
- Junior devs **implement** them without worrying about the bigger system.
- Everyone follows a consistent, **safe structure**.

|Benefit|Explanation|
|---|---|
|Reduces Complexity|You work with a simplified view of the system|
|Improves Code Maintainability|You can change internals without affecting outside code|
|Promotes Reusability|Abstract base classes/interfaces can be reused across projects|
|Enables Polymorphism|You can treat multiple types through a common interface|
|Encourages Clean Architecture|Helps build layered systems — UI ↔ Service ↔ Repository|





---
---
---
---





## Interfaces:

---
### What is an Interface?

An **interface** in Java is a **contract** that specifies **what** a class should do, but **not how**. It only contains method **signatures** (no implementation) and **constants**. Any class that implements an interface must provide implementations for all of its methods.

---
### Syntax

```java
interface Vehicle {
    void start();   // abstract method
    int wheels = 4; // implicitly always public, static, and final
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
}
```

---

### Key Features of Interfaces

- All methods are **implicitly public and abstract** (until Java 7).
- All variables are **public, static, and final**.
- A class uses `implements` to use an interface.
- A class can implement **multiple interfaces** (unlike single class inheritance).
- Interfaces support **multiple inheritance**.
    
- From Java 8+, interfaces can also have:
    
    - **default methods** (with a body)
    - **static methods**
        
- From Java 9+, interfaces can have **private methods** (used inside default/static).
    
---
### Example with Default Method (Java 8+)

```java
interface Camera {
    void click();

    default void record() {
        System.out.println("Recording in HD");
    }
}

class Phone implements Camera {
    public void click() {
        System.out.println("Photo clicked");
    }
}
```

---
### Why Use Interfaces?
- To achieve full **abstraction**.
- To provide a **common behavior** across unrelated classes.
- To simulate **multiple inheritance**.
    
---
### Multiple Interfaces Example

```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class AllInOne implements Printer, Scanner {
    public void print() {
        System.out.println("Printing document");
    }
    public void scan() {
        System.out.println("Scanning document");
    }
}
```

If two interfaces have **methods with the same name, return type, and arguments**, it’s **not a problem**. The implementing class just overrides **one method** — which satisfies both interfaces.

---
### Interface vs Abstract Class

|Feature|Interface|Abstract Class|
|---|---|---|
|Methods|Only abstract (till Java 7), default, static|Abstract + concrete methods|
|Variables|public static final only|Instance or static|
|Inheritance|Multiple|Single|
|Constructors|❌ Not allowed|✅ Allowed|
|Access Modifiers|Only public|Any (private, protected, etc.)|

---

### Final Notes

- Interfaces are ideal when **multiple classes share common behavior** but don't form a strict class hierarchy.
    
- Interfaces support loose coupling and flexible architecture.
    
- Use interfaces when you need to enforce a common **API/contract** across multiple classes.



---
---



### Java Inheritance Types and Interface Extension

---
### 1. **Extending Interfaces**

Interfaces in Java can extend one or more other interfaces using the `extends` keyword. The child interface inherits all method declarations from the parent interfaces.
#### Example:

```java
interface A {
    void methodA();
}

interface B {
    void methodB();
}

interface C extends A, B {
    void methodC();
}

class MyClass implements C {
    public void methodA() {
        System.out.println("A method");
    }
    public void methodB() {
        System.out.println("B method");
    }
    public void methodC() {
        System.out.println("C method");
    }
}
```

#### Explanation:

- Interface `C` extends interfaces `A` and `B`, so it inherits both `methodA()` and `methodB()`.
    
- `MyClass` implements `C`, so it must implement **all** three methods: `methodA()`, `methodB()`, and `methodC()`.
    
- At runtime, you can treat `MyClass` as a `C`, `A`, or `B` because it conforms to all of them.
    
---

### 2. **Types of Inheritance in Java**

#### A. **Class to Class Inheritance**

- Achieved using `extends`
- Only **single inheritance** is allowed with classes to avoid the diamond problem
    

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}

class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // Accessing parent class method
        d.bark();  // Accessing own method
    }
}
```

#### Explanation:

- `Dog` extends `Animal` and inherits `eat()` method.
- `Dog` can use its own methods and all accessible methods of `Animal`.
- You cannot extend more than one class at the same time.

---

#### B. **Class to Interface Implementation**

- A class uses `implements` to adopt an interface
- Must implement **all** interface methods unless it is an abstract class
    

```java
interface Movable {
    void move();
}

class Robot implements Movable {
    public void move() {
        System.out.println("Robot moving");
    }
}

class Main {
    public static void main(String[] args) {
        Movable m = new Robot();
        m.move();  // Calls move() from Robot
    }
}
```

#### Explanation:

- Even though `Movable` doesn't define how `move()` works, it forces any class that implements it to define the method.
    
- You can also use the interface type (`Movable`) as a reference, enabling polymorphism.
    

---

#### C. **Interface to Interface Inheritance**

- One interface can `extend` one or more interfaces
- The child interface inherits all abstract method signatures from parent interfaces

```java
interface Engine {
    void start();
}

interface AdvancedEngine extends Engine {
    void turboBoost();
}

class SportsCar implements AdvancedEngine {
    public void start() {
        System.out.println("Engine started");
    }
    public void turboBoost() {
        System.out.println("Turbo boost activated");
    }
}
```

#### Explanation:

- `AdvancedEngine` inherits `start()` from `Engine`.
- `SportsCar` implements `AdvancedEngine`, so it must define both `start()` and `turboBoost()`.
- This allows a deeper inheritance chain among interfaces while still keeping things abstract.
    

---
#### Summary Table

|Inheritance Type|Keyword|Multiple Allowed?|Example Syntax|Explanation|
|---|---|---|---|---|
|Class to Class|extends|❌|`class B extends A`|One parent class only|
|Class to Interface|implements|✅|`class C implements I1, I2`|Must implement all interface methods|
|Interface to Interface|extends|✅|`interface C extends I1, I2`|Inherits all abstract methods|





---
---
---




### Real-Life Example of Interfaces in Java

Let's take a **real-world example of a Payment System**.

---
### Scenario:

You are building an **e-commerce platform** that supports multiple payment methods: Credit Card, PayPal, UPI, etc.

All payment methods should:
- Authorize a transaction
- Make the payment
- Generate a receipt
    
These operations are the **contract** that each payment method must follow, but their **implementation details will differ**.

---
### Step 1: Define the Interface

```java
interface PaymentMethod {
    void authorize();
    void pay(double amount);
    String getReceipt();
}
```

This interface represents the common behavior for any payment type.

---
### Step 2: Implementing the Interface

#### Credit Card Payment

```java
class CreditCardPayment implements PaymentMethod {
    public void authorize() {
        System.out.println("Authorizing credit card...");
    }

    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " using Credit Card.");
    }

    public String getReceipt() {
        return "Receipt: Credit Card Payment";
    }
}
```

#### PayPal Payment

```java
class PayPalPayment implements PaymentMethod {
    public void authorize() {
        System.out.println("Logging into PayPal account...");
    }

    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " via PayPal.");
    }

    public String getReceipt() {
        return "Receipt: PayPal Payment";
    }
}
```

---
### Step 3: Use in Main Program

```java
public class PaymentProcessor {
    public static void main(String[] args) {
        PaymentMethod method1 = new CreditCardPayment();
        PaymentMethod method2 = new PayPalPayment();

        method1.authorize();
        method1.pay(1500);
        System.out.println(method1.getReceipt());

        System.out.println("---");

        method2.authorize();
        method2.pay(700);
        System.out.println(method2.getReceipt());
    }
}
```

---

### Explanation

- `PaymentMethod` is the **interface** that defines the contract.
- `CreditCardPayment` and `PayPalPayment` **implement** the interface in their own way.
- The `PaymentProcessor` class uses the interface as a **reference type**, enabling **polymorphism**.
### Why Use an Interface Here?

- You can easily add more payment types (e.g., UPI, Bank Transfer) by just creating new classes that implement the same interface.
    
- No need to modify existing code — open/closed principle.
    
- Promotes loose coupling, maintainability, and scalability.
    
---

