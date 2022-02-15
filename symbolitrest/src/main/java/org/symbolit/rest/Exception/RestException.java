package org.symbolit.rest.Exception;

/**
 * Classe non implémentée pour ce test
 */
public class RestException extends RuntimeException {

    protected final Exception exception;

    public RestException(Exception pException) {
        exception = pException;
    }

    public RestException(String message, Exception pException) {
        super(message);
        exception = pException;
    }

    public RestException(String message, Throwable cause, Exception pException) {
        super(message, cause);
        exception = pException;
    }

    public RestException(Throwable cause, Exception pException) {
        super(cause);
        exception = pException;
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Exception pException) {
        super(message, cause, enableSuppression, writableStackTrace);
        exception = pException;
    }

    public Exception getException() {
        return exception;
    }
}
