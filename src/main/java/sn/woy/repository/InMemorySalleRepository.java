package sn.woy.repository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import sn.woy.domain.Salle;

/**
 * Implementation memoire du contrat {@link SalleRepository}.
 */
public final class InMemorySalleRepository implements SalleRepository {

    private final Map<Integer, Salle> salles = new LinkedHashMap<>();
    private Integer prochainId = 1;

    @Override
    public Salle save(Salle salle) {
        if (salle == null) {
            throw new IllegalArgumentException("La salle a sauvegarder est obligatoire.");
        }
        if (salle.getId() == null) {
            salle.setId(prochainId);
        }
        if (salle.getId() >= prochainId) {
            prochainId = salle.getId() + 1;
        }
        salles.put(salle.getId(), salle);
        return salle;
    }

    @Override
    public Optional<Salle> findById(Integer id) {
        return Optional.ofNullable(salles.get(id));
    }

    @Override
    public List<Salle> findAll() {
        return List.copyOf(salles.values());
    }

    @Override
    public List<Salle> findActivesAvecCapaciteMinimale(int capaciteMinimale) {
        return salles.values().stream()
                .filter(Salle::isActive)
                .filter(salle -> salle.getCapacite() >= capaciteMinimale)
                .sorted(Comparator.comparing(Salle::getCode))
                .toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return salles.containsKey(id);
    }

    @Override
    public long count() {
        return salles.size();
    }
}