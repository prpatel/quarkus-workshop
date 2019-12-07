# Exercise 7
## Messaging with Kafka
We've included the full sample project here for you to play with.

### 0. Start Quarkus in Dev mode & Start postgres

You'll need to have docker installed for this exercise. Run this to start up postgres:

```
./start-local-test-db.sh
```

Then start up a Kafka instance

``` 
in the kafka/ folder, run:
docker-compose up
```

Start Quarkus in dev mode
   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. Trigger a message

Create a new Article, which will send a message to a Kafka topic - see ArticleResource.java.

``` 
curl  -H "Content-type: application/json" -d "{\"title\":\"Kafka test\", \"asciidocsource\":\"https://somesource/\"}" -X POST http://localhost:8080/article
```

There's a consumer in EmailGenerator.java that prints out what is being read from the topic.

The ArticleResouce class sends the message - have a look at its setup.

### 2. Examine the Quarkus-Kafka configuration

Have a look at the application.properties file to see how all this is setup!

### 3. Implement a periodic FLowable

Follow this guide:
https://quarkus.io/guides/kafka

### EXTRA CREDIT - Kafka Streams

Follow this guide to get learn how to implement Kafka Streams and processing in Quarkus:

https://quarkus.io/guides/kafka-streams

### EXTRA EXTRA CREDIT

Write unit tests using Kafka TestContainers! Have a look at the KafkaEnvironment.java to help you get started.
