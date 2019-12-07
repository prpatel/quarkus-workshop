# Hands-on with Quarkus: Coding that Sparks Joy in Java
## Quarkus: Supersonic Subatomic Java

In this hands-on workshop we will do exercises using the hot new Quarkus framework to build a sample app. Quarkus is based on Java standards such as CDI and JPA, and is built specifically to offer a compelling platform for building Serverless application, whether you run on Kubernetes or Cloud Functions.

Quarkus is also built to bring “developer joy” and makes development in Java fun and fluid and fast!

## Learn (and code!):
* Getting to know Quarkus - create a new project
* Building REST endpoints quickly using RESTEasy
* Deploy app as Cloud Function
* Easy to write unit tests with RESTAssured
* No-fuss and intuitive database access with Panache


## Prerequisites: 
1. Do the setup here: [Setup for workshop](https://github.com/prpatel/Serverless-Workshop-Setup-All-Platforms)
2. Basic Java knowledge with a Java IDE
3. Please have this installed on your laptop: Java 8, Maven, Docker

### 0. Test IBM Cloud Functions From The CLI

Run the following command to invoke a test function from the command-line.

   ```
   $ ibmcloud wsk action invoke whisk.system/utils/echo -p message hello --result
   {
       "message": "hello"
   }
   ```

*If you're using the local OpenWhisk instance, simply drop the 'ibmcloud' at the beginning of the command, and run the rest*

*If this command executes successfully, you have verified that the IBM Cloud CLI and Cloud Functions plugin have been installed and configured correctly. If this does not work, please contact the workshop organiser to provide assistance!*

### 1. Build your first Quarkus app!

[Instructions here](exercise1/)

### 2. Deploy the app to IBM Cloud Functions 

[Instructions here](exercise2/)

### 3. Create a RESTful endpoint

[Instructions here](exercise3/)

### 4. Write unit tests using RESTassured

[Instructions here](exercise4/)

### 5. Connect to and use a Database! 

[Instructions here](exercise5/)

### 6. Easy integration tests with TestContainers 

### 7. Messaging with Kafka
[Instructions here](exercise7/)
