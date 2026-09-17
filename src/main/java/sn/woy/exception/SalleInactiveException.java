package sn.woy.exception;

/**
 * Signale que la salle existe mais n'est pas active.
 */
public class SalleInactiveException extends RuntimeException {

    public SalleInactiveException(String message) {
        super(message);
    }
}