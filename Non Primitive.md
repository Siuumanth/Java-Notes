## 1. Arrays

#### 🔸 About Arrays in Java
- Arrays in Java are **objects**, and are **not dynamic** in size.
- Once you create an array with a specific size, **it cannot grow or shrink** automatically (unlike Python lists or C++ vectors).
    
- If you want resizable behavior, you must either:
    - Manually create a **new larger array** and copy over data.
    - Use **`ArrayList`** (a part of `java.util`) for dynamic resizing.

---
#### 🔸 Arrays Are Objects

- Arrays are created from special internal classes (`[I` for `int[]`, `[Ljava.lang.String;` for `String[]`, etc.).
- They are stored in the **heap**, and the reference to them is stored in the **stack**.
- When you declare an array, it creates an **object**, and you get a reference to that object.

---
#### 🔹 Declaring and Initializing Arrays

```java
int[] arr = new int[5];           // Fixed-size array
int[] arr2 = {10, 20, 30};        // Direct initialization
String[] names = new String[3];   // Array of objects (Strings)

arr.length => get array size
```

Default values for indexes = 0 for int.

---
#### 🔹 Using Utility Methods (`java.util.Arrays`)

You can import the `Arrays` class to help with common operations:

```java
import java.util.Arrays;

Arrays.sort(arr);                       // Sorts in-place
Arrays.fill(arr, 0);                    // Fill all values with 0
int index = Arrays.binarySearch(arr, 5); // Searches for 5 (sorted array only)
String str = Arrays.toString(arr);      // Convert to printable string
```

---

##### Jagged Arrays in Java (2D Arrays with Unequal Columns)

- In Java, **2D arrays are arrays of arrays**, so the inner arrays **can have different lengths**.
- Such 2D arrays with uneven inner sizes are called **jagged arrays**.

---
#### 🔹 Declaration and Initialization

```java
int[][] jagged = new int[3][];  // 3 rows, column sizes unspecified

jagged[0] = new int[2];         // Row 0 has 2 columns
jagged[1] = new int[4];         // Row 1 has 4 columns
jagged[2] = new int[1];         // Row 2 has 1 column
```

Or directly initialize:

```java
int[][] jagged = {
    {1, 2},
    {3, 4, 5, 6},
    {7}
};
```

---
#### 📌 Summary

- Jagged arrays give **more flexibility** and **save space**.
- Common in cases like **triangular matrices**, **ragged tables**, or **variable-length data** per row.
    
---
## 2. ArrayList:

#### 🔸 About `ArrayList` in Java

- `ArrayList` is a **resizable array** provided by Java's **Collections Framework** (`java.util.ArrayList`).
    
- Internally, it uses an array that **grows automatically** when needed.
    
- It only stores **objects**, not primitives. For primitive types like `int`, you use wrapper classes like `Integer`.

---

#### 🔹 Declaring an ArrayList

```java
import java.util.ArrayList;

ArrayList<Integer> list = new ArrayList<>();
ArrayList<String> names = new ArrayList<>();
```

- You must use **generics** (`<Type>`), as `ArrayList` is type-safe.
- For primitive types: `int → Integer`, `double → Double`, etc.

---

#### 🔹 Common `ArrayList` Methods

| Method                | Description                                    |
| --------------------- | ---------------------------------------------- |
| `add(element)`        | Adds element to end                            |
| `add(index, element)` | Inserts at a given index                       |
| `get(index)`          | Returns element at index                       |
| `set(index, element)` | Replaces element at index                      |
| `remove(index)`       | Removes element at index                       |
| `remove(Object o)`    | Removes first occurrence of given object       |
| `size()`              | Returns number of elements                     |
| `isEmpty()`           | Checks if the list is empty                    |
| `clear()`             | Removes all elements                           |
| `contains(element)`   | Checks if element is present                   |
| `indexOf(element)`    | Returns index of first occurrence (or -1)      |
| `toArray()`           | Converts to an array                           |
| `sort()`              | Sort using `Collections.sort()` (not directly) |

In Java, you **cannot** use bracket notation like `list[2] = 7` with `ArrayList`.
That syntax only works for **arrays**, not for `ArrayList`.

---
#### 🔹 Example Usage

```java
ArrayList<Integer> list = new ArrayList<>();

list.add(10);               // [10]
list.add(20);               // [10, 20]
list.add(1, 15);            // [10, 15, 20]
System.out.println(list.get(0));     // 10
list.set(2, 25);            // [10, 15, 25]
list.remove(1);             // [10, 25]
System.out.println(list.size());     // 2
```

