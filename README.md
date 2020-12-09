# How to run

## Requirements
- Back end: Maven and Java 8 (latest version)
- Front end: npm, Axios and Vue (can be installed from npm packages)

## Back end
First, compile the back-end application
```
cd coding-app/pagacoins
mvn clean install
```
Run the application jar
```
cd target
java -jar pagacoins-0.0.1-SNAPSHOT.jar
```
To run the unit tests only, from the /pagacoins folder, run:
```
mvn clean test
``` 
Since the app uses an instance of an in-memory database (H2), it is embedded in the app and will load sample data when starting up. To add more data, edit the data.sql file in pagacoins/src/main/resources/data.sql

The API can be accessed from localhost:8000

## Front end
In a different terminal/cmd window or tab, first, download the required modules
```
cd coding-app/coding-app-ui
npm install
``` 
To run the UI app:
```
npm run serve
```
UI web page can be accessed from localhost:8080
