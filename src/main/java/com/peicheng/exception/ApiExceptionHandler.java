package com.peicheng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiException(
            ApiRequestException apiRequestException
    ){
        ApiException apiException = new ApiException(
                apiRequestException.getMessage(),
                apiRequestException,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleApiException(
            NotFoundException notFoundException
    ){
        ApiException apiException = new ApiException(
                notFoundException.getMessage(),
                notFoundException,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(
                apiException,
                HttpStatus.NOT_FOUND
        );
    }
}
