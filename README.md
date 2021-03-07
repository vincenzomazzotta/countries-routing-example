# example application

## Description
The application based on SpringBoot when start application recover data from external link by RestTemplate and load
in ConcurrentSkipListMap to manage concurrence by get and put rest api.
I have integrated openAPI to describe rest API.
Basic Auth is done by Spring Authentication

##REST API
- authenticated by basic auth
- See any details by of REST API by openAPI below link. 


## URL

- Back End:
    - http://localhost:8080/rest/v1

- Front End:
    - http://localhost:8080/homepage

## Technologies

Here is a list of basic technologies used in the project:

- Java 8
- SpringBoot
- Maven
- Tomcat
- OpenAPI

### Prerequisites

- git
- java SE 8.8
- maven

### Local run for development

- Clone the repo
- Run ExampleApplication.java in your IDE (I used IntelliJ)

#### Run via command line inputs

- move in project root directory: `\example`
- create target folder: `mvn clean package -DskipTests`
- `-DskipTests` command is camel case
- deploy war on Tomcat
- snapshot version is taken from the pom.xml file

## API Description
### Swagger openAPI
- http://localhost:8080/swagger-ui-custom.html

