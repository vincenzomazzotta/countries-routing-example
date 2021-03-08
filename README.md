# example application

<span style="vertical-align: middle;"><img src="https://www.bandiere-mondo.it/data/flags/emoji/facebook/256x256/it.png" width="10%" height="10%" alt="Traduci questo documento in Italiano">[Traduci questo documento in Italiano](http://translate.google.com/translate?js=n&sl=auto&tl=it-IT&u=https://raw.githubusercontent.com/vincenzomazzotta/countries-routing-example/master/README.md)

## Preface
Compared to the big italian bank, the test is slightly more demanding especially because it is very long and covers many areas from the multithreading to REST API to MVC to Templating to Unit Tests to Integration ...
It is complete.
This application was done in about 5-6 hours during 7 and 8 March 2021 during the night as the weekend is dedicated to my daughters and is sacred.

## Description
The application based on SpringBoot when start application recover data from external link by RestTemplate (spring Integration is write only xml outbound gateway) and load
in a ConcurrentSkipListMap object (to allow to manage concurrence call and because this data structure mantains the order of insert) by get and put rest api.

- It have integrated openAPI swagger to describe rest API.
- It have integrate Basic Auth by Spring Authentication only basic authentication for Rest Backend
- It have frontend website by Spring MVC using templating by Thymeleaf
- It have unit test by Sprigboot test (only authenticate and not authenticate backend API rest tests)
- It have backend REST API to Set capital for a Country to update a capital of country and web page to do it
- It haven''t Spring Integration (write only http-outbound-gateway.xml) I lost time because it's too long more of 5-6 hours.

##REST API
- authenticated by basic auth
- See any REST API details by [openAPI swagger](http://localhost:8080/swagger-ui-custom.html) when launch the application. 

## URL

- Back End:
    - http://localhost:8080/rest/v1/all
    - http://localhost:8080/rest/v1/changeCapital
    - you can find username and password in application.properties

- Front End:
    - http://localhost:8080/setCapital that use another [REST API backend](http://localhost:8080/rest/v1/getCountryList) to get all countriest to load in select options
    - http://localhost:8080/allCountries

## Technologies

Here is a list of basic technologies used in the project:

- Java 8
- SpringBoot 2.5.0
- Maven
- Tomcat
- OpenAPI
- SpringBoot Unit Test (calling rest API check 200 OK and calling without authentication check 401 UNAUTHORIZED)
- **Spring Integration (I didn't have time to do it, tomorrow I will work :)** but I defined the http-outbound-gateway it's needed to change Rest call on PostCostructor service class to recover data from external superset and then transformate them.)

### Prerequisites

- git
- java 8.0
- maven

### Local run for development

- Clone the repo
- Run ExampleApplication.java in your IDE (I used IntelliJ)

#### Run via command line inputs

- move in project root directory: `\example`
- create target folder: `mvn clean package -DskipTests`
- remember that `-DskipTests` command it's in camel case
- deploy war on Tomcat
- snapshot version is taken from the pom.xml file

## API Description by Swagger openAPI
- http://localhost:8080/swagger-ui-custom.html

