package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(RuntimeException.class) // ukazivaetsa klass iskluchenija, dlja kotorogo budeet vipolnene dannij kod
    @PostMapping(produces = "application/json") // otpravlaetsa JSON objekt polzovatelju
    ResponseEntity handleRuntimeException(Exception e){
        String errorMessage = e.getMessage();
        return new ResponseEntity(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class) // ukazivaetsa klass iskluchenija, dlja kotorogo budeet vipolnene dannij kod
    @PostMapping(produces = "application/json") // otpravlaetsa JSON objekt polzovatelju
    ResponseEntity handleIllegalArgumentException(Exception e){
        String errorMessage = e.getMessage();
        return new ResponseEntity(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Unauthorized.class) // ukazivaetsa klass iskluchenija, dlja kotorogo budeet vipolnene dannij kod
    @PostMapping(produces = "application/json") // otpravlaetsa JSON objekt polzovatelju
    ResponseEntity handleUnauthorized(Exception e){
        String errorMessage = e.getMessage();
        return new ResponseEntity(new ErrorResponse(errorMessage), HttpStatus.UNAUTHORIZED);
    }

}
