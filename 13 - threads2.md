
## Mutation, Threads, and Race Condition in Java

---
### Mutation

**Mutation** refers to the act of **changing the state** of an object or variable after it has been created. In Java, this typically means modifying the values of fields in an object.

For example:

```java
int x = 10;
x = 20; // mutation of variable x
```

Mutable objects can lead to unexpected behavior in multithreaded environments if multiple threads access and modify them simultaneously.

---



### Threads and Shared Data

When multiple threads access **shared mutable data**, problems can occur if they are not properly synchronized. Each thread has its own execution path but can access shared variables in memory.

If one thread is **mutating** a shared variable while another is **reading or writing** to it simultaneously, the program can enter an inconsistent state.

---
### Race Condition

A **race condition** occurs when the outcome of a program depends on the **timing or order** of threads' execution. It happens when:

1. Two or more threads access shared data
2. At least one thread modifies the data
3. There's no proper synchronization (like `synchronized` blocks or locks)

This leads to unpredictable behavior such as:
- Incorrect values
- Lost updates
- Crashes

---
### Example Scenario

If two threads increment the same counter:

```java
counter++; // not atomic
```

This actually involves multiple steps: read, increment, write. If two threads do this at the same time, they might both read the same value and overwrite each other’s result.

---
### Preventing Race Conditions

Use one of the following:
- `synchronized` keyword
- Locks (from `java.util.concurrent.locks`)
- Atomic classes (e.g., `AtomicInteger`)
- Thread-safe collections

Proper synchronization ensures that only one thread can mutate shared data at a time.

---

- **Mutation**: Changing state after creation
- **Threads**: Independent paths of execution
- **Race Condition**: Conflict due to unsynchronized access to shared mutable data
    
Managing shared data correctly is critical to writing safe and reliable multithreaded programs.



---



## How to make threads safe?

### Thread Safety Techniques in Java

---
### What is Thread Safety?

**Thread safety** means that shared data remains in a consistent and expected state when accessed by multiple threads concurrently. A thread-safe program prevents race conditions, data corruption, or unexpected behavior.

---
### Using `join()` to Ensure Order

The `join()` method allows one thread to **wait for another thread to complete** before continuing. This helps avoid situations where threads access shared resources **before another thread has finished updating them**.

---
### Example Using `join()`

```java
class Task extends Thread {
    public void run() {
        System.out.println("Task started");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println("Task finished");
    }
}

public class Main {
    public static void main(String[] args) {
        Task t1 = new Task();
        t1.start();

        try {
            t1.join(); // Main thread waits for t1 to finish
        } catch (InterruptedException e) {}

        System.out.println("Main thread continues after t1");
    }
}
```

**Output:**

```
Task started
Task finished
Main thread continues after t1
```

Here, `join()` ensures that the main thread does not proceed until `t1` completes.

---
### Limitation of `join()`

- `join()` only ensures execution **order**, not **mutual exclusion** or **data protection**.
- It does not make code inherently thread-safe when threads modify **shared data**.

---
### Making Threads Safe Using `synchronized`

The `synchronized` keyword is one of the most common ways to ensure **thread safety** in Java. It is used to define a **critical section**, which is a part of the code that should be executed by **only one thread at a time** when accessing shared mutable data.

---
### What is a Critical Section?

A **critical section** is a segment of code that accesses a shared resource (like a variable or object) and must **not be executed by more than one thread at a time**. If two threads enter a critical section simultaneously without synchronization, it can lead to **race conditions**.

---
### How `synchronized` Works

When a thread enters a `synchronized` block or method, it acquires the **intrinsic lock (monitor)** for that object. Any other thread trying to enter a `synchronized` block on the same object will be **blocked until the lock is released**.
### ✅ Yes, in Java:

- A `synchronized` block or method **acts as a critical section**, just like in Operating Systems. It ensures that **only one thread at a time can execute that code**, **if** they are locking on the same object.
    
- It does this by acquiring a **monitor lock (also called intrinsic lock)** on the object.

So when you write:

```java
synchronized (someObject) {
    // critical section
}
```

Only one thread can hold the lock on `someObject` at a time. All others trying to enter the block must wait.

---
### ✅ And yes, semaphores are related:

- A **semaphore** is a more flexible construct (from `java.util.concurrent`) that controls **how many threads** can access a resource.
    
- You can use it to **limit access to one thread** (binary semaphore — similar to a lock), or more (counting semaphore).

Example:

```java
Semaphore semaphore = new Semaphore(1); // only one permit

semaphore.acquire(); // waits if another thread already has it
try {
    // critical section
} finally {
    semaphore.release(); // give up the permit
}
```

