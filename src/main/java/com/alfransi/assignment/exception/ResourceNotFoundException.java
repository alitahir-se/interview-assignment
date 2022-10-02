package com.alfransi.assignment.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
