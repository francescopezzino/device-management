# Devices Management APIs

REST API capable of persisting and managing device resources.

## Device Domain
- Id
- Name
- Brand
- State (available, in-use, inactive)
- Creation time
## Supported functionality
- Create a new device
- Fully and/or partially update an existing device
- Fetch a single device
- Fetch all devices
- Fetch devices by brand
- Fetch devices by state
- Delete a single device

## Requirements

 - Java 21
 - Maven 3.9+
 - Git
 - MySQL Server Instance

## Spring Framework
SpringBoot

spring boot starters dependencies

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

## Building the project

Git
Clone the project and use Maven to build the server

$ mvn clean install

## OpenAPI definition
Using Swagger UI is possible to test the various endpoints

Open your browser at the following URL for Swagger UI:
 - http://localhost:8080/swagger-ui/index.htm  

### device-controller endpoints exposed
- GET /api/v1/devices/{id}

- PUT /api/v1/devices/{id}

- DELETE /api/v1/devices/{id}

- POST /api/v1/devices/createNew

- GET /api/v1/devices/fetchAll

- GET /api/v1/devices/fetchAllByState

- GET /api/v1/devices/fetchAllByBrand

### POST

```
curl -X 'POST' \
  'http://localhost:8080/api/v1/devices/createNew' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string",
  "brand": "string",
  "state": "ACTIVE"
}'
```
##### Request body
```
{
"name": "string",
"brand": "string",
"state": "ACTIVE"
}
```
##### Response body
```
{
  "id": 5,
  "name": "string",
  "brand": "string",
  "state": "ACTIVE",
  "creationTime": "2025-09-17T22:06:54.500+00:00"
}

```
##### Response headers
```
 connection: keep-alive 
 content-type: application/json 
 date: Wed,17 Sep 2025 22:06:54 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 
```
### GET

```
curl -X 'GET' \
  'http://localhost:8080/api/v1/devices/5' \
  -H 'accept: */*'
```
##### Request URL
```
http://localhost:8080/api/v1/devices/5
```
##### Response body
```
{
  "id": 5,
  "name": "string",
  "brand": "string",
  "state": "ACTIVE",
  "creationTime": "2025-09-17T22:06:54.500+00:00"
}
```
##### Response headers
```
connection: keep-alive 
 content-type: application/json 
 date: Wed,17 Sep 2025 22:15:08 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 
```

### Schemas
#### DeviceDTO
- idread-only     integer
- name            string
- brand           string
- stateExpand all string
- creationTime    read-only (stringdate-time)

```json

{
"id": 1,
"name": "string",
"brand": "string",
"state": "ACTIVE",
"creationTime": "2025-09-17T20:50:27.874+00:00"
}

```

## Application Containerization
The build-image goal requires access to a Docker daemon.  
Image can be built using the one the following maven goal: 

```

mvn spring-boot:build-image  

```

or  
```

mvn spring-boot:build-image-no-fork  

```



Refer to the following Spring Boot documentation:  
- https://docs.spring.io/spring-boot/maven-plugin/build-image.html
-  https://dzone.com/articles/webflux-reactive-programming-with-spring-part-3

## Enabled Actuator Metrics

- Endpoints enabled: http://localhost:8080/actuator
- health: http://localhost:8080/actuator/health
- metrics: http://localhost:8080/actuator/metrics
- info: http://localhost:8080/actuator/info
- env: http://localhost:8080/actuator/env
- beans: http://localhost:8080/actuator/beans
- configprops: http://localhost:8080/actuator/configprops

Production-ready Features reference: 
https://docs.spring.io/spring-boot/reference/actuator/endpoints.html