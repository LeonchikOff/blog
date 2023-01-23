package org.example.blog.exception;

public class UncheckedSystemException extends RuntimeException {
    public UncheckedSystemException(String message) {
        super(message);
    }

    public UncheckedSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedSystemException(Throwable cause) {
        super(cause);
    }

    public UncheckedSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
