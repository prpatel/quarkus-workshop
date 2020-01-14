# Exercise 4
## Write tests for our REST endpoint

In this exercise, we will write some tests using RESTassured for the GreetingResource we constructed in Exercise 3.

### 0. Running tests


   ```
   ./mvnw test
   ```

> run this as you write test cases!

> You can work here by copying over the GreetingResource from exercise3, or just work in this exercise4 folder

### 1. Examine the sample (full) test class

At the bottom of this README, we've included a sample test class that exercises all of the RESTful endpoints in a Resource.

> Remember that the above example uses JPA. For what you're doing here, you won't need @Transactional, and you will use the GreetingService to work with the "database"

Here's an explanation of the one we've already done:

``` 
    @Test
    public void testGetOne() {
        given()
          .when().get("/greeting/1")
          .then()
             .statusCode(200)
             .body(is("Salut"));
    }
```

You can see wee pass the param in the "URL" that we wish to test: `/greeting/1`

".then"

we check the status code. and check that the body is what we expect: `Salut`.

### 3. Implement tests for all of the endpoints  

We've implemented testGetOne. Remember you are testing the actual endpoint results.

You will code this test in the "GreetingResourceTest.java" file under the test/ folder.

Use the example test case at the bottom of this README as a guide, and write tests for:
``` 
testGetAllGreetingsEndpoint
testGetNotFound
testAddingAnItem
testUpdatingAnItem
testDeletingAnItem
```

> Run "mvn test" as you're developing the tests. You can also test using your IDE if you wish.

### SAMPLE REST TEST CASE

``` 
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleResourceTest {

  public boolean somevar = false;

  @Test
  @Order(0)
  public void testGetAllArticlesEndpoint() {
    given()
      .when().get("/article")
      .then()
      .statusCode(200)
      .log().body()
      .body("size()", is(13));
  }

  @Test
  @Order(1)
  void testGetOne() {

    given()
      .pathParam("id", "1")
      .when()
      .get("/article/{id}")
      .then()
//      .log().body()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .statusCode(HttpStatus.SC_OK)
      .body("title", equalTo("Test Article"));

  }

  @Test
  @Order(2)
  void testGetNotFound() {

    given()
      .pathParam("id", "15")
      .when()
      .get("/article/{id}")
      .then()
      .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @Order(3)
  void testAddingAnItem() {
    Article article = new Article();
    article.title = "testing the application";
    article.asciidocsource = "http://someurl/";
    given()
      .body(article)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
      .when()
      .post("/article")
      .then()
      .statusCode(HttpStatus.SC_CREATED)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .body("title", is(article.title))
      .body("id", notNullValue());

    given()
      .when()
      .get("/article")
      .then()
      .body("size()", is(14));


    // alternate way using TypeRef
    List<Article> articles = get("/article").then()
      .statusCode(HttpStatus.SC_OK)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .extract().body().as(getArticleTypeRef());
    assertEquals(14, articles.size());
  }

  @Test
  @Order(4)
  void testUpdatingAnItem() {
    Article article = new Article();
    article.title = "testing the application (updated)";
    article.asciidocsource = "https://some.thing.com/";
    given()
      .body(article)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
      .pathParam("id", 2)
      .when()
      .put("/article/{id}")
      .then()
      .statusCode(HttpStatus.SC_OK)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .body("title", is(article.title))
      .body("id", is(2));
  }

  @Test
  @Order(5)
  void testDeletingAnItem() {
    given()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
      .pathParam("id", 5)
      .when()
      .delete("/article/{id}")
      .then()
      .statusCode( HttpStatus.SC_NO_CONTENT);

    given()
      .when()
      .get("/article")
      .then()
      .body("size()", is(13));
  }
```

<p  align="center">
	<font size="4">
 		<a href="../exercise3/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/../../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise5/">Next >></a></td>
 </font>
</p>
