package org.example.blog.exception;

public class CheckedValidationException extends Exception {
    public CheckedValidationException(String message) {
        super(message);
    }

    public CheckedValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedValidationException(Throwable cause) {
        super(cause);
    }

    public CheckedValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
