package sn.woy.exception;

/**
 * Signale que la salle demandee n'existe pas.
 */
public class SalleIntrouvableException extends RuntimeException {

    public SalleIntrouvableException(String message) {
        super(message);
    }
}