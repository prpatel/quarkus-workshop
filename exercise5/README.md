# Exercise 5
## Connect to and use a Database!


In this exercise, we will use Panache, a feature of Quarkus that allows fast and smooth database integration!

### 0. Start Quarkus in Dev mode


   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. Configure database access

We've already configured for you a in-memory H2 database in resources/application.properties. We'll connect to a postgres DB in the next exercise.

``` 
quarkus.datasource.url = jdbc:h2:mem:blog
quarkus.datasource.driver = org.h2.Driver
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.sql=true
```

### 2. Look at the code for a Panache entity bean

Have a look at:
org.example.exercise5/Tag.java

You can see we've extended PanacheEntity, and have the standard JPA @Entity annotation on it. This PanacheEntity uses Hibernate underneath the hood as the ORM engine.

Also have a look at:
org.example.exercise5/TagResource.java

This is our RESTful endpoint for the Tag database-aware object.

Fire up Quarkus in dev mode if you haven't already:

   ```
   ./mvnw quarkus:dev
   ```

then go to:

http://localhost:8080/tag

We've pre-seeded the database with some initial entries using the resources/import.sql file.

### 3. Implement a new Entity class called Article

Create a new Entity object called Article. Add these persistent fields to it:

```
  @NotBlank
  public String title;
  public String subtitle;
  public String linktotweet;
  public String coverImage;
  public int likes;
  @NotBlank
  public String asciidocsource;
```

### 4. Write a new RESTful endpoint called ArticleResource

Create a new file ArticleResource.java and create some RESTful endpoints to use the Article entity object. Refer to the code fragment below and the guide link below to get going. Name your RESTful endpoint "/article". Jave a look at TagResource.java for some inspiration!

```
  @GET
  @Path("/{id}")
  public Response getOne(@PathParam("id") Long id) {
    Article entity = Article.findById(id);
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

```

[Quarkus Panache Guide](https://quarkus.io/guides/)

Go to: http://localhost:8080/article to test out your Resource and Entity!

### EXTRA CREDIT 

Write some unit tests! Below you'll see a sample test for the Tag entity. Create one to test the CRUD operations for Article.


```
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagEntityTest {

  public boolean somevar = false;

  @Test
  @Order(0)
  @Transactional
  public void testAddAndGetTags() {
    Tag tag = new Tag();
    tag.name ="BIG DATA";
    tag.persist();
    assertEquals(tag.name, "BIG DATA");

    List<Tag> foundTags = Tag.listAll();
    assertEquals(foundTags.size(), 8);

    foundTags = Tag.list("order by id");
    assertEquals(foundTags.get(0).name, "Java");
  }

}


```    

<p  align="center">
	<font size="4">
 		<a href="../exercise4/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise6/">Next >></a></td>
 </font>
</p>
