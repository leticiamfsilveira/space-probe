package com.elo7.space_probe.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção personalizada para indicar que um recurso não foi encontrado.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidMovementException extends RuntimeException {

    public InvalidMovementException(String message) {
        super(message);
    }

    public InvalidMovementException(String message, Throwable cause) {
        super(message, cause);
    }
}
