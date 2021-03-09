# Intro to deploying JUnit tests with Gradle
This example project is written in Java, and tested with Gradle/JUnit.

There are also two tests run against this code, one that demonstrates the gradle build and one that compiles and runs HelloWorld from source.

### The assignment
The tests are currently failing because of an output mismatch between the code and the JUnit test. Fixing the output text in `System.out.println()` in the main method will make the tests green.  Even though the test is named "Hello World" the text you must alter in `System.out.println()`, is unrelated.  Check the JUnit Test to see what output it requires to go green.

To examine the JUnit Tests being run against this code, go to `/src/test/java/com/example/project` and see what kind of output the test expects. You can then modify the source code in `/src/main/java/com/example/project` to match the desired output of the test.

### A little bit about Gradle
Gradle and Maven are both open source build automation tools used for building, testing, and deploying code.  You'll notice that Eclipse and other IDEs have built in support for Gradle and Maven and will auto-generate your directory structure and a number of other components for you.  Here's a link to the User Manual if you're interested: https://docs.gradle.org/current/userguide/what_is_gradle.html.

We are using Gradle in this class both to familiarize you with it, and because it sets up JUnit tests in such a way that you can run them by simply executing `gradle test`, which is much easier than other ways of running them from the command line.  


### Tests
There are two tests being run against this code, one is `gradle test`, which will use Gradle's infrastructure to do an output match against the JUnit test you looked at.  It will pass if it both passes the JUnit test and gives expected output.

The second is an output match against separately compiled source code that independently verifies the output of the Java class.
