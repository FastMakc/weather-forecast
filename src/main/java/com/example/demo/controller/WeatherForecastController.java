package com.example.demo.controller;

import com.example.demo.model.user.UserRequest;

public interface WeatherForecastController {

    void getWeatherForecast(String city, String country);

    void getWeatherForecast(UserRequest request);


}
