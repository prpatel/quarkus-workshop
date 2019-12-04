package com.thejavacafe.backend;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(TestcontainersEnvironment.class)
//@QuarkusTestResource(KafkaEnvironment.class)
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

  @Test
  @Order(6)
  void testCheckTagDataInArticle() {
    String rawJson = get("/article/1").then()
      .extract().body().asString();
    System.out.println(rawJson);

    Article article = get("/article/1").then()
      .statusCode(HttpStatus.SC_OK)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .extract().body()
      .as(Article.class);
    assertEquals(3, article.tags.size());
  }

  @Test
  @Order(7)
  void testPagination() {
    given()
      .queryParam("page", 0)
      .when()
      .get("article")
      .then()
      .body("size()", is(11));

  }

  private TypeRef<List<Article>> getArticleTypeRef() {
    return new TypeRef<List<Article>>() {
      // Kept empty on purpose
    };
  }

}
