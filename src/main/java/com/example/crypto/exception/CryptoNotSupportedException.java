package com.example.crypto.exception;

import static java.lang.String.format;

public class CryptoNotSupportedException extends RuntimeException {

    public CryptoNotSupportedException(String entity, String name) {
        super(format("%s with name %s is not supported", entity, name));
    }

    public CryptoNotSupportedException(Class entityClass, String name) {
        this(entityClass.getSimpleName(), name);
    }
}
