package com.dmiche_weather.demo.weather_demo.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.XML;



@RestController
@RequestMapping("api/v1/weather")
public class WeatherResource {

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAllStudents(){
        return "{'osdoodsoso':'44444'}";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        path = "{city_name}"
    )
    public String getCityByName(@PathVariable("city_name") String cityName) throws IOException {
        String url = "http://api.openweathermap.org/data/2.5/weather";
        String charset = "UTF-8";
        String param1 = cityName;//"London";
        String param2 = "a48e44e536a2a6c23b6c1253e13d79ed";
        String param3 = "xml";
        String param4 = "metric";

        String query = String.format("q=%s&APPID=%s&mode=%s&units=%s",
                URLEncoder.encode(param1, charset),
                URLEncoder.encode(param2, charset),
                URLEncoder.encode(param3, charset),
                URLEncoder.encode(param4, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }

        JSONObject soapDatainJsonObject = XML.toJSONObject(responseBody);

        return soapDatainJsonObject.toString();// "{'osdoodsoso':'323'}";
    }
}
