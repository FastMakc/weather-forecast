package com.example.demo.model.user;

import lombok.Data;

@Data
public class UserRequest {

    private String city;
    private String country;
    private double lon;
    private double lat;
}
