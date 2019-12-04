package org.example.exercise5;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
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

}
