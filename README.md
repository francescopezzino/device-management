# Devices API

REST API capable of persis:ng and managing device resources.

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

 - JavA 21
 - Maven 3.9+
 - Git
 - MySQL Server Instance

## Spring Framework
SpringBoot
A CloudFoundry account (to deploy the server)

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

## Building the project
You will need:

Java JDK 8 or higher
Maven 3.1.1 or higher
Git
Clone the project and use Maven to build the server

$ mvn clean install

Deploying to the cloud
You can deploy the server to CloudFoundry using:

## OpenAPI definition
Open your browser at the following URL for Swagger UI (giving REST interface details):
 - http://localhost:8080/swagger-ui/index.htm  
Using Swagger UI is possible to test the endpoint
### device-controller endpoints exposed
- GET /api/v1/devices/{id}

- PUT /api/v1/devices/{id}

- DELETE
- /api/v1/devices/{id}

- POST /api/v1/devices/createNew

- GET /api/v1/devices/fetchAll

- GET /api/v1/devices/fetchAllByState

- GET /api/v1/devices/fetchAllByBrand


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
Image can be built using the mavent goal build-image  
The build-image goal requires access to a Docker daemon.  
Refer to the following Spring Boot documentation:  
- https://docs.spring.io/spring-boot/maven-plugin/build-image.html

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