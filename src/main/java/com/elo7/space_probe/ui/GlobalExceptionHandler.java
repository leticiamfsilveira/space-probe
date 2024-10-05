package com.elo7.space_probe.ui;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseStatus(value = BAD_REQUEST)
    ErrorMessageDTO handleException(Exception exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), BAD_REQUEST);
    }
}
