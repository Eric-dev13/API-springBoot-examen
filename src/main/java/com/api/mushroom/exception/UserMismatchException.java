package com.api.mushroom.exception;

public class UserMismatchException extends RuntimeException {
    public UserMismatchException(String message) {
        super(message);
    }
}
