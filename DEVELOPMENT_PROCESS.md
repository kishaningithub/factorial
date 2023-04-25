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