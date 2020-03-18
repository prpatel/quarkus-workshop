# Exercise 1
## Run and hack on a Quarkus project

In this exercise, we will run a Quarkus project and play with it to get familiar with some basic commands and constructs!

### 0. Start Quarkus in Dev mode


   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. View app in web browser
Go to http://localhost:8080/hello

### 2. Look at the code for the RESTful endpoint
Open this project in a Java editor/IDE

Open exercise1/src/main/java/org/example/exercise1/GreetingResource.java

### 3. Play around with hot-reloading 
Change the String in the last line:

```return "hello" ```

to something else. 

> Do not restart the anything, simply go to the web browser, and hit reload. Quarkus automatically loads your changes without restarting the app server!

### 4. Create an executable JAR file

   ```
   ./mvnw package
   ```
Then see what was produced, look into the target directory:

   ```
    // unix
   ls -l target

    // windows
    dir target
   ```

```
╰─ ls -1 target 
classes
exercise1a-1.0-SNAPSHOT-runner.jar
exercise1a-1.0-SNAPSHOT.jar
generated-sources
generated-test-sources
lib
maven-archiver
maven-status
quarkus
surefire-reports
test-classes
```

The *-runner is the file we can use to run the app!

### Run the application in standard mode

```
java -jar target/exercise1a-1.0-SNAPSHOT-runner.jar 

```
Look for this on the first line:
" started in 0.663s. "

> Note how quickly this Quarkus application starts up!

<p  align="center">
	<font size="4">
 		&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise2/">Next >></a></td>
 </font>
</p>
