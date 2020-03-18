package com.thejavacafe.backend;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

@Testcontainers
public class KafkaEnvironment implements QuarkusTestResourceLifecycleManager {

  @Container
  KafkaContainer kafkaContainer = new KafkaContainer().withEmbeddedZookeeper();


  @Override
  public Map<String, String> start() {
    kafkaContainer.start();
    Map<String, String> systemProps = new HashMap<>();
    return systemProps;
  }

  @Override
  public void stop() {

  }
}
