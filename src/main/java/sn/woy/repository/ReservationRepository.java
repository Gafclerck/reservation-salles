package sn.woy.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import sn.woy.domain.Reservation;

/**
 * Contrat d'acces aux reservations.
 */
public interface ReservationRepository {

    /**
     * Sauvegarde une reservation. Si son identifiant est absent, un nouvel identifiant est attribue.
     *
     * @param reservation la reservation a sauvegarder
     * @return la reservation sauvegardee
     */
    Reservation save(Reservation reservation);

    /**
     * Recherche une reservation par son identifiant.
     *
     * @param id l'identifiant de la reservation
     * @return la reservation trouvee, ou {@code Optional.empty} si elle est absente
     */
    Optional<Reservation> findById(Integer id);

    /**
     * Liste toutes les reservations.
     *
     * @return une copie immuable des reservations connues
     */
    List<Reservation> findAll();

    /**
     * Liste les reservations d'une salle donnee.
     *
     * @param salleId l'identifiant de la salle
     * @return une copie immuable des reservations de la salle
     */
    List<Reservation> findBySalleId(Integer salleId);

    /**
     * Liste les reservations d'une salle donnee, triees par date de debut croissante.
     *
     * @param salleId l'identifiant de la salle
     * @return les reservations de la salle triees par date de debut
     */
    List<Reservation> findBySalleIdTrieParDateDebut(Integer salleId);

    /**
     * Indique si un creneau chevauche au moins une reservation existante de la salle.
     * Deux creneaux se chevauchent si {@code debut < fin existante} et {@code fin > debut existante}.
     *
     * @param salleId l'identifiant de la salle
     * @param debut le debut du creneau recherche
     * @param fin la fin du creneau recherche
     * @return {@code true} si un chevauchement existe, {@code false} sinon
     */
    boolean existeChevauchement(Integer salleId, LocalDateTime debut, LocalDateTime fin);

    /**
     * Indique si une reservation existe pour un identifiant donne.
     *
     * @param id l'identifiant recherche
     * @return {@code true} si la reservation existe, {@code false} sinon
     */
    boolean existsById(Integer id);

    /**
     * Compte le nombre de reservations connues.
     *
     * @return le nombre de reservations
     */
    long count();
}