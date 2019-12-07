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

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(TestcontainersEnvironment.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagEntityTest {

  public boolean somevar = false;

  @Test
  @Order(0)
  @Transactional
  public void testGetTags() {
    Tag tag = new Tag();
    tag.name ="BIG DATA";
    tag.persist();
    assertEquals(tag.name, "BIG DATA");

    List<Tag> foundTags = Tag.listAll();
    assertEquals(foundTags.size(), 8);

    foundTags = Tag.list("order by id");
    assertEquals(foundTags.get(0).name, "Java");
  }

  @Test
  @Order(1)
  public void testArticleGetsTags() {
    Article article = Article.findById(1L);
    assertEquals(article.tags.size(), 3);
    System.out.println(article.tags);
//    assertTrue(article.tags.contains("Java"));
//    assertTrue(article.tags.contains("Quarkus"));
  }
}
