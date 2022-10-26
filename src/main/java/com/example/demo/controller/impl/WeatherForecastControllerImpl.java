package com.example.demo.controller.impl;

import com.example.demo.controller.WeatherForecastController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WeatherForecastControllerImpl implements WeatherForecastController {

    @Override
    @GetMapping("/forecast")
    public void getWeatherForecast(@RequestParam(value = "city") String city, @RequestParam(value = "country") String country) {


        log.info("City is: " + city + " country is: " + country);

    }

    @Override
    @GetMapping("/forecast")
    public void getWeatherForecast(@RequestParam(value = "city") String city) {


        log.info("City is: " + city);

    }
}
