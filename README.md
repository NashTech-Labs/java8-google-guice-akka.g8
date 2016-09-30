# Java 8 with Goole Guice and Akka

This is pure java based sbt, sample application using **Java 8 lamdas**, **Google Guice for DI** and **Akka** for concurrency and fault tolerance. This application used **Akka** **Abstract** classes for building actors and **Builder** classes for handling actor messages.

**Abstract** classes and **Builder** classes is the way for remove biolerplate code from java and write neat and clean code as relevant to scala. We are creating test cases for actors and Java 8 **CompletableFuture** for handling concurrency.

This applications follow standards tools for maintaining code quality and best practices, below are the list: 
   - Jacoco for Test Coverage
   - CPD for Copy Paste Detection
   - Depedendency Graph
   - Checkstyle
   - Findbugs
   
Below are the commands for building and running the application: 
```
{project_dir} $ sbt clean compile test

{project_dir} $ sbt run
```
