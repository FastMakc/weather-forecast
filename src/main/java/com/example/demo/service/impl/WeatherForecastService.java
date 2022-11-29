package com.example.demo.service.impl;

import com.example.demo.config.ClientConfig;
import com.example.demo.exception.Unauthorized;
import com.example.demo.model.remote.RemoteResponsePollution;
import com.example.demo.model.remote.RemoteResponsePollutio;
import com.example.demo.model.remote.RemoteResponseWeather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @Override
    public Mono<RemoteResponseWeather> getForecast(double lat, double lon) {

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
                .bodyToMono(RemoteResponseWeather.class);

    }

    @Override
    public Mono<RemoteResponseWeather> getPlace(String city) {
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
                .onStatus(httpStatus ->
                                httpStatus.equals(HttpStatus.UNAUTHORIZED),
                        errorResponse -> errorResponse.bodyToMono(String.class).map(Unauthorized::new)) //Mono<String> -> map(Mono<String)
                // (Unauthorized::new) == (lambda -> new Unautohorized(lambda))
                .onStatus(httpStatus ->
                                httpStatus.equals(HttpStatus.BAD_REQUEST),
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(RemoteResponseWeather.class);
    }

    @Override
    public Mono<RemoteResponsePollution> getPollution(double lat, double lon) {

        var response = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/data/2.5/air_pollution")
                                .queryParam("lat", lat)
                                .queryParam("lon", lon)
                                .queryParam("appid", clientConfig.getApiKey())
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(RemoteResponsePollutio.class);

       return response.map(res -> {
           return new RemoteResponsePollution(res.list.get(0).components.co, res.list.get(0).components.no,
                    res.list.get(0).components.no2, res.list.get(0).components.o3,
                    res.list.get(0).components.so2, res.list.get(0).components.pm2_5,
                    res.list.get(0).components.pm10, res.list.get(0).components.nh3);

       });

    }
}
