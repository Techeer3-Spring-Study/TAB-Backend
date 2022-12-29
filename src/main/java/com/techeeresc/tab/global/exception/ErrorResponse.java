package com.techeeresc.tab.global.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private int errorCode;
    private String message;
}
