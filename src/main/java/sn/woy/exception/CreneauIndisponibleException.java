package sn.woy.exception;

/**
 * Signale que le creneau demande chevauche une reservation existante.
 */
public class CreneauIndisponibleException extends RuntimeException {

    public CreneauIndisponibleException(String message) {
        super(message);
    }
}