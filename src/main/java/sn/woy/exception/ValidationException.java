package sn.woy.exception;

/**
 * Signale qu'une donnee fournie par l'appelant est invalide.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}