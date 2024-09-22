package dev.arman.bookmyshow.exceptions;

/**
 * @author mdarmanansari
 */
public class ShowSeatNotFoundException extends RuntimeException {
    public ShowSeatNotFoundException() {
        super();
    }

    public ShowSeatNotFoundException(String message) {
        super(message);
    }

    public ShowSeatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShowSeatNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ShowSeatNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
