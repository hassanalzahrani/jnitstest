package com.example.springsecurity.ControllerAdviser;

import com.example.springsecurity.API.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdviser  {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){


        return ResponseEntity.status(400).body(e.getMessage());
    }
}
