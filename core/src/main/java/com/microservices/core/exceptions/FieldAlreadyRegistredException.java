package com.microservices.core.exceptions;

import java.io.Serializable;

public class FieldAlreadyRegistredException extends RuntimeException implements Serializable {

    public FieldAlreadyRegistredException(String message) {
        super(message);
    }
}
