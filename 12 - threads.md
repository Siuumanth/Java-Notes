## **Threads in Java 

### **1. What is a Thread in Java?**

- A **thread** in Java is a lightweight **sub-process** or **smallest unit of execution** within a program.
    
- Each thread has its **own execution path**, but **shares the same memory** and resources with other threads in the same process.
    
- Java provides built-in support for multithreading, making it easy to write programs that perform multiple tasks simultaneously.

Think of a thread as a **single line of work** — like one person working independently but using the same office files.

---
### **2. What is Multithreading?**

- **Multithreading** is the concurrent execution of two or more threads.
    
- In Java, this means writing programs where **multiple threads can run concurrently**, each performing different tasks (or even working together on the same task).
    
- Java achieves multithreading via the **`java.lang.Thread` class** and the **`Runnable` interface**.
    
- The Java Virtual Machine (JVM) manages thread **scheduling**, **priorities**, and **execution** using the underlying OS.

---
### **3. Key Concepts Related to Threads**

#### a) **Concurrency vs Parallelism**

- **Concurrency**: Multiple threads are in progress at the same time, but **not necessarily executing simultaneously**. They can share CPU time (time slicing).
    
- **Parallelism**: Threads **run at the exact same time**, typically on **multiple CPU cores**.

#### b) **Thread Lifecycle States**

1. **New**: Thread is created but not yet started.
2. **Runnable**: Ready to run but waiting for CPU time.
3. **Running**: Actively executing.
4. **Blocked/Waiting**: Waiting for some resource or signal.
5. **Terminated**: Execution completed or aborted.

---
### **4. Why Use Multithreading in Java? (Real-life Use Cases)**

#### a) **Responsive Applications (like UI)**
- In GUI-based programs (e.g., JavaFX or Swing), keeping the interface responsive while doing background tasks like file I/O or downloading data.
#### b) **Web Servers**
- Handling thousands of client requests — each connection is often handled by a separate thread (e.g., Tomcat, Jetty).
#### c) **Parallel Processing**
- Performing tasks like image processing, data parsing, machine learning computations faster by splitting into subtasks handled by multiple threads.
#### d) **Gaming**
- One thread for rendering graphics, another for user input, another for physics simulation.
#### e) **Real-time Systems**
- Systems like air traffic control or automotive embedded systems require tasks to happen concurrently within strict time limits.

---
### **5. Time Sharing in Threads**

- **Time sharing** is a scheduling concept where multiple threads are **given small slices of CPU time in turns**.
    
- The **OS or JVM scheduler** switches between threads so fast that it appears they are running simultaneously (on a single-core CPU).
    
- This prevents any single thread from **hogging CPU** and allows fair CPU access to all threads.
    
> Example: In a video call app, the CPU rapidly switches between threads for camera input, microphone input, video processing, and network streaming.

---
### **6. Advantages of Using Threads in Java**

- **Better resource utilization**: Especially on multi-core processors.
- **Improved application performance**: By avoiding blocking.
- **Simplified program structure**: Helps break complex tasks into manageable concurrent subtasks.
- **Responsiveness**: Keeps programs interactive, especially important for GUI or server-based applications.

---
### **7. Challenges in Multithreading**

- **Race conditions**: When two threads access shared data simultaneously leading to inconsistent results.
- **Deadlocks**: Threads waiting on each other to release resources.
- **Thread safety**: Code must be written to avoid problems when multiple threads access shared memory.
- **Complexity**: Debugging multithreaded applications is harder.








---
---
---







## Java multithreading example 

---
### Theory

In Java, when you extend the `Thread` class and override the `run()` method, you define what the thread will do when it runs. To actually start the thread in a separate path of execution, you must call the `start()` method. This creates a new call stack and executes the `run()` method in a new thread.

If you instead call `run()` directly, it behaves like a normal method call — the code will run sequentially in the same thread (usually the main thread).

This program demonstrates the difference by creating two classes, `A` and `B`, that both print a message five times. When `.start()` is used, their outputs can overlap due to concurrent execution.

---
### Code

```java
class A extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread A: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        // sleep throws a checked exception so in try catch  
        }
    }
}

class B extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread B: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.start();  // starts a new thread
        b.start();  // starts another thread
    }
}
```

---

### sample output

```
Thread A: 1
Thread B: 1
Thread A: 2
Thread B: 2
Thread A: 3
Thread B: 3
Thread A: 4
Thread B: 4
Thread A: 5
Thread B: 5
```

Note: The exact order may vary depending on the thread scheduler. Sometimes `Thread A` might print multiple lines before `Thread B` catches up, and vice versa.

---


## Scheduler:

In Java, the **thread scheduler** is a part of the Java Virtual Machine (JVM) that decides **which thread runs at what time**. It is responsible for managing **time slicing** (where each thread gets a small time to execute) and **thread priority** (where higher-priority threads may get preference). However, the actual behavior of the scheduler is **heavily dependent on the underlying operating system**, meaning Java gives only limited control over it.

When you call `.start()` on a thread, you're essentially **requesting the scheduler** to run that thread. The scheduler may choose to start it immediately, delay it, or interleave it with other running threads. This is why thread output can vary between runs — the scheduler dynamically decides execution order based on system load, priority, and CPU availability.

You **can influence** the scheduler _slightly_ using the `setPriority(int priority)` method (values range from 1 to 10), but it is only a **suggestion** to the scheduler — not a guarantee. True thread scheduling is **not deterministic or manually controlled** in standard Java, and there is **no way to force exact execution order** unless you implement custom synchronization or control mechanisms (like using `join()`, semaphores, or `ExecutorService` for managed thread pools).


### Thread priority in java

---
### theory