---
#### 🔹 Sorting an ArrayList

```java
import java.util.Collections;

Collections.sort(list);  // Ascending order
Collections.reverse(list); // Reverse order
```

---
#### 🔹 Iterating Over an ArrayList

```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

for (int val : list) {
    System.out.println(val);
}
```

---
#### 🔸 Performance Notes

| Operation     | Time Complexity        |
| ------------- | ---------------------- |
| `add()`       | Amortized O(1)         |
| `get(i)`      | O(1)                   |
| `remove(i)`   | O(n) (due to shifting) |
| `contains(x)` | O(n)                   |

---

## 3. String

String is a class.

```java
String str = "safasf";
String str2 = new String();
String str3 = new String("ummm");

String s1 = "umm";
String s2 = "umm";

s1 == s2 is true;
```

### 🔸 String Constant Pool in Java

Java uses a special memory region called the **String Constant Pool (SCP)** to **optimize memory usage** for strings.

---
#### 🔹 What Is the String Constant Pool?

- It's a section of the **heap memory** where Java stores **unique string literals**.
- If you create multiple string variables with the **same literal**, they **share the same reference** in the pool.
    
---
#### 🔹 How It Works

```java
String s1 = "hello";
String s2 = "hello";

System.out.println(s1 == s2);  // true
```

- `"hello"` is stored **only once** in the SCP.
- Both `s1` and `s2` point to **the same object** in memory.
- This is **reference equality**, not just content equality.

---
#### 🔹 When Strings Are Not Shared

```java
String s3 = new String("hello");

System.out.println(s1 == s3);  // false
System.out.println(s1.equals(s3)); // true
```

- `new String("hello")` forces a **new object** on the heap, not in the pool.
- So `s1 == s3` is false (different references), even though contents are equal.

---
#### 🔹 Interning Strings

You can manually move a string to the SCP using `.intern()`:

```java
String s4 = new String("hello").intern();
System.out.println(s1 == s4);  // true
```

- `intern()` checks if an identical string exists in the pool.
- If yes, it returns the reference from the pool.
- If not, it adds the string to the pool and returns that reference.

---
#### 📌 Why Java Does This

- **Efficiency**: String literals are very common. Pooling avoids creating duplicates.
- **Immutability**: Strings are immutable in Java, so sharing them is safe.

---

## 4. StringBuffer 

- `StringBuffer` is a **mutable**, thread-safe class used to create and manipulate strings.
    
- Unlike `String`, which is **immutable**, `StringBuffer` allows **modification** of the string **without creating a new object** every time.
    
---
#### 🔹 Why StringBuffer?

- When you **concatenate strings repeatedly**, using `String` creates many temporary objects in memory (since strings are immutable).
    
- `StringBuffer` solves this by using a **dynamic character array** that can be modified directly.
    
---
#### 🔹 Key Features

| Feature     | StringBuffer                                 |
| ----------- | -------------------------------------------- |
| Mutability  | ✅ Yes                                        |
| Thread-safe | ✅ Yes (synchronized)                         |
| Performance | Slower than `StringBuilder` (due to locking) |
| Package     | `java.lang.StringBuffer`                     |

---
#### 🔹 Common Methods

```java
StringBuffer sb = new StringBuffer("Hello");
```

|Method|Description|
|---|---|
|`append(String s)`|Adds to the end|
|`insert(int index, String s)`|Inserts at a position|
|`replace(start, end, String)`|Replaces characters in range|
|`delete(start, end)`|Deletes characters in range|
|`reverse()`|Reverses the entire string|
|`toString()`|Converts to `String`|
|`capacity()`|Returns current buffer size (capacity)|
|`ensureCapacity(int n)`|Ensures minimum capacity|
|`charAt(index)`|Returns character at index|
|`setCharAt(index, char)`|Sets character at index|

---
#### 🔹 Example

```java
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");                  // Hello World
sb.insert(5, ",");                    // Hello, World
sb.replace(0, 5, "Hi");               // Hi, World
sb.delete(3, 5);                      // Hi World
sb.reverse();                         // dlroW iH
System.out.println(sb.toString());   // prints the string
```

---

#### 🔹 Difference Between `String`, `StringBuffer`, and `StringBuilder`

|Feature|String|StringBuffer|StringBuilder|
|---|---|---|---|
|Mutable|❌ No|✅ Yes|✅ Yes|
|Thread-safe|❌ No|✅ Yes|❌ No|
|Performance|Slow (if many changes)|Medium|Fastest (no sync)|

---

