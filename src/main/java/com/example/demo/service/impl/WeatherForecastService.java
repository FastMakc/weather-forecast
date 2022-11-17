package com.example.demo.service.impl;

import com.example.demo.config.ClientConfig;
import com.example.demo.model.remote.RemoteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class WeatherForecastService implements com.example.demo.service.WeatherForecastService {

    private final WebClient webClient;
    private final ClientConfig clientConfig;

    @Override public Mono<RemoteResponse> getForecast(double lat, double lon) {

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/data/2.5/weather")
                                .queryParam("lat", lat)
                                .queryParam("lon", lon)
                                .queryParam("appid", clientConfig.getApiKey())
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(RemoteResponse.class);

    }

    @Override
    public Mono<RemoteResponse> getPlace(String city) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/data/2.5/weather")
                                .queryParam("q", city)
                                .queryParam("limit", 5)
                                .queryParam("appid", clientConfig.getApiKey())
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(RemoteResponse.class);
    }


}
