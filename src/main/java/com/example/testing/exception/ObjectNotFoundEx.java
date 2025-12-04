package com.example.testing.exception;

public class ObjectNotFoundEx extends RuntimeException {
    private final Integer status;
    public ObjectNotFoundEx(String message) {
        super(message);
        this.status = 404;
    }

    public Integer getStatus() {
        return status;
    }
}
