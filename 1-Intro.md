## How java works:

### **Java is Platform Independent (at the source level)**

- When you write Java code (`.java` files), the **Java Compiler (javac)** compiles it into **bytecode** (`.class` files), **not machine code**.
    
- This bytecode is a **universal, intermediate format**, not tied to any specific operating system or hardware.

---
### **JVM is Platform Dependent**

- The **Java Virtual Machine (JVM)** is what executes the bytecode.
- However, the **JVM itself is platform-specific** — meaning there's a different JVM for Windows, Linux, macOS, etc.
- But once you have the right JVM for your OS, it can **run the same bytecode** regardless of where it was compiled.

---
### **So how does this make Java platform-independent?**

- **You write code once**, compile it into bytecode.
- **That bytecode can run anywhere**, as long as there's a JVM for that platform.
- Hence, Java follows: **"Write Once, Run Anywhere"**.
    
---

Every java needs to have a main method for execution

JRE = JVM + libraries 
Java is WORA.

| **Component** | **Full Form**            | **Purpose**                                        | **Contains**                              | **Used By**            |
| ------------- | ------------------------ | -------------------------------------------------- | ----------------------------------------- | ---------------------- |
| **JDK**       | Java Development Kit     | To **develop, compile, and run** Java applications | JRE + Compiler (`javac`), Debugger, Tools | Developers             |
| **JRE**       | Java Runtime Environment | To **run** Java applications                       | JVM + Core Java Libraries                 | End-users & developers |
| **JVM**       | Java Virtual Machine     | To **execute bytecode**                            | Execution engine (platform-dependent)     | Internally by JRE/JDK  |

Here’s a **block text diagram** to visualize how **JDK, JRE, and JVM** relate:

```
+------------------------------+
|          JDK                |   <- Java Development Kit
|  +-----------------------+  |
|  |        JRE            |  |   <- Java Runtime Environment
|  |  +------------------+ |  |
|  |  |      JVM         | |  |   <- Java Virtual Machine
|  |  +------------------+ |  |
|  |  |  Java Libraries  | |  |
|  |  +------------------+ |  |
|  +-----------------------+  |
|  | Compiler (javac)      |  |
|  | Debugger, Tools       |  |
|  +-----------------------+  |
+------------------------------+
```

### Quick Meaning:
- **JDK** includes everything: tools + JRE
- **JRE** includes JVM and standard libraries
- **JVM** runs your `.class` bytecode
    

![[Java-working.png]]

---

