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
public class TagResourceTest {

  public boolean somevar = false;

  @Test
  @Order(0)
  public void testGetAllTags() {
    given()
      .when()
      .get("/tag")
      .then()
      .statusCode(200)
      .body("size()", is(8));
  }

  @Test
  @Order(1)
  void testGetOne() {

    given()
      .pathParam("id", "15")
      .when()
      .get("/tag/{id}")
      .then()
//      .log().body()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .statusCode(HttpStatus.SC_OK)
      .body("name", equalTo("Cloud"));

  }


}
