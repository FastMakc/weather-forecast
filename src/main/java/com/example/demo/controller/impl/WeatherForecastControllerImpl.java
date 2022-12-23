package com.example.demo.controller.impl;

import com.example.demo.config.ClientConfig;
import com.example.demo.controller.WeatherForecastController;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.service.impl.WeatherForecastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.example.model.remote.RemoteResponsePollutio;
import org.example.model.remote.RemoteResponsePollution;
import org.example.model.remote.RemoteResponseWeather;
import org.example.model.user.UserRequest;
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
    public Mono<RemoteResponseWeather> getWeatherForecast(@RequestBody UserRequest request) {

        log.info("Request received: " + request);
        log.info("API key used: " + clientConfig.getApiKey());

        if(request.getLat() == 0.0) {
            throw new IllegalArgumentException("Please check lat and lon parameters");
        }

        return weatherForecastService.getForecast(request.getLat(), request.getLon());
    }

    @Override
    @PostMapping(value ="/jsonPlace", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RemoteResponseWeather> getWeatherPlace(@RequestBody UserRequest request) {
        log.info("Request received: " + request);
        log.info("API key used: " + clientConfig.getApiKey());

        return weatherForecastService.getPlace(request.getCity());
    }

    @Override
    @Operation(summary = "returns pollution info", description = "dsjdfdfjglfjghlfgjhlfgjhlfgjhfjgh")
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = RemoteResponsePollutio.class))),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/jsonPollution", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RemoteResponsePollution> getPollution(@RequestBody UserRequest request) {

        log.info("Request received: " + request);
        log.info("API key used: " + clientConfig.getApiKey());

        if(request.getLat() == 0.0) {
            throw new IllegalArgumentException("Please check lat and lon parameters");
        }

        return weatherForecastService.getPollution(request.getLat(), request.getLon());
    }
    }


