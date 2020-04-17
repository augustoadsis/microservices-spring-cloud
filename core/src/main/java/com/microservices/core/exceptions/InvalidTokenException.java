package com.microservices.core.exceptions;

import java.io.Serializable;

public class InvalidTokenException extends RuntimeException implements Serializable {

    public InvalidTokenException(String message) {
        super(message);
    }
}
