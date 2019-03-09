# Java Case Study – Weather forecast REST End Point (Finleap)

The repository contains code segments related to a case study on implementing a REST-ful web service for exposing weather forecast (temperature and pressure related) details to the end user. Open weather map (https://openweathermap.org/) is basically the data resource, where the forecast related details are retrieved. The application processes the incoming data and finally the necessary details related to temperature and pressure measurements will be forecasted to the end user.

## Programming languages, technologies and frameworks used

Java 8, Springboot 1.4.2, Maven 3.6, Swagger 2.6.1, jackson-databind 2.8.4

## How to Run

The project a springboot application which is developed upon maven. Please follow these steps to configure and run the project 

1. Open the project directory. Go the location where the pom.xml file is placed. Build the project using terminal. (mvn clean install)
2. Import the project into an IDE.
3. WeatherForecastApplication.java contains the main method. Execute this file through IDE
4. Once the server is started, you will be able to access the REST end point using http://localhost:8080/data/{cityName} (The application runs on the default port)
5. In order the execute the tests specifically, you could use the IDE, or execute the mvn test/ mvn clean install command


## Project Structure

The project consist of several main packages. (config - Configuration files (Swagger), constants - Contains the project static type constant variables, controller - is for placing the rest controller classes, exceptionhandler - related to exception handling approaches, model - contains the POJO objects, service - contains the business logics of the application, util - contains utility related classes, test - contains tests related to the business logic and API controller classes, appication.properties - external API end point related details are placed)

## Motivations, Challenges faced

