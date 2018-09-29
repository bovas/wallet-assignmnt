# Wallet Service 
[Swagger UI](http://localhost:8080/swagger-ui.html)

### Clone the repository or Download the code directly and extract it to the system
```
git clone https://github.com/bovas/wallet-assignmnt.git
```

### Build and Run the app using Maven 
1. Ensure Java development kit and runtime are installed and JAVA_HOME path is set in the System.
2. Navigate to the project path
   ```
   cd wallet-assignmnt/wallet-service
   ```
3. Run the below command
   ```
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
### Interact with the app through Swagger UI
1. Click on the swagger ui link given in the header.
2. Expand the `wallet-service-rest-controller` API in the swagger ui api's list and expand the `performTransaction` operation.
3. Copy and paste the below request pay load to trigger the testing or edit the swagger provided sample payload.
```
{
  "playerId": 1,
  "transactionAmount": 15,
  "transactionId": "cf6fbae7-ef2b-4b7b-94a6-b5998853ae53",
  "transactionType": "credit"
}
```
Note: Currently there are five players already added into the application with the below data.
```
PLAYER_ID  	PLAYER_NAME  	CURRENT_BALANCE  
1         	Anders	        10000.0
2         	Solvig	        0.0
3	        Sigfrid	        10.0
4	        Eric	        100.0
5	        Edvin           50.0

```
4. Sample data can be modified by logging into [console](http://localhost:8080/h2-console) and connecting to the schema `jdbc:h2:mem:testdb`
5. Transaction id in the request payload should be a UUID(Globally Unique id), it can be generated from this [site](https://www.uuidgenerator.net/version4)

### Appendix
Please refer this page for more detailed information - [Wiki](https://github.com/bovas/wallet-assignmnt/wiki)
