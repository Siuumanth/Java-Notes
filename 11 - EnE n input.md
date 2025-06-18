## Throwable Hierarchy and Exception Architecture in Java

---
### Root of All: `Object`

- In Java, every class inherits from the base class `Object`.
- `Object` provides basic methods like `toString()`, `equals()`, `hashCode()`, etc.

---
### `Throwable` Class

- Direct subclass of `Object`.
- Superclass for all errors and exceptions in Java.
- Only instances of `Throwable` or its subclasses can be thrown by the JVM or the `throw` statement.

### Throwable Subclasses:

```
java.lang.Object
     |
     --> java.lang.Throwable
              |-- java.lang.Exception
              |        |-- java.io.IOException
              |        |-- java.sql.SQLException
              |        |-- java.lang.RuntimeException
              |               |-- NullPointerException
              |               |-- ArithmeticException
              |               |-- IndexOutOfBoundsException
              |
              |-- java.lang.Error
                       |-- OutOfMemoryError
                       |-- StackOverflowError
                       |-- NoClassDefFoundError
```

---
### 1. `Exception`

- Represents conditions that a reasonable application **might want to catch**.
- **Checked exceptions:** Must be handled using `try-catch` or declared with `throws`.
- **Unchecked exceptions:** Subclasses of `RuntimeException`. These are not checked at compile time.

### 2. `Error`

- Indicates **serious problems** that an application should not try to catch.
- Examples: `OutOfMemoryError`, `StackOverflowError`, etc.
- Typically caused by the **environment** or JVM, not the application code.

---
### Visual Tree

```
Object
 └── Throwable
      ├── Exception
      │    ├── Checked (e.g., IOException, SQLException)
      │    └── Unchecked (RuntimeException and its subclasses)
      └── Error
           ├── OutOfMemoryError
           ├── StackOverflowError
           └── InternalError
```

---

### Methods in `Throwable`

- `getMessage()` – Returns description.
- `printStackTrace()` – Prints call stack.
- `getCause()` – Returns cause of the exception.

---
### Summary

- `Object` is the root class of everything.
- `Throwable` is the base class for all exceptions and errors.
- `Exception` is for recoverable problems; `Error` is for unrecoverable issues.
- Java uses this hierarchy to allow flexible and controlled error handling.
    





---
---







### Java Errors and Common Error Types 

---
### What Are Errors in Java?

In Java, an **error** is a serious problem that typically indicates a condition outside the control of the program. Errors are **unchecked** and belong to the `java.lang.Error` class.

However, in general programming terms, we often categorize issues in code as:

- **Compile-Time Errors**
- **Run-Time Errors**
- **Logical Errors**

Let’s understand each of these before diving deeper into Java-specific `Error` types.

---
### 1. **Compile-Time Errors**

These occur when the code **fails to compile** due to syntax or semantic mistakes. They are caught by the compiler.

**Examples:**
- Missing semicolon
- Type mismatch
- Undefined variable or method

```java
int x = "hello"; // Type mismatch
System.out.println(x // Missing parenthesis and semicolon
```

**Fix:** Correct the syntax or semantic issue in the code.

---
### 2. **Run-Time Errors or Exceptions**

These occur while the program is running. The code compiles successfully, but something goes wrong during execution.

**Examples:**

- Division by zero (`ArithmeticException`)
- Null pointer access (`NullPointerException`)
- Array index out of bounds

```java
int[] arr = new int[5];
System.out.println(arr[10]); // Runtime error: ArrayIndexOutOfBoundsException
```

**Fix:** Add validations and exception handling.

---
### 3. **Logical Errors**

These do **not produce any compile or runtime error**, but the program behaves incorrectly due to incorrect logic.

**Example:**

```java
int a = 5, b = 10;
System.out.println("Sum: " + (a - b)); // Logic error: should be a + b
```

**Fix:** Use debugging, unit tests, or code reviews to identify logical flaws.

---

### What is `java.lang.Error`?

This represents **serious system-level problems** that are typically not meant to be handled in code.

Hierarchy:

```
Throwable
  |
  |-- Exception (recoverable)
  |
  |-- Error      (serious, usually unrecoverable)
```

---
### Common Java Error Types

#### a. **OutOfMemoryError**

Occurs when JVM can't allocate memory.

