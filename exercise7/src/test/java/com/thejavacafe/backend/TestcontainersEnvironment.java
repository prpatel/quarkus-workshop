package com.thejavacafe.backend;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategyTarget;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Testcontainers
public class TestcontainersEnvironment implements QuarkusTestResourceLifecycleManager {


  static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer<>("postgres:10-alpine")
    .withDatabaseName("quarkus")
    .withUsername("quarkus")
    .withPassword("quarkus")
          .waitingFor(new WaitStrategy() {
            @Override
            public void waitUntilReady(WaitStrategyTarget waitStrategyTarget) {

            }

            @Override
            public WaitStrategy withStartupTimeout(Duration duration) {
              return this;
            }
          })
          .withReuse(true);


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
