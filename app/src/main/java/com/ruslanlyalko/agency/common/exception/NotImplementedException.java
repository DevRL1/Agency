package com.ruslanlyalko.agency.common.exception;

public class NotImplementedException extends RuntimeException {

    private final String mMessage;
    private final Class<?> mClassToImpl;

    public NotImplementedException(final String message, final Class<?> classToImpl) {
        mMessage = message;
        mClassToImpl = classToImpl;
    }
}