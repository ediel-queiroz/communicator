# Communicator Project

###### To execute all the commands below you will need to be in the project's root folder

## Build

`mvn clean install`

## Test

`mvn clean test`

## Run with maven

##### In this case, the database used is H2 (in memory database)

`mvn spring-boot:run`

## Run in containers using Docker and Docker Compose

###### In this case, the database used is PostgreSQL. **Note:** Docker and Docker Compose are necessary.

`mvn clean install`

`docker-compose -p communicator -f docker-compose.yaml up --build`

## API Documentation

###### When your application is running, go to the URL's below for Swagger documentation

###### Swagger API Docs
http://localhost:8080/v2/api-docs

###### Swagger UI
http://localhost:8080/swagger-ui.html


## Request examples using cURL:

###### To send a scheduling

`curl -v -X POST -H 'Content-Type: application/json' -d '{"date_time":"2020-09-26T10:15:30","messages":[{"type":"EMAIL","sender":"test@sender.com.br","recipient":"test@recipient.com.br","subject":"Email Test","content":"I am testing my email scheduling"}]}' http://localhost:8080/v1/schedules`

###### OBS: The available types are EMAIL, SMS, PUSH and WHATSAPP

###### To get a scheduling

`curl -v http://localhost:8080/v1/schedules/1`

###### To fetch all schedules

`curl -v http://localhost:8080/v1/schedules`

###### To delete a scheduling

`curl -v -X DELETE http://localhost:8080/v1/schedules/1`
