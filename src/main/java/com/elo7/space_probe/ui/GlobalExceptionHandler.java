package com.elo7.space_probe.ui;

import com.elo7.space_probe.app.exceptions.InvalidMovementException;
import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseStatus(value = BAD_REQUEST)
    ErrorMessageDTO handleException(Exception exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    ErrorMessageDTO handleException(ResourceNotFoundException exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(InvalidMovementException.class)
    @ResponseStatus(value = CONFLICT)
    ErrorMessageDTO handleException(InvalidMovementException exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), CONFLICT);
    }
}
