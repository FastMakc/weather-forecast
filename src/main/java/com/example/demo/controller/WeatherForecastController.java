package com.example.demo.controller;

import com.example.demo.model.remote.RemoteResponsePollution;
import com.example.demo.model.remote.RemoteResponseWeather;
import com.example.demo.model.user.UserRequest;
import reactor.core.publisher.Mono;

public interface WeatherForecastController {

    void getWeatherForecast(String city, String country);

    Mono<RemoteResponseWeather> getWeatherForecast(UserRequest request);
    Mono<RemoteResponseWeather> getWeatherPlace(UserRequest request);

    Mono<RemoteResponsePollution> getPollution(UserRequest request);


}
