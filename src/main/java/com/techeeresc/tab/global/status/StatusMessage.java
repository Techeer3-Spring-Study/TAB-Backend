package com.techeeresc.tab.global.status;

import lombok.Getter;

@Getter
public enum StatusMessage {
    OK("Request success"),
    CREATED("Request success and created"),
    CONFLICT("This user already exists"),
    BAD_REQUEST_ABOUT_PARAMETER_MISSING("Parameter is missing"),
    BAD_REQUEST_ABOUT_TYPE_MISMATCH("Parameter type mismatch"),
    NOT_FOUND("Request Not found");

    String statusMessage;

    StatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
