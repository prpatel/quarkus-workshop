package org.example.exercise4;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreetingResourceTest {

    @Test
    @Order(1)
    public void testGetOne() {
        given()
          .when().get("/greeting/1")
          .then()
             .statusCode(200)
             .body(is("Salut"));
    }

    @Test
    @Order(0)
    public void getGetAll() {
        given()
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .log().body()
                .body("size()", is(4));
    }


    @Test
    @Order(3)
    void testAddingAnItem() {
        String greeting = "HIYA";
        given()
                .body(greeting)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/greeting")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body( is("HIYA"));

        given()
                .when()
                .get("/greeting")
                .then()
                .body("size()", is(5));

    }

    @Test
    @Order(4)
    void testUpdatingAnItem() {
        String name = "Ciao";
        given()
                .body(name)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 2)
                .when()
                .put("/greeting/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(is("Ciao"));
    }

    @Test
    @Order(5)
    void testDeletingAnItem() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 2)
                .when()
                .delete("/greeting/{id}")
                .then()
                .statusCode( HttpStatus.SC_NO_CONTENT);

        given()
                .when()
                .get("/greeting")
                .then()
                .body("size()", is(4));
    }


}