So both `synchronized` and `Semaphore` enforce mutual exclusion — the idea that **only one thread** (or limited number) can enter the **critical section** — just like in OS concepts.

---
### Synchronized Block Syntax

```java
synchronized (lockObject) {
    // critical section
}
```

- `lockObject` is the object whose lock is being acquired.
- Only one thread can hold the lock at a time.

---
### Synchronized Method Syntax

```java
public synchronized void method() {
    // critical section
}
```

- Equivalent to: `synchronized(this) { ... }`
- Locks the current instance of the class.

---
### Example: Thread-Unsafe Counter vs Synchronized Counter

**Without Synchronization (Thread-Unsafe):**

```java
class Counter {
    int count = 0;

    public void increment() {
        count++;
    }
}
```

**With Synchronization:**

```java
class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

**Or using synchronized block:**

```java
class Counter {
    int count = 0;
    Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
```

---

### Static Synchronized Methods

```java
public static synchronized void staticMethod() {
    // Locks the class object, not the instance
}
```

- Locks on the `Class` object of the class (`ClassName.class`), not an individual object instance.
    

---
### Tips

- Always synchronize access to **shared mutable data**.
- Avoid using publicly accessible objects (like Strings or global variables) as lock objects.
- Keep the synchronized block **as small as possible** to minimize performance impact.
- Avoid deadlocks by not holding multiple locks unnecessarily.

---
### Summary

- `synchronized` ensures that only one thread accesses the critical section at a time.
- Use synchronized methods or blocks to **guard shared data**.
- Critical sections must be protected to avoid data races and ensure thread safety.
- Combine `join()` (for ordering) with `synchronized` (for exclusivity) to write reliable multithreaded programs.




---





### Detailed Example of Using `synchronized` in Java

---
### Objective

To demonstrate how the `synchronized` keyword works in Java to protect shared data in a multithreaded environment.

---
### Problem

We have a `Counter` class with a `count` variable that multiple threads will try to increment. If we do not use synchronization, the result will be incorrect due to race conditions.

---

### Code Without Synchronization (Thread-Unsafe)

```java
class Counter {
    int count = 0;

    public void increment() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // here on 2 threads, we are trying to update same object
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.count);
    }
}
```

#### Expected Output (Thread-Unsafe)

```bash
Final count: 1148   # or smtg less than 2000
```

Due to race conditions, some increments are lost.

In the non-synchronized version, the final count is **less than 2000** because of a **race condition** during the `count++` operation.

Let’s break it down:

---
#### 🔁 What happens during `count++`?

Even though it looks like one step, this line:

```java
count++;
```

Actually involves **three separate operations** at the CPU level:

1. **Read** the current value of `count` from memory.
2. **Increment** it by 1.
3. **Write** the new value back to memory.

---
#### 🧵 Now imagine this with two threads:

Let’s say `count = 1000`, and both `Thread A` and `Thread B` try to increment it **at the same time**:

- **Thread A reads** `count = 1000`
- **Thread B reads** `count = 1000` (same time)
- Both increment: `1000 + 1 = 1001`
- Both write `1001` back to memory

Expected value = `1002`  
Actual value = `1001`  
✅ **One increment is lost**

---
#### 🔄 This Happens Repeatedly

If this happens even a few times across 1000 iterations per thread, your final result becomes **some number less than 2000**.

---
#### ✅ Solution

When you use `synchronized`, it ensures only one thread can do the `count++` at a time — so no overlaps happen. That’s why synchronization gives the **correct result (2000)**.

---
#### Code With `synchronized` (Thread-Safe)

```java
class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.count);
    }
}
```

#### Output (Thread-Safe)

```
Final count: 2000
```

Now the result is correct because the `increment()` method is synchronized, making it a **critical section**.

Critical section can also be defined like:

```java

class Counter {
    int count = 0;
    final Object lock = new Object(); // optional but common for flexibility

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}

OR

public void increment() {
    synchronized (this) {
        count++;
    }
}


```


---
### Explanation

- In the thread-unsafe version, both threads can enter `increment()` at the same time and overwrite each other's updates.
    
- In the synchronized version, only one thread at a time can execute `increment()` on the same `Counter` object.
    
- This eliminates the race condition and guarantees correctness.

---
### Summary

- Use `synchronized` to make a block or method a **critical section**.
- It ensures mutual exclusion, so only one thread can modify shared data at a time.
- It’s simple and effective for thread safety when dealing with a single shared object.

----
