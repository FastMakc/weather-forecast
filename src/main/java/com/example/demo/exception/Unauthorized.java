package com.example.demo.exception;

public class Unauthorized extends RuntimeException{

    public Unauthorized(String msg){
        super(msg);
    }
}

