package com.example.demo.service;

import com.example.demo.model.remote.RemoteResponse;
import reactor.core.publisher.Mono;

public interface WeatherForecastService {

    Mono<RemoteResponse> getForecast(double lat, double lon); // Mono 0..1; Flux 0..n
}
