package sn.woy.domain;

import java.util.List;

public final class Salle extends AbstractEntity {
    public Salle() {
        super();
    }

    private String code;
    private String description;
    private TypeSalle typeSalle;
    private int capacite;
    private boolean active;

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }

    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
