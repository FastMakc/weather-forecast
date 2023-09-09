package com.example.demo.controller;

import org.example.model.remote.RemoteResponsePollution;
import org.example.model.remote.RemoteResponseWeather;
import org.example.model.user.UserRequest;
import reactor.core.publisher.Mono;

public interface WeatherForecastController {

    void getWeatherForecast(String city, String country);

    Mono<RemoteResponseWeather> getWeatherForecast(UserRequest request);
    Mono<RemoteResponseWeather> getWeatherPlace(UserRequest request);

    Mono<RemoteResponsePollution> getPollution(UserRequest request);
}
