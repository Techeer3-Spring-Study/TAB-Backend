package com.techeeresc.tab.global.status;

import lombok.Getter;

@Getter
public enum StatusCodes {
    OK("OK", 200),
    CREATED("CREATED", 201),
    CONFLICT("CONFLICT", 409),
    BAD_REQUEST("BAD_REQUEST", 400),
    NOT_FOUND("NOT_FOUND", 404);

    String statusCodeName;
    int statusCode;

    StatusCodes(String statusCodeName, int statusCode) {
        this.statusCodeName = statusCodeName;
        this.statusCode = statusCode;
    }
}
