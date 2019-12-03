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

### 2. Look at the code below for this RESTful endpoint 

(this one uses a JPA entity object called "Tag", we're going to use the GreetingService from exercise1, to which we've already added some methods to CRUD the data)

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

### 3. Implement the Update, Delete and the Create RESTful endpoints  

We've implemented the easy ones for you :)

To create a new Greeting, you would call: service.add (we've already injected the service into the GreetingResource). Inspect the GreetingService to see how to use it.
 

> Remember Quarkus automatically loads your changes without restarting the app server!


> Endpoint address: http://localhost:8080/greeting

Here are some curl commands to get you going!

> curl -v -H "Content-type: application/json" -X GET http://localhost:8080/greeting

> curl -v -H "Content-type: application/json" -d "{\"name\":\"Containers\"}" -X POST http://localhost:8080/greeting
     
### EXTRA CREDIT



### [<< Back](../Exercise2/README.md) [Index](/README.md) [Next >>](../Exercise4/README.md)