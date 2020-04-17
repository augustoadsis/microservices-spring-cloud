package com.microservices.core.exceptions;

import java.io.Serializable;

public class NoContentException extends RuntimeException implements Serializable {

    public NoContentException(String message) {
        super(message);
    }
}
