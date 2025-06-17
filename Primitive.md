## Data types:

Java is a strongly / statically typed language.


### Primitive Data Types:

1. **Integer Types**
    - `byte` – Size: **= 1 byte**, Range: **–2⁷ to 2⁷–1** (–128 to 127)
    - `short` – Size: 2 bytes, Range: **–2¹⁵ to 2¹⁵–1** (–32,768 to 32,767)
    - `int` – Size: 2⁶ bits = 4 bytes**, Range: **–2³¹ to 2³¹–1**
    - `long` – Size:  2⁷ bits = 8 bytes**, Range: **–2⁶³ to 2⁶³–1**

 even 
 ```java
 int num = 0b101  5  // binary = 5
 int num = 0x7E
 int num = 10_000_000  // Basically commas

// works 
```
1. **Floating Point Types**
    - `float` – Size: 4 bytes**, IEEE 754 single precision
    - `double` – Size: 8 bytes**, IEEE 754 double precision
    double is default in java.
        
2. **Character Type**
    - `char` – Size:  2 bytes**, Range: **0 to 2¹⁶–1** (0 to 65,535)
    uses unicode character set.
    
3. **Boolean Type**
    - `boolean` – Logical size: **1 bit**, but typically **1 byte (2³ bits)** in JVM for storage
    - Values: **true** or **false**
    
---

#### Type conversion and casting:

Implicit type conversion works from bigger to smaller types, not the other way around.

Explicit:
- b = (int)a

```java
byte b = 127
int a = 257
b = (byte)a

// a will store 257 % 256 = 1  (value/int size)
```

---

#### Operators


### 1. **Arithmetic Operators**

|Operator|Symbol|Description|
|---|---|---|
|Addition|`+`|Adds two operands|
|Subtraction|`-`|Subtracts second operand from first|
|Multiplication|`*`|Multiplies two operands|
|Division|`/`|Divides numerator by denominator|
|Modulus|`%`|Returns remainder of division|
|Increment|`++`|Increases value by 1|
|Decrement|`--`|Decreases value by 1|

---

### 2. **Relational (Comparison) Operators**

|Operator|Symbol|Description|
|---|---|---|
|Equal to|`==`|Returns true if operands are equal|
|Not equal to|`!=`|Returns true if operands are not equal|
|Greater than|`>`|Returns true if left operand is greater|
|Less than|`<`|Returns true if left operand is smaller|
|Greater than or equal|`>=`|Returns true if left ≥ right|
|Less than or equal|`<=`|Returns true if left ≤ right|

---

### 3. **Logical Operators**

| Operator | Symbol | Description                              |
| -------- | ------ | ---------------------------------------- |
| AND      | `&&`   | Returns true if both conditions are true |
| OR       | `\|\|` |                                          |
| NOT      | `!`    | Reverses the condition (true ↔ false)    |

---

### 4. **Bitwise Operators**

|Operator|Symbol|Description|
|---|---|---|
|AND|`&`|Bitwise AND|
|OR|`|`|
|XOR|`^`|Bitwise exclusive OR|
|Complement|`~`|Bitwise NOT|
|Left Shift|`<<`|Shifts bits to the left|
|Right Shift|`>>`|Shifts bits to the right (sign-filled)|
|Unsigned Right Shift|`>>>`|Shifts bits right (zero-filled)|

---

### 5. **Assignment Operators**

|Operator|Symbol|Description|
|---|---|---|
|Assignment|`=`|Assigns value from right to left|
|Add and assign|`+=`|`a += b` → `a = a + b`|
|Subtract and assign|`-=`|`a -= b` → `a = a - b`|
|Multiply and assign|`*=`|`a *= b` → `a = a * b`|
|Divide and assign|`/=`|`a /= b` → `a = a / b`|
|Modulus and assign|`%=`|`a %= b` → `a = a % b`|
|Bitwise AND and assign|`&=`|`a &= b`|
|Bitwise OR and assign|`|=`|
|Bitwise XOR and assign|`^=`|`a ^= b`|
|Left shift and assign|`<<=`|`a <<= b`|
|Right shift and assign|`>>=`|`a >>= b`|
|Unsigned right shift and assign|`>>>=`|`a >>>= b`|

---

### 6. **Unary Operators**

|Operator|Symbol|Description|
|---|---|---|
|Unary Plus|`+`|Indicates positive value (rarely used)|
|Unary Minus|`-`|Negates an expression|
|Increment|`++`|Increases value by 1 (`++a`, `a++`)|
|Decrement|`--`|Decreases value by 1 (`--a`, `a--`)|
|Logical NOT|`!`|Reverses logical value|
|Bitwise Complement|`~`|Flips bits|

---

### 7. **Ternary Operator**

|Operator|Symbol|Description|
|---|---|---|
|Ternary|`? :`|Conditional operator → `condition ? expr1 : expr2`|

---

### 8. **Instanceof Operator**
boolean output

|Operator|Symbol|Description|
|---|---|---|
|instanceof|`instanceof`|Checks if an object is an instance of a class|

---

### 9. **Type Cast Operator**

|Operator|Syntax|Description|
|---|---|---|
|Cast|`(type)`|Converts value to specified data type|

---

#### Checking data type:
```java
String s = "hello";
sopln(s.getClass().getName());

String s = "hello";

System.out.println(s.getClass());              // class java.lang.String
System.out.println(s.getClass().getName());    // java.lang.String
System.out.println(s.getClass().getSimpleName()); // String
```


---