In Java, each thread has a **priority**, an integer value between 1 (MIN_PRIORITY) and 10 (MAX_PRIORITY), with the default being 5 (NORM_PRIORITY). The thread scheduler may use these priorities as hints to decide which thread to run first, but this is **not guaranteed**, as the behavior depends on the JVM and the operating system.

Setting higher priority **can** increase the chances of that thread getting more CPU time, but it doesn't ensure it. In real-world applications, relying on priority is discouraged for thread coordination.

---
### Modified code with priorities

```java
class A extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread A: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

class B extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread B: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class ThreadPriorityExample {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.setPriority(Thread.MIN_PRIORITY);  // Priority = 1
        b.setPriority(Thread.MAX_PRIORITY);  // Priority = 10

        a.start();
        b.start();
    }
}
```

---

### explanation of difference

- In this modified version, we explicitly set the priorities of the threads using `setPriority()`.
- `Thread A` has the **lowest priority** (1), while `Thread B` has the **highest priority** (10).
- This may cause `Thread B` to execute more often or earlier than `Thread A`, but it is **not guaranteed**.
### sample output (may vary)

```
Thread B: 1
Thread B: 2
Thread A: 1
Thread B: 3
Thread A: 2
Thread B: 4
Thread A: 3
Thread B: 5
Thread A: 4
Thread A: 5
```

Again, this output can differ between runs or systems — priorities act more like **suggestions** than strict rules for execution order.

---


### Follow-up: Why doesn't `Thread B` always execute first even with higher priority?

Setting a thread’s priority in Java using `setPriority()` is **only a suggestion to the JVM and OS scheduler** — it does **not guarantee** any specific execution order.

Here’s why `Thread B` (even with `MAX_PRIORITY`) **may not start first** or **run more often**:

---
### 1. **Thread Scheduling is OS-Dependent**

- Java's `Thread` priority is passed to the underlying **operating system scheduler**.
- On many modern operating systems (like Windows, Linux, macOS), **user-level thread priorities are either ignored or loosely respected**.
- These OSes use **time-sharing preemptive scheduling**, where all threads are given fair CPU slices regardless of priority, unless real-time scheduling is used (which Java doesn't use by default).

---
### 2. **start() Only Requests Execution**

- When you call `.start()`, the thread is put into the **`Runnable` state**, but the **JVM doesn't run it immediately**.
- Whether `a` or `b` starts first depends on **timing**, **thread queue state**, and **CPU load** — all of which are out of your control.

---
### 3. **Priority is Not a Deterministic Control**

- Even with `b.setPriority(10)`, the JVM might decide to start `Thread A` first.
- In fact, on most systems, unless you're doing CPU-intensive tasks, **both threads end up getting nearly equal CPU time** due to the fairness of the OS-level scheduler.
    
---
### Conclusion

Java thread priority is **best-effort only**. It's useful when you want to _suggest_ that some thread is more important than another (e.g., background logging vs. UI animation), but it is **not a reliable way to enforce execution order**. For deterministic behavior, you should use `join()`, semaphores, locks, or executor frameworks.







---
---
---






## Runnable Reference and Class Object Relationship

---
### What is Runnable and How It Relates to Thread

In Java, `Runnable` is a **functional interface** in the `java.lang` package. It represents a task that can be executed by a thread. The interface has only one method:

```java
void run();
```

To create a thread in Java, you can either:

1. **Extend the `Thread` class** and override its `run()` method, or
2. **Implement the `Runnable` interface** and define the `run()` method

The second approach is often preferred because it allows the class to **extend another class** (since Java supports only single inheritance) and promotes **separation of concerns** — the task logic is defined separately from thread management.

Once you implement `Runnable`, you pass an object of that class to a `Thread` constructor, which will call the `run()` method when you start the thread.

---
### Theory

In Java, when a class implements an interface (like `Runnable`), you can assign an object of that class to a reference of the interface type. This is because the object "is-a" Runnable — it fulfills the contract of the `Runnable` interface by implementing its `run()` method.

This is known as **polymorphism**: the ability to refer to an object using a reference of its interface or superclass type.

---
### Example

```java
class TaskA implements Runnable {
    public void run() {
        System.out.println("Running TaskA");
    }
}

public class Main {
    public static void main(String[] args) {
        Runnable obj = new TaskA(); // Allowed due to interface implementation
        Thread thread = new Thread(obj); // Thread accepts Runnable
        thread.start();
    }
}
```

---
### Explanation

- `TaskA` implements the `Runnable` interface, so any object of `TaskA` is considered a `Runnable`.
    
- `Runnable obj = new TaskA();` means:
    - `obj` is a **reference of type Runnable**.
    - `new TaskA()` is an **object of class TaskA**, which implements `Runnable`.
        
- This allows you to pass `obj` to the `Thread` constructor because `Thread` expects a `Runnable`.

This design allows for flexibility:
- You can easily swap `TaskA` with any other class that implements `Runnable` without changing the rest of your code.

---
### Benefit

This is a clean way to **decouple task logic from thread creation**, and it supports **interface-based programming**, which is a core principle of Java's object-oriented design.

---
### Runnable Using Lambda Expression

Since `Runnable` is a **functional interface** (it has only one method: `run()`), you can use a **lambda expression** to define the `run()` method directly inside the `main` method.

This provides a very concise way to create threads without defining a separate class.

---
### Example with Lambda

```java
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Task: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
```

---
### Explanation

- `Runnable task = () -> { ... };` creates an anonymous implementation of `Runnable` with a `run()` method.
- This avoids the need to create a separate `Runnable` class.
- It is useful when the logic is short and used only once.
    
---
### Conclusion

Using lambda expressions for `Runnable` is a concise and modern way of defining thread tasks, especially useful for small tasks or one-time thread creation. It aligns with Java's move towards more functional programming practices.










---
---
---

