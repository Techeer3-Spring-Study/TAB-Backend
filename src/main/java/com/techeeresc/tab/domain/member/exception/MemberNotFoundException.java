package com.techeeresc.tab.domain.member.exception;

import com.techeeresc.tab.global.status.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {
    private int errorCode;

    public MemberNotFoundException(String message, StatusCodes statusCodes) {
        super(message);
        this.errorCode = statusCodes.getStatusCode();
    }
}