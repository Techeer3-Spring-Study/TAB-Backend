package com.techeeresc.tab.global.exception.customexception;

import com.techeeresc.tab.global.status.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {
    private int errorCode;

    public RequestNotFoundException(String message, StatusCodes statusCodes) {
        super(message);
        this.errorCode = statusCodes.getStatusCode();
    }
}
