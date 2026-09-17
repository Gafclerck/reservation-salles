package sn.woy.application;

import java.time.LocalDateTime;

/**
 * Commande de creation d'une reservation. C'est un objet de transport (DTO) :
 * il ne porte ni comportement metier, ni acces technique (console, base).
 *
 * @param salleId l'identifiant de la salle concernee
 * @param nom le nom de la personne ou de l'evenement qui reserve
 * @param dateDebut le debut du creneau
 * @param dateFin la fin du creneau
 */
public record CreerReservationCommand(
        Integer salleId,
        String nom,
        LocalDateTime dateDebut,
        LocalDateTime dateFin) {
}