package org.example.exercise1;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testGetOne() {
        given()
          .when().get("/greeting/1")
          .then()
             .statusCode(200)
             .body(is("Salut"));
    }

}
