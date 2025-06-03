package com.DuckVest.Exceptions.GlobalNotFound;

import org.springframework.http.HttpStatus;

public class GlobalNotFound {
    private String message;
    private Throwable cause;
    private HttpStatus httpStatus;
    private int statusCode;

    public GlobalNotFound(String message, Throwable cause, HttpStatus httpStatus, int statusCode) {
        this.message = message;
        this.cause = cause;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
