package com.dmiche_weather.demo.weather_demo.service;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherServiceTest {

    WeatherService weatherService;

    @Test
    public void getWeatherByCityName() throws Exception{
        String cityName = "Oulu";

        String responceName = requestCityName(cityName);

        Assert.assertEquals(cityName, responceName);
    }

    @Test
    public void getWeatherByNonexistentCityName() throws Exception{
        String cityName = "StrangeCity";

        weatherService = new WeatherService();

        JSONObject jsonObject =  weatherService.getWeatherByCityName(cityName);
        System.out.println(jsonObject);
        String cod = "";

        if (jsonObject.has("ClientError")){
            JSONObject errorObj = jsonObject.getJSONObject("ClientError");
            if (errorObj.has("cod")){
                cod = errorObj.get("cod").toString();
            }
        }

        Assert.assertEquals("404", cod);
    }

    private String requestCityName(String cityName) throws Exception{
        weatherService = new WeatherService();

        JSONObject jsonObject =  weatherService.getWeatherByCityName(cityName);
        String name = "";

        if (jsonObject.has("current")){
            JSONObject currentObj = jsonObject.getJSONObject("current");
            if (currentObj.has("city")){
                JSONObject cityObj = currentObj.getJSONObject("city");
                if (cityObj.has("name")){
                    name = cityObj.get("name").toString();
                }
            }
        }

        return name;
    }

}