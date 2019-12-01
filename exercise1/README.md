# Exercise 1
## Run and hack on a Quarkus project

In this exercise, we will run a Quarkus project and play with it to get familiar with some basic commands and constructs!

### 0. Start Quarkus in Dev mode


   ```
   mvn quarkus:dev
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

### 4. Write a new JSON based method

Create a new method called "multipleGreetings" that sends a list of greetings in different languages, as JSON. Below are some hints/code to get you started. 

```
@Produces("application/json")
@Consumes("application/json")
@Path("/multipleGreetings")

```

[Quarkus REST Guide](https://quarkus.io/guides/rest-json)

### 5. Connect to and use a Database! 
Go to the Exercise5/ folder and follow the instructions there. 

### 6. Easy integration tests with TestContainers 
Go to the Exercise6/ folder and follow the instructions there. 
