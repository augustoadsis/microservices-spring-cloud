package com.microservices.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.util.Arrays.asList;

@Slf4j
@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 100;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return response(HttpStatus.BAD_REQUEST, "Not found", ex, request.getContextPath());
    }

    private ResponseEntity<Object> response(HttpStatus status, String message, Exception ex, String path) {
        return ResponseEntity
                .status(status)
                .body(StandartError.builder()
                        .message(message)
                        .errors(asList(ex.getMessage()))
                        .path(path)
                        .build()
                );
    }

    private ResponseEntity<Object> response(HttpStatus status, String message, String path) {
        return ResponseEntity
                .status(status)
                .body(StandartError.builder()
                        .message(message)
                        .errors(asList(message))
                        .path(path)
                        .build()
                );
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        return response(HttpStatus.NOT_FOUND, "Not found", ex, request.getRequestURI());
    }

}
