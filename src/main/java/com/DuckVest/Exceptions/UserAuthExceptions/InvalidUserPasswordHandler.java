package com.DuckVest.Exceptions.UserAuthExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidUserPasswordHandler {

    @ExceptionHandler(value = {InvalidUserPasswordException.class})
    public ResponseEntity<Object> handelInvalidUserPassword (InvalidUserPasswordException exception) {
        InvalidUserPassword invalidUserPassword = new InvalidUserPassword(
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(invalidUserPassword, HttpStatus.UNAUTHORIZED);
    }
}
