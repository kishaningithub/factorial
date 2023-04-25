# Development process

This app was developed by adhering to the test driven development(TDD) process.
This project goal is to serve as an example for building cli apps using TDD

## Steps

- Create the project using maven quickstart archetype, either via CLI or via GUI of your IDE
  - Using command line `mvn archetype:generate -DgroupId=com.example -DartifactId=factorial -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`
  - [Using intelliJ](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/creating-a-project/) 
- Update the pom.xml to include Junit and remove all existing java files viz `AppTest.java` and `App.java` to have a clean slate
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
- Add the first failing test and make it pass
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
- Add the second test
```java
@Test
public void testFactorialOf1Is1() {
    assertEquals(1, factorial(1));
}
```
 - If you notice here the test passes! because if you notice the implementation we just return 1
 - Now on to the third
