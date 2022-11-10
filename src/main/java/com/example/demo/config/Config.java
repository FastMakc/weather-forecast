package com.example.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.security.InvalidParameterException;

@Configuration
@AllArgsConstructor
public class Config {

    private final Environment env;

    @Bean
    String apiKey() {

        String key = env.getProperty("api.key");

        if(key != null && !key.isEmpty()) {
            return key;
        }
        else {
            throw new InvalidParameterException("Could not load API key!!!");
        }

    }

    @Bean
    ClientConfig clientConfig() {
        return new ClientConfig(apiKey());
    }
}
