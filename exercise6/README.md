# Exercise 6
## Connect to a real DB and use test-containers for nice integration tests

We've included the full sample project here for you to play with.

### 0. Start Quarkus in Dev mode & Start postgres

You'll need to have docker installed for this exercise. Run this to start up postgres:

```
./start-local-test-db.sh
```

Start Quarkus in dev mode
   ```
   ./mvnw quarkus:dev
   ```

> keep this running in a separate terminal window throughout this exercise!

### 1. View app in web browser
http://localhost:8080/article

### 2. Run the unit/integration tests


``` 
./mvnw test
```


### 3. Examine the source code

Look at the tests, specifically:

TestcontainersEnvironment

ArticleResourceTest

TagEntityTest

Notice in the log output how the TestContainers are starting up a docker postgres instance.

### EXTRA CREDIT 

Write tests for Article entity and finish the TagEntity tests. Try writing some tests that exercise the relationship between Article and Tag entity objects.

Write some tests that fetch Article based on tag. Do you need to modify the TagResource to traverse the relationship?


