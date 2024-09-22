package dev.arman.bookmyshow.exceptions;

/**
 * @author mdarmanansari
 */
public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException() {
        super();
    }

    public ShowNotFoundException(String message) {
        super(message);
    }

    public ShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShowNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ShowNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
