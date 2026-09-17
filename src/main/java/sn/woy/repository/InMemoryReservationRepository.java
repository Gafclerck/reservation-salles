package sn.woy.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import sn.woy.domain.Reservation;
import sn.woy.domain.Salle;

/**
 * Implementation memoire du contrat {@link ReservationRepository}.
 */
public final class InMemoryReservationRepository implements ReservationRepository {

    private final Map<Integer, Reservation> reservations = new LinkedHashMap<>();
    private Integer prochainId = 1;

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("La reservation a sauvegarder est obligatoire.");
        }
        if (reservation.getId() == null) {
            reservation.setId(prochainId);
        }
        if (reservation.getId() >= prochainId) {
            prochainId = reservation.getId() + 1;
        }
        reservations.put(reservation.getId(), reservation);
        rattacherSalle(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return Optional.ofNullable(reservations.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return List.copyOf(reservations.values());
    }

    @Override
    public List<Reservation> findBySalleId(Integer salleId) {
        List<Reservation> resultat = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            Salle salle = reservation.getSalle();
            if (salle != null && salle.getId() != null && salle.getId().equals(salleId)) {
                resultat.add(reservation);
            }
        }
        return List.copyOf(resultat);
    }

    @Override
    public List<Reservation> findBySalleIdTrieParDateDebut(Integer salleId) {
        return reservations.values().stream()
                .filter(reservation -> appartientALaSalle(reservation, salleId))
                .sorted(Comparator.comparing(Reservation::getDateDebut))
                .toList();
    }

    @Override
    public boolean existeChevauchement(Integer salleId, LocalDateTime debut, LocalDateTime fin) {
        return reservations.values().stream()
                .filter(reservation -> appartientALaSalle(reservation, salleId))
                .anyMatch(reservation -> debut.isBefore(reservation.getDateFin())
                        && fin.isAfter(reservation.getDateDebut()));
    }

    @Override
    public boolean existsById(Integer id) {
        return reservations.containsKey(id);
    }

    @Override
    public long count() {
        return reservations.size();
    }

    private boolean appartientALaSalle(Reservation reservation, Integer salleId) {
        Salle salle = reservation.getSalle();
        return salle != null && salle.getId() != null && salle.getId().equals(salleId);
    }

    private void rattacherSalle(Reservation reservation) {
        Salle salle = reservation.getSalle();
        if (salle == null || salle.getReservations() == null) {
            return;
        }
        if (!salle.getReservations().contains(reservation)) {
            salle.getReservations().add(reservation);
        }
    }
}