package com.example.demo.controller.impl;

import com.example.demo.config.ClientConfig;
import com.example.demo.controller.WeatherForecastController;
import com.example.demo.model.remote.RemoteResponse;
import com.example.demo.model.user.UserRequest;
import com.example.demo.service.impl.WeatherForecastService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class WeatherForecastControllerImpl implements WeatherForecastController {


    @Autowired
    ClientConfig clientConfig;
    @Autowired
    WeatherForecastService weatherForecastService;

    @Override
    @GetMapping("/forecast")
    public void getWeatherForecast(@RequestParam(value = "city") String city, @RequestParam(value = "country") String country) {

        log.info("City is: " + city + " country is: " + country);

    }

    @Override
    @PostMapping(value = "/jsonForecast", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RemoteResponse> getWeatherForecast(@RequestBody UserRequest request) {

        log.info("Request received: " + request);
        log.info("API key used: " + clientConfig.getApiKey());

        return weatherForecastService.getForecast(request.getLat(), request.getLon());
    }

}