```java
List<int[]> list = new ArrayList<>();
while (true) {
    list.add(new int[1000000]);
}
```

Fix: Optimize memory usage, or increase heap space.

---

#### b. **StackOverflowError**

Occurs from deep or infinite recursion.

```java
void call() {
    call();
}
```

Fix: Ensure base condition in recursive methods.

---
#### c. **NoClassDefFoundError**

Thrown if a class was present during compile time but is missing during runtime.
Fix: Ensure correct classpath or library dependencies.

---
#### d. **ExceptionInInitializerError**

Occurs during static initialization if an exception is thrown.

```java
class Test {
    static int val = 1 / 0;
}
```

Fix: Avoid exceptions in static blocks.

---
#### e. **UnsatisfiedLinkError**

Thrown when a required native method cannot be found.  
Fix: Verify native libraries are correctly loaded.

---
### Should You Catch Errors?

Errors can be caught using `try-catch`, but it's not recommended unless you know what you're doing. Example:

```java
try {
    recurse();
} catch (StackOverflowError e) {
    System.out.println("Caught error");
}
```

In general, you **catch exceptions**, not errors.

---
### Summary Table

|Type|Detected At|Can Handle?|Examples|
|---|---|---|---|
|Compile-Time|Compilation|Yes|Syntax errors, missing methods|
|Run-Time|Execution|Yes|NullPointer, ArrayIndexOutOfBounds|
|Logical|Execution|No (not thrown)|Incorrect algorithm or formula|
|JVM `Error`|Execution|Rarely|StackOverflowError, OutOfMemoryError|

---
### Final Notes
- Understand the **difference between logic, syntax, and system-level errors**.
- `Error` types in Java are rarely handled and usually signal a JVM-level issue.
- Always write code defensively to avoid run-time and logical errors.
    






---
---
---





## Java Exception Handling - Detailed Notes

---
### What is Exception Handling?

**Exception handling** in Java is a mechanism to handle **run-time errors** so that the normal flow of the application can be maintained.

Exceptions are objects that represent an abnormal condition in the program. All exceptions in Java are descendants of the `Throwable` class.

---
### Exception Hierarchy

```
Throwable
  |-- Exception (checked & unchecked)
  |     |-- IOException (checked)
  |     |-- RuntimeException (unchecked)
  |           |-- NullPointerException
  |           |-- ArithmeticException
  |
  |-- Error (not meant to be caught)
```

---
### Types of Exceptions

#### 1. **Checked Exceptions**   - compiler will force us to throw an exc

- These are exceptions that are checked at **compile time**.
- If a method can throw a checked exception, it must either handle it with a try-catch block or declare it using `throws`.
- Checked exceptions must be handled explicitly at compile time. This means the compiler forces the programmer to either catch the exception using a `try-catch` block or declare that the method `throws` the exception.

**Example:** `IOException`, `SQLException`

```java
import java.io.*;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        System.out.println(reader.readLine());
        reader.close();
    }
}
```

so if that exception occurs, it will be displayed to the user.

So Java says:

> “Hey, since these methods might throw an exception, and you’re not handling them with try-catch, you must tell whoever calls this method that it might throw an `IOException`.”

**Explanation:** Since `FileReader` and `BufferedReader` can throw `IOException`, the code uses `throws IOException` to delegate the handling.

##### Analogy:

Think of `throws` like a **warning label**:
> “This method may cause this problem — it’s not fixing it, just informing you.”

If you don't catch the exception with `try-catch`, then **you must declare `throws`** — that’s the rule with **checked exceptions**.

---

### ✅ What “Checked at Compile Time” Really Means:

**It doesn’t mean** the exception _occurs_ at compile time.  
It means the **compiler checks** that you're either:

- **Catching** the exception (`try-catch`)  
    **OR**
- **Declaring** it using `throws`
    
If you don’t do either, **your code won’t compile**.

#### 🔹 Example 1: FileReader (Checked Exception)

```java
public static void main(String[] args) {
    FileReader fr = new FileReader("file.txt"); // FileNotFoundException (checked)
}
```

🔴 **Compile-time error:**

```
Unhandled exception: java.io.FileNotFoundException
```

Because the compiler sees:

> “This code might throw a checked exception, and you didn’t handle or declare it.”

