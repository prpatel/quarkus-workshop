package org.example.exercise1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.isA;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(isA(String.class));
    }

}