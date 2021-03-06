package com.dmiche_weather.demo.weather_demo.resource;

import com.dmiche_weather.demo.weather_demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import org.json.JSONObject;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherResource {

    private final WeatherService weatherService;

    @Autowired
    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @CrossOrigin
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        path = "{city_name}"
    )
    public String getCityByName(@PathVariable("city_name") String cityName) throws IOException {

        JSONObject soapDatainJsonObject = weatherService.getWeatherByCityName(cityName);

        return soapDatainJsonObject.toString();
    }
}
