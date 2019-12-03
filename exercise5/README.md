# Exercise 5
## Create a full RESTful endpoint

In this exercise, we will create a full REST endpoint !

### 0. Start Quarkus in Dev mode


   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. View app in web browser
http://localhost:8080/tag

http://localhost:8080/tag/1

### 2. Look at the code for this RESTful endpoint

``` 
@Path("/tag")
@Produces("application/json")
@Consumes("application/json")
public class TagResource {

  @OPTIONS
  public Response opt() {
    return Response.ok().build();
  }

  @GET
  public List<Tag> getAll() {
    return Tag.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getOne(@PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    return Response
      .status(Response.Status.OK)
      .entity(entity)
      .build();
  }

  @POST
  @Transactional
  public Response create(Tag tag) {
    tag.persist();
    return Response.status(Response.Status.CREATED).entity(tag).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(Tag tag, @PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
    entity.name = tag.name;
    entity.persist();
    return Response.ok(entity).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public Response deleteOne(@PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    entity.delete();
    return Response.noContent().build();
  }
```


### 3. Implement the GreetingResource RESTful endpoint 
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
Add another method to get a single value from greetingsInMultipleLangs. Here's a hint:

```
  @GET
  @Path("/{id}")
  public String getOne(@PathParam("id") Long id) {

...

```    

<p  align="center">
	<font size="4">
 		<a href="../exercise4/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise6/">Next >></a></td>
 </font>
</p>