---
### 🔹 Fixed: Handle or Declare

**Option 1: Try-Catch**

```java
try {
    FileReader fr = new FileReader("file.txt");
} catch (FileNotFoundException e) {
    System.out.println("File not found.");
}
```

**Option 2: Declare `throws`**

```java
public static void main(String[] args) throws FileNotFoundException {
    FileReader fr = new FileReader("file.txt");
}
```

✅ Now the compiler is satisfied.

---
### 🔸 Summary

- **Checked exception** = Java **checks at compile time** that you’ve dealt with it
- It doesn’t “run” the code — just **verifies** your code **acknowledges** the risk
- It’s a safety mechanism to ensure developers **handle recoverable problems**







---
#### 2. **Unchecked Exceptions**

- These are not checked at compile time. They occur during runtime and typically represent programming errors.
- Unchecked exceptions occur at runtime and do not require explicit handling at compile time. The compiler does not enforce `try-catch` blocks or `throws` declarations for them.

**Example:** `NullPointerException`, `ArithmeticException`

```java
public class Test {
    public static void main(String[] args) {
        String str = null;
        System.out.println(str.length());
    }
}
```

**Explanation:** This throws a `NullPointerException` at runtime because the code tries to call `.length()` on a `null` object.

---
### Exception Handling Keywords

- `try`: Wraps code that might throw an exception.
- `catch`: Catches and handles the exception.
- `finally`: Contains code that runs regardless of exception.
- `throw`: Used to explicitly throw an exception.
- `throws`: Declares the possibility of an exception in a method.

---
### Basic Example

```java
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Explanation:**
- `10 / 0` throws `ArithmeticException`.
- The `catch` block handles it and prints a message.
- `finally` block runs regardless of exception outcome.

**Output:**

```
Cannot divide by zero!
Finally block executed
```

---
### Multiple Catch Blocks

```java
try {
    String str = null;
    System.out.println(str.length());
} catch (ArithmeticException e) {
    System.out.println("Math error");
} catch (NullPointerException e) {
    System.out.println("Null object reference");
}
```

**Explanation:** This code catches different exceptions separately, so you can give custom responses to specific problems.

---
### Throw and Throws

#### `throw` Example:

```java
public class Main {
    public static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Not eligible");
        }
        System.out.println("Eligible to vote");
    }

    public static void main(String[] args) {
        checkAge(15);
    }
}
```

**Explanation:** `throw` is used to manually throw an exception if the condition is met (age < 18).

---

#### `throws` Keyword in Java

The `throws` keyword is used in a method declaration to **indicate that the method may throw one or more exceptions**. This helps inform the caller that it needs to handle or declare those exceptions.

#### Syntax:

```java
returnType methodName(parameters) throws Exception1, Exception2, ... {
    // method body
}
```

#### Example:

```java
import java.io.*;

public class Example {
    public static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data.txt"));      // may throw fileNotFound exep
        System.out.println(reader.readLine());   // may throw IO excep
        reader.close();
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Handled IOException: " + e.getMessage());
        }
    }
}
```
#### Explanation:

- `readFile()` uses `throws IOException` because it may throw a checked exception.
- The `main()` method **calls** `readFile()` and handles the exception with `try-catch`.

##### 🔸 So Why Do You Need `throws IOException`?

Because **this method is not handling the exception inside itself**, it's saying:

> “I don’t want to handle it, whoever calls me must handle it.”

That's why `throws IOException` is necessary.

#### Key Points:

- You can list multiple exceptions using commas: `throws IOException, SQLException`
- You must declare only the checked exceptions (not unchecked ones).
- If a method doesn't handle a checked exception internally, it must declare it using `throws`.

Automatically, when the function throws an exception, is isn't transferred to what is outside the method, u need to declare it explicitly using throws.

---
### Summary

- `Object` is the root class of everything.
- `Throwable` is the base class for all exceptions and errors.
- `Exception` is for recoverable problems; `Error` is for unrecoverable issues.
- The `throws` keyword is used to pass exception handling responsibility to the caller.
- Java uses this hierarchy to allow flexible and controlled error handling.

Let me know if you want to go deeper into exception propagation or `try-with-resources` next.

---
### Custom Exception

```java
class InvalidAgeException extends Exception {    // shud extend superClass
    InvalidAgeException(String msg) {
        super(msg);  // calling the super class constructor instead of our 
    }
}

