# Java Case Study – Weather forecast REST End Point (Finleap)

The repository contains code segments related to a case study on implementing a REST-ful web service for exposing weather forecast (temperature and pressure related) details to the end user. Open weather map (https://openweathermap.org/) is the data resource, where the forecast related details are gathered. All the gathered weather forecast details are processed within the application and the necessary details related to temperature and pressure measurements will be routed to the end user.

## Programming languages, technologies and frameworks used

Java 8, Springboot 1.4.2, Maven 3.6, Swagger 2.6.1, jackson-databind 2.8.4

## How to Run

The project is a springboot application which is developed upon maven. Please follow these steps to configure and run the project.

1. Open the project directory. Execute the maven installation command in the pom.xml placed directory (mvn clean install)
2. Import the project into an IDE.
3. WeatherForecastApplication.java contains the main method. Execute this file through IDE
4. Once the server is started, you will be able to access the REST end point using http://localhost:8080/data/{cityName} url (Application runs on the default port 8080)
5. Please note that you might need to connect through Postman or Swagger or any other REST client to verify the endpoint
6. In order the execute the tests separately, you could use the IDE, or execute the mvn test or mvn clean install commands

## Performed Tasks

1. Implementation is completed based on the requirement document
2. Wrote tests covering all the services and REST controllers
3. API usage documentation (Swagger is included in the project)
4. Has followed the development best practices during the implementation

## Project Structure

The project consist of several main packages. (config - Configuration files (Swagger), constants - Contains the project static type constant variables, controller - is for placing the rest controller classes, exceptionhandler - related to exception handling approaches, model - contains the POJO objects, service - contains the classes of which include business logics of the application, util - contains utility related classes, test - contains tests related to the business logic and API controller classes, appication.properties - external API end point, API key and related details are placed)

## Motivations and Challenges faced

Basic requirement is to forecast the average temperature and pressure values for next 3 days for a given city. Basic 2 APIs were there for which can be used for this task. 5 day / 3 hour forecast API and 16 day / daily forecast API. Although the mentioned requirement can be easily rendered with a minimal effort if the 16 day / daily forecast API is used, it cannot be subscribed for free. Therefore I selected the 5 day/ 3 hour forecast API to retrieve data and a post processing phase is implemented within the application to render the required data for routing.

5 day / 3 hour API returns altogether 40 data rows for a given city. But since the forecasting is required to be done for 3 days, there is an initial filtering performed using date ranges (3 day range). After the filtering, the resulted data stream is used for the next level filtering which is to filter average pressure values and the temperature values. Then the application delegates the filterings into basic 2 services which are implemented for filtering pressure and temperature values separately. Finally the returned result values of those services are gathered together and returned it back as the end point as a single response. 

retrieving the average value of pressure is much easier than the temperature, since temperature was required to be filtered out based on the morning and night sessions. And most importantly, average value was required to be grouped based on the morning and night sessions. Java 8 stream API was used in all the filtering.

## Assumptions

1. There were 2 temperature values defined in the open weather map API (minimum temperature and the maximum temperature). In order to get a much more accurate value for the temperature, average of the maximum and the minimum has been considered as the actual temperature at that time.

2. I have assumed that the morning time is exactly as 6:00 to 17:59 and night time is exactly as 18:00 to 5:59 during the temperature average calculations.

3. I have rounded the temperature and pressure values into 2 decimal points before routing it to the client side.













