package dev.arman.bookmyshow.exceptions;

/**
 * @author mdarmanansari
 */
public class InvalidResponseException extends RuntimeException{
    public InvalidResponseException() {
        super();
    }

    public InvalidResponseException(String message) {
        super(message);
    }

    public InvalidResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResponseException(Throwable cause) {
        super(cause);
    }

    protected InvalidResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
