package com.example.demo.service;

import com.example.demo.model.remote.RemoteResponsePollutio;
import com.example.demo.model.remote.RemoteResponsePollution;
import com.example.demo.model.remote.RemoteResponseWeather;
import reactor.core.publisher.Mono;

public interface WeatherForecastService {

    Mono<RemoteResponseWeather> getForecast(double lat, double lon); // Mono 0..1; Flux 0..n
    Mono<RemoteResponseWeather> getPlace(String city);
    Mono<RemoteResponsePollution> getPollution(double lat, double lon);
}
