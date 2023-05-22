package com.example.demo.config;

import io.netty.handler.logging.LogLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GeneralConfig {

    private final ClientConfig clientConfig;

    @Bean
    public ClientHttpConnector getConnector(){
        HttpClient client = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.INFO,
                AdvancedByteBufFormat.TEXTUAL);
        return new ReactorClientHttpConnector(client);
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .baseUrl(clientConfig.getBaseUrl())
                .clientConnector(getConnector())
                .build();
    }
}
