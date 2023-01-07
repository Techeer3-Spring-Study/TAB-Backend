package com.techeeresc.tab.global.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
<<<<<<< HEAD
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
=======
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
>>>>>>> 4719b56aa861c1ae6ed95a34390651458402cba0
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<ErrorResponse> badRequestByParameterMissingHandler(ValueInstantiationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(StatusCodes.BAD_REQUEST, StatusMessage.BAD_REQUEST_ABOUT_PARAMETER_MISSING);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundRequestHandler(RequestNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(StatusCodes.NOT_FOUND, StatusMessage.NOT_FOUND);
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> badRequestByInvalidFormatHandler(InvalidFormatException exception) {
        ErrorResponse errorResponse = new ErrorResponse(StatusCodes.BAD_REQUEST, StatusMessage.BAD_REQUEST_ABOUT_TYPE_MISMATCH);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
