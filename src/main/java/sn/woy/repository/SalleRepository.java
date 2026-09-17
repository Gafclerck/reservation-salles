package sn.woy.repository;

import java.util.List;
import java.util.Optional;
import sn.woy.domain.Salle;

/**
 * Contrat d'acces aux salles.
 */
public interface SalleRepository {

    /**
     * Sauvegarde une salle. Si son identifiant est absent, un nouvel identifiant est attribue.
     *
     * @param salle la salle a sauvegarder
     * @return la salle sauvegardee
     */
    Salle save(Salle salle);

    /**
     * Recherche une salle par son identifiant.
     *
     * @param id l'identifiant de la salle
     * @return la salle trouvee, ou {@code Optional.empty} si elle est absente
     */
    Optional<Salle> findById(Integer id);

    /**
     * Liste toutes les salles.
     *
     * @return une copie immuable des salles connues
     */
    List<Salle> findAll();

    /**
     * Indique si une salle existe pour un identifiant donne.
     *
     * @param id l'identifiant recherche
     * @return {@code true} si la salle existe, {@code false} sinon
     */
    boolean existsById(Integer id);

    /**
     * Compte le nombre de salles connues.
     *
     * @return le nombre de salles
     */
    long count();
}