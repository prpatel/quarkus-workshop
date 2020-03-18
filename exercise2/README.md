# Exercise 2
## Run and hack on a Quarkus project

In this exercise, we will run a Quarkus project and play with it to get familiar with some basic commands and constructs!

### 0. Start Quarkus in Dev mode


   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. Getting parameters

Have a look at the endpoint that is declared in GreetingResource:

``` 
@Path("/greeting/{name}")
```

Note how we declare and access the "name" parameter using the @PathParam annotation!

### 2. Write a new JSON based method

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

### 3. Explore CDI (dependency injection) 
You probably noticed a GreetingService.java file in the same dir as GreetingResource.java. The service was injected in using this annotation:
```
@Inject
GreetingService service; 
```
And was used later in a method like this: service.greeting(name)

Go and change the return value of greeting method in the service and hit the /greeting/{name} endpoint, see what happens.
     
### 4. Create new endpoint
Add another method to get a single value from `greetingsInMultipleLangs` created in step #2 above. Here's a hint:

```
  @GET
  @Path("/{id}")
  public String getOne(@PathParam("id") Long id) {

...

```    

<p  align="center">
	<font size="4">
 		<a href="../exercise1/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise2/">Next >></a></td>
 </font>
</p>
