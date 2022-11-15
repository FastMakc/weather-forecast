package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientConfig {

    private final String apiKey;
    private final String baseUrl;
}
