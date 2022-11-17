package com.example.demo.controller;

import com.example.demo.model.remote.RemoteResponse;
import com.example.demo.model.user.UserRequest;
import reactor.core.publisher.Mono;

public interface WeatherForecastController {

    void getWeatherForecast(String city, String country);

    Mono<RemoteResponse> getWeatherForecast(UserRequest request);
    Mono<RemoteResponse> getWeatherPlace(UserRequest request);


}
