package com.pvb.springboot.veeru.weather.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*") // allow frontend access
public class WeatherController {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<?> getWeatherData(@RequestParam String city) {
        try {
            // Fetch current weather
            String currentWeatherUrl = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                city, apiKey
            );
            Map<String, Object> currentWeather = restTemplate.getForObject(currentWeatherUrl, HashMap.class);

            // Fetch 5-day forecast
            String forecastUrl = String.format(
                "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric",
                city, apiKey
            );
            Map<String, Object> forecastData = restTemplate.getForObject(forecastUrl, HashMap.class);

            // Pack both into a single response
            Map<String, Object> response = new HashMap<>();
            response.put("current", currentWeather);
            response.put("forecast", forecastData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found or error occurred!");
        }
    }
}
