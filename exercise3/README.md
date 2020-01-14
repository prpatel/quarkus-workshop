# Exercise 3
## Create a full RESTful endpoint

In this exercise, we will create a full REST endpoint !

### 0. Start Quarkus in Dev mode


   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. View app in web browser
http://localhost:8080/greeting

http://localhost:8080/greeting/1

### 2. Look at the code a full RESTful endpoint 

Go to the end of this README and look at a sample RESTful endpoint. 

> This one uses a JPA entity object called "Tag", we will get to database and JPA in the next exercise
> We have already created a full "CRUD" GreetingService, to which we've already added some methods to CRUD the data. Examine it to see what methods are available
>

Note how we have put these annotations at the top:

``` 
@Produces("application/json")
@Consumes("application/json")
```

To let Quarkus know that we expect JSON in/out!

Also note that we're using these annotations to indicate the different HTTP verbs for a full REST endpoint:

``` 
@DELETE
@PUT
@POST
@GET
```

Also note that the GreetingService is NOT using JPA (we'll do that in the next exercise), so you can just call the methods in GreetingService to do the appropriate operation. You won't need to use these things for now:

``` 
entity.persist();
@Transactional
```

As they are JPA/Panache specific.

You will, however want to use the RESTeasy helpers to build responses:

``` 
return Response
      .status(Response.Status.OK)
      .entity(entity)
      .build();
```

Remember to use this guide if you need some docs, or ask  your instructor!
[Quarkus REST Guide](https://quarkus.io/guides/rest-json)

### 3. Implement the Update, Delete and the Create RESTful endpoints  

Open GreetingResource.java. We've implemented a couple of REST endpoints for you :)

> Your task is to implement the Update, Delete, and Create endpoints.

To create a new Greeting, you would call: service.add (we've already injected the service into the GreetingResource). Inspect the GreetingService to see how to use it.
 
Again, scroll to the bottom of this README to see a complete REST Resource Quarkus-Java source! You can copy/paste parts of it, and edit, to complete this exercise. 

> Remember Quarkus automatically loads your changes without restarting the app server!

> Endpoint address: http://localhost:8080/greeting

Here are some curl commands to get you going!

Get all greetings:
> curl -v -H "Content-type: application/json" -X GET http://localhost:8080/greeting

Create a new greeting (you must implement this endpoint):

> curl -v -H "Content-type: application/json" -d "{\"name\":\"Hiya\"}" -X POST http://localhost:8080/greeting
     
### SAMPLE FULL REST RESOURCE CODE

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
> Remember that the above example uses JPA. For what you're doing here, you won't need @Transactional, and you will use the GreetingService to work with the "database"



<p  align="center">
	<font size="4">
 		<a href="../exercise2/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise4/">Next >></a></td>
 </font>
</p>
