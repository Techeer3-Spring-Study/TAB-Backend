package com.techeeresc.tab.global.exception.response;

import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private int errorCode;
    private String message;

    public ErrorResponse(StatusCodes statusCodes, StatusMessage statusMessage) {
        this.errorCode = statusCodes.getStatusCode();
        this.message = statusMessage.getStatusMessage();
    }
}
