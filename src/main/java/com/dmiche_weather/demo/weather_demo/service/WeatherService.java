package com.dmiche_weather.demo.weather_demo.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

@Service
public class WeatherService {
    private String apiUri = "http://api.openweathermap.org/data/2.5";
    private String APPID = "a48e44e536a2a6c23b6c1253e13d79ed";
    private String charset = "UTF-8";
    private String mode = "xml";
    private String units = "metric";

    public JSONObject getWeatherByCityName(String cityName) throws IOException {
        String responce = sendRequestToOpenweathermap(formatUri("weather", cityName));

        JSONObject responceJsonObject = XML.toJSONObject(responce);
        return responceJsonObject;
    }

    public JSONObject getFiveDayForecastByCityName(String cityName) throws IOException {
        String responce = sendRequestToOpenweathermap(formatUri("forecast", cityName));

        JSONObject responceJsonObject = XML.toJSONObject(responce);
        return responceJsonObject;
    }

    private String formatUri(String resource, String cityName) throws IOException
    {
        String url = String.format("%s/%s",this.apiUri, resource);
        String query = String.format("q=%s&APPID=%s&mode=%s&units=%s",
                URLEncoder.encode(cityName, charset),
                URLEncoder.encode(this.APPID, charset),
                URLEncoder.encode(this.mode, charset),
                URLEncoder.encode(this.units, charset));

        return url + "?" + query;
    }

    private String sendRequestToOpenweathermap(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();

        String responseBody = "";
        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        return responseBody;
    }
}