public class Main {
    static void check(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Underage");
        else System.out.println("Eligible");
    }

    public static void main(String[] args) {
        try {
            check(16);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

**Explanation:**
- You create a custom exception by extending `Exception`.
- The `check()` method throws it for age < 18.
- The main method catches and prints the custom message.

---
### Best Practices
- Catch only specific exceptions (not `Exception` or `Throwable` blindly).
- Always clean up resources in `finally` or use `try-with-resources`.
- Write custom exceptions for clear business logic separation.
- Don't suppress exceptions silently.
    
---
### Summary
- Java provides robust error handling using `try`, `catch`, `finally`, `throw`, and `throws`.
- Checked exceptions must be handled at compile time; unchecked ones can be caught optionally.
- Use custom exceptions to represent domain-specific issues.
- Proper exception handling improves program stability and user experience.







---
---
---









### Java User Input: Streams, BufferedReader, and InputStreams

Java uses **streams** to handle input and output. A stream is a sequence of data. Java has two types of streams:
- **Input Streams**: Read data (from keyboard, files, etc.)
- **Output Streams**: Write data (to console, files, etc.)

These notes focuses on reading input using:

1. Streams (concept)
2. `InputStream` and `System.in`
3. `BufferedReader`

---

#### 1. What Are Streams in Java?

#### 🔹 Concept:

A **stream** is a continuous flow of data between a source and a destination. Java streams can be either **byte streams** (for binary data) or **character streams** (for text).

#### 🔹 Categories:

|Type|For|Base Class|
|---|---|---|
|Byte Stream|Binary data|`InputStream`, `OutputStream`|
|Char Stream|Character/text|`Reader`, `Writer`|

---

#### 2. `InputStream` and `System.in`

#### 🔹 What it is:

- `InputStream` is an **abstract class** to read bytes.
- `System.in` is a predefined `InputStream` connected to the keyboard.

#### 🔹 Common Methods:

- `read()` – reads a single byte
- `read(byte[] b)` – reads bytes into an array

#### 🔹 Example:

```java
import java.io.*;

public class ByteInputExample {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a character: ");
        int input = System.in.read();

        System.out.println("You entered: " + (char) input);
    }
}
```

#### 🔹 Output:

```
Enter a character: A
You entered: A
```

#### 🔹 Explanation:

- `System.in.read()` reads a single byte as an integer
- Casting to `(char)` makes it readable as a character
    

---

#### 3. Using `BufferedReader` (Efficient for Console Input)

#### 🔹 What it is:
- `BufferedReader` is a class for reading text efficiently from a character input stream.
- Usually wrapped around `InputStreamReader` which converts byte stream to character stream.
#### 🔹 Syntax:

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

#### 🔹 Example:

```java
import java.io.*;

public class InputExample {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter your name: ");
        String name = reader.readLine();

        System.out.println("Hello, " + name + "!");
    }
}
```

#### 🔹 Output:

```
Enter your name: Varsha
Hello, Varsha!
```

#### 🔹 Explanation:

- `System.in` is a byte stream
- `InputStreamReader` converts it to a character stream
- `BufferedReader` adds buffering and enables `readLine()`

---

### 4. Reading Full Lines with InputStreamReader + BufferedReader

```java
import java.io.*;

public class InputStreamReaderExample {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        System.out.print("Enter a sentence: ");
        String input = reader.readLine();

        System.out.println("You typed: " + input);
    }
}
```

#### 🔹 Explanation:

- `InputStreamReader` converts `System.in` from byte stream to character stream
- `BufferedReader` allows efficient line reading

---

### Summary Table

|Component|Role|
|---|---|
|`System.in`|Low-level byte input stream|
|`InputStreamReader`|Converts bytes to characters|
|`BufferedReader`|Buffers characters for efficient reading|
|`readLine()`|Reads a full line of input|
|`read()`|Reads one byte at a time|

---

### Use Cases

- Use `BufferedReader` for **text/line input**
- Use `InputStream` for **low-level binary input**
- Use `read()` for **single character** inputs or manual parsing

Let me know if you'd like to add file reading, Scanner usage, or comparisons next.





