package com.example.grpc.client;

public class UserRetrieveException extends RuntimeException {

    public UserRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

}
