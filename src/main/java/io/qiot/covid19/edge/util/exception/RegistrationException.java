/**
 * 
 */
package io.qiot.covid19.edge.util.exception;

/**
 * @author andreabattaglia
 *
 */
public class RegistrationException extends Exception {

    public RegistrationException() {
        super();
    }

    public RegistrationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable cause) {
        super(cause);
    }

}
