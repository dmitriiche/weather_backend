# weather backend app
Minimal spring boot demo project.
The app implement REST api.
Available resources is:
 - Five day forecast
 - Current weather data
## Requirements
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally
### Using IntelliJ IDEA
- Open project directory in the IntelliJ IDEA
- Go to the /src/main/java/com.dmiche_weather.demo.weather_demo/ directory
- Right click on the WeatherDemoApplication class
- Select run

### Using console
- Go to the project directory
- Run command mvn spring-boot:run

Server start at the localhost:8090


## Api usage
### Get current weather by city name
###### Api call:
http://localhost:8080/api/v1/weather/{city_name}
###### Examples:
http://localhost:8080/api/v1/weather/Oulu

http://localhost:8080/api/v1/weather/London

### Get 5 day / 3 hour forecast data by city name
###### Api call:
http://localhost:8080/api/v1/forecast/{city_name}
###### Examples:
http://localhost:8080/api/v1/forecast/Oulu

http://localhost:8080/api/v1/forecast/London