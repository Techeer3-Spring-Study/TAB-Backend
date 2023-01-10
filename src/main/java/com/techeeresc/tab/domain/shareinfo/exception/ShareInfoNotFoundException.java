package com.techeeresc.tab.domain.shareinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShareInfoNotFoundException extends RuntimeException {
    public ShareInfoNotFoundException(String message) {
        super(message);
    }
}
