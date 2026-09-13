package sn.woy.domain;

import java.time.LocalDateTime;

public final class Reservation extends AbstractEntity {
    public Reservation() {
        super();
    }

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private Salle salle;

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
}
