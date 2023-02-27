package com.ates.rating.app.exception.handler;

public class UnknownResourceException extends RuntimeException{
    public UnknownResourceException(String message) {
        super(message);
    }
}
