package com.norris.weather.weatherserver;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest webRequest) {
        final String exceptionBody = "Invalid zip code. Zip code must be exactly 5 digits.";
        return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleBadRequest(final Exception ex, final WebRequest webRequest) {
        final String exceptionBody = "Invalid URI. URI should be in the format http://localhost:8080/api/v1/wind/{zip code} where {zip code} is exactly 5 digits.";
        return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
