package com.DuckVest.Exceptions.GlobalNotFound;

public class GlobalNotFoundException extends RuntimeException {

    public GlobalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
