package com.example.springsecurity.API;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
