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
