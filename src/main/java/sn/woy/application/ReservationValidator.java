package sn.woy.application;

import sn.woy.exception.ValidationException;

/**
 * Valide les donnees d'une commande de creation de reservation.
 * Ce composant ne fait aucun acces technique (ni console, ni base) :
 * il se contente de verifier les regles de forme.
 */
public class ReservationValidator {

    /**
     * Valide une commande de creation de reservation.
     *
     * @param command la commande a valider
     * @throws ValidationException si une donnee est absente ou incoherente
     */
    public void valider(CreerReservationCommand command) {
        if (command == null) {
            throw new ValidationException("La commande de reservation est obligatoire.");
        }
        if (command.salleId() == null) {
            throw new ValidationException("L'identifiant de la salle est obligatoire.");
        }
        if (command.nom() == null || command.nom().isBlank()) {
            throw new ValidationException("Le nom est obligatoire.");
        }
        if (command.dateDebut() == null || command.dateFin() == null) {
            throw new ValidationException("Les dates de debut et de fin sont obligatoires.");
        }
        if (!command.dateDebut().isBefore(command.dateFin())) {
            throw new ValidationException("La date de debut doit etre anterieure a la date de fin.");
        }
    }
}