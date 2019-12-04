package com.thejavacafe.backend;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Testcontainers
public class TestcontainersEnvironment implements QuarkusTestResourceLifecycleManager {


  @Container
  PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer<>("postgres:10.5")
    .withDatabaseName("quarkus")
    .withUsername("quarkus")
    .withPassword("quarkus");


  @Override
  public Map<String, String> start() {
    postgresqlContainer.start();
    Map<String, String> systemProps = new HashMap<>();
    systemProps.put("quarkus.datasource.url", postgresqlContainer.getJdbcUrl());
    systemProps.put("quarkus.hibernate-orm.database.generation", "create-drop");
    systemProps.put("quarkus.hibernate-orm.sql-load-script", "import.sql");
    System.out.println("\n\n\n\n" + postgresqlContainer.getJdbcUrl());
    return systemProps;
  }

  @Override
  public void stop() {
//    postgresqlContainer.stop();

  }
}
