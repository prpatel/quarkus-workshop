# Exercise 2
## Deploy your Quarkus app to the cloud!

In this exercise, we will run deploy your Quarkus project to IBM Cloud Functions and invoke it in the Cloud

### 0. Build and package the project
Build a jar file and get it ready for deployment. Run the following command in the previous exercise1 folder where you were working:

   ```
   ./mvnw install
   ```

### Create an IBM Cloud function with this code

``` 
# from the exercise1/ folder
ibmcloud fn action create quarkustest target/exercise1-1.0-SNAPSHOT-runner.jar --docker prpatel/openwhisk-quarkus --web true
```

This creates the cloud function, passes in the jar source code, and uses a special Apache OpenWhisk container for easy Quarkus deployments. It is different than how we normally architect OpenWhisk / IBM Cloud Functions - it uses a URI proxy based approach. Your instructor can discuss this in detail if you wish.

Once the above command succeeds, get the URL for it by issuing this command:

```
ibmcloud fn action get quarkustest --url 
```

This should return something like this:

```
https://us-south.functions.cloud.ibm.com/api/v1/web/...../default/quarkustest
```

Pull this up in a web browser! Try some of the endpoints you created, they should all work (for example https://..../hello/multiple).

> Note that you can not use the standard "fn action invoke ..." command with this special proxy Apache OpenWhisk docker image. Only Web actions are allowed. 
> You can, however use a curl / postman / etc call!

### EXTRA CREDIT
Invoke the new JSON enabled endpoint  you did in exercise 1 against both the local and Cloud Fn instance using curl.


<p  align="center">
	<font size="4">
 		<a href="../exercise1/"><< Back</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../exercise3/">Next >></a></td>
 </font>
</p>