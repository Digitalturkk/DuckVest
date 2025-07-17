package com.DuckVest.Exceptions.UserAuthExceptions;

public class InvalidUserPasswordException extends RuntimeException {

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
