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

### 4. Write a new JSON based method

Create a new method called "multipleGreetings" that sends a list of greetings in different languages, as JSON. Below is some basic code to get you started. 

```

// create a list to show some data
private List<String> greetingsInMultipleLangs = new ArrayList<String>() {{
    add("Hello");
    add("Salut");
    add("Hola");
    add("Nǐ hǎo");
}};
   
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/multiple")
public List<String> multipleGreetings() {
    return greetingsInMultipleLangs;
}

```

[Quarkus REST Guide](https://quarkus.io/guides/rest-json)

Now when you go to: http://localhost:8080/hello/multiple you should see some JSON. We suggest using Firefox as it has a nice built in JSON response viewer.

### 5. Explore CDI (dependency injection) 
You probably noticed a GreetingService.java file in the same dir as GreetingResource.java. The service was injected in using this annotation:
```
@Inject
GreetingService service; 
```
And was used later in a method like this: service.greeting(name)

Go and change the return value of greeting method in the service and hit the /greeting/{name} endpoint, see what happens.
     
### EXTRA CREDIT
Add another method to get a single value from `greetingsInMultipleLangs`. Here's a hint:

```
  @GET
  @Path("/{id}")
  public String getOne(@PathParam("id") Long id) {

...

```    


<p  align="center">
	<font size="4">
 		<a href="/">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise2/">Next >></a></td>
 </font>
</p>
