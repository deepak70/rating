package com.ates.rating.app.exception.handler;

public class ResourceAlreadyExistsHandler extends RuntimeException{
    public ResourceAlreadyExistsHandler(String message) {
        super(message);
    }
}
