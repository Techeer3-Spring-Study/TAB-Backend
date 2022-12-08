package com.techeeresc.tab.domain.post.exception;

public class NotFoundException extends NullPointerException {
    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }
}
