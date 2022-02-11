
## Running the tests:
#### From the test Runner

```sh
# Locate the testRunner in the src/test/java/testrunners/AmazonSearchTest.java folder
# Rigth click and run (This depends on the IDE you are using)
```
#### From command line
```
# In the proyect folder, open a coomand line and run:
mvn clean test
```
#### You can run the test in Chrome and firefox, changing the browser in the properties file
- Properties file folder: src/test/resources/config/test.properties
```sh
# In the .properties file you can see this options
browser=chrome
headless=false
# Change them accordingly to the needs
```

## Logs
After the test finish, you can find the logs in the target folder: target/classes/logs/test.log