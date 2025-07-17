package com.DuckVest.Exceptions.UserAuthExceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserPassword {
    String message;
    HttpStatus httpStatus;
    int statusCode;

    public InvalidUserPassword(String message, HttpStatus httpStatus, int statusCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
