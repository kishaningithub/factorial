# Development process

This app was developed by adhering to the test driven development(TDD) process.
This project goal is to serve as an example for building cli apps using TDD

## Steps

###  Create the project using maven quickstart archetype, either via CLI or via GUI of your IDE
  - Using command line `mvn archetype:generate -DgroupId=com.example -DartifactId=factorial -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`
  - [Using intelliJ](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/creating-a-project/) 
### Update the pom.xml to include Junit and remove all existing java files viz `AppTest.java` and `App.java` to have a clean slate
```xml
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.9.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```

### Add the first failing test and make it pass
 
- When writing the test always start with the assertion to aid the thinking process
```java
public class FactorialTest {
    @Test
    public void testFactorialOf0Is1(){
        assertEquals(1, factorial(0));
    }
}
```
- At this point the `factorial` method does not exist (the first RED! yay!) so make it GREEN by creating the method
```java
public class FactorialTest {
    @Test
    public void testFactorialOf0Is1(){
        assertEquals(1, factorial(0));
    }

    private int factorial(int n) {
        return 0;
    }
}
```
- If you notice above i have done the most minimal change (just created the method in the test class itself) and we have the first GREEN (compiler is happy)
- Now run the test
  - The test will fail with the following (our second RED)
```
org.opentest4j.AssertionFailedError: 
Expected :1
Actual   :0
```
  - Now make the test pass by doing the minimal change 
```java
private int factorial(int n) {
    return 1;
}
```
- The test passes! Now on to the third phase REFACTOR!
  - Move the factorial method to a separate class
```java
public class Factorial {
    static int factorial(int n) {
        return 1;
    }
}
```

### Add the second test

```java
@Test
public void testFactorialOf1Is1() {
    assertEquals(1, factorial(1));
}
```
- If you notice here the test passes! because if you notice the implementation we just return 1
- Now on to the third

### Add the third test case

```java
@Test
public void testFactorialOf2Is2(){
    assertEquals(2, factorial(2));
}
```
- This will fail(RED), now make it pass (GREEN) with the minimal code change to satisfy the test
```java
static int factorial(int n) {
    if(n == 0 || n == 1){
        return 1;
    }
    return 2;
}
```
- There is not much to REFACTOR, now on to the next

### Add the fourth test case

```java
@Test
public void testFactorialOf3Is6(){
    assertEquals(6, factorial(3));
}
```
- The test case will fail (RED), now make it pass (GREEN)
```java
public class Factorial {
    static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
```
  - Now time to REFACTOR, here we refactor both the implementation and tests
    - The `n == 1` is redundant and hence the implementation code can be simplified as
```java
static int factorial(int n) {
    if (n == 0) {
        return 1;
    }
    return n * factorial(n - 1);
}
```
  - The test code can leverage the power of `ParameterizedTest` in junit5 and simplify the test as
```java
public class FactorialTest {

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6"
    })
    public void testFactorial(int n, int expected) {
        assertEquals(expected, factorial(n));
    }
}
```
  - Yeah that refactor was cool :-)

### Lets try to break the system

#### Breaking integer boundaries

If you notice the factorial results grows quite exponentially with input
- Let try n=13, if you see the value of factorial is 6227020800 (~ 6 billion) which is larger than the maximum size of integer which is 2147483647 (~ 2 billion)
```java
@ParameterizedTest
@CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "13, 6227020800"
})
public void testFactorial(int n, int expected) {
    assertEquals(expected, factorial(n));
}
```
- The above test will fail because integer value cannot hold it, so lets convert it to a bigger value which is long, to the code becomes
```java
@ParameterizedTest
@CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "13, 6227020800"
})
public void testFactorial(int n, long expected) {
    assertEquals(expected, factorial(n));
}
```
```java
static long factorial(int n) {
    if (n == 0) {
        return 1;
    }
    return n * factorial(n - 1);
}
```
- NOTE: we only write implementation code if the test demands it!

#### Breaking long boundaries
- If you observe factorial(30) is `265252859812191058636308480000000` which is much much higher then the size of long. In java, the data type that can hold huge integers like is `BigInteger` lets use that
- After following the RED, GREEN, REFACTOR this will be the results
```java
@ParameterizedTest
@CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "13, 6227020800",
        "30, 265252859812191058636308480000000"
})
public void testFactorial(int n, BigInteger expected) {
    assertEquals(expected, factorial(n));
}
```
```java
static BigInteger factorial(int n) {
    if (n == 0) {
        return BigInteger.ONE;
    }
    return BigInteger.valueOf(n).multiply(factorial(n - 1));
}
```

### Next feature - expose this as a CLI App

The next feature is to try expose this as a CLI app. So far we have been dealing with "pure" functions which jelled so well with
our thought process, as we increase the side effects (printing to console, writing to network etc) the amount of setup that is
needed to test this application increases so TDD actually nudges you to decrease the amount of side effects thereby improving the 
overall design

### Start with the corner case

- In this lets test for the case where the app is run without passing any input
- Follow the test first approach and create classes and implementation details, the result will look like this
```java
public class AppTest {
    @Test
    public void testAppShouldNotFailIfNoInputIsGiven() {
        assertDoesNotThrow(App::main);
    }
}
```

```java
public class App {
    public static void main() {

    }
}
```
