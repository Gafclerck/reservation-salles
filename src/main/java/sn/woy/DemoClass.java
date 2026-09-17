package sn.woy;

import java.util.ArrayList;
import java.util.List;
import sn.woy.domain.Salle;
import sn.woy.domain.TypeSalle;

public class DemoClass {
    private List<Salle> demoSalles;

    public DemoClass() {
        this.demoSalles = new ArrayList<>();
        initialiserSalles();
    }

    private void initialiserSalles() {
        Salle s1 = new Salle();
        s1.setId(1);
        s1.setCode("A101");
        s1.setDescription("Salle de cours amphitheatre");
        s1.setTypeSalle(TypeSalle.COURS);
        s1.setCapacite(120);
        s1.setActive(true);
        s1.setReservations(new ArrayList<>());
        demoSalles.add(s1);

        Salle s2 = new Salle();
        s2.setId(2);
        s2.setCode("B202");
        s2.setDescription("Salle de soutenance kube");
        s2.setTypeSalle(TypeSalle.SOUTENANCE);
        s2.setCapacite(40);
        s2.setActive(true);
        s2.setReservations(new ArrayList<>());
        demoSalles.add(s2);

        Salle s3 = new Salle();
        s3.setId(3);
        s3.setCode("C303");
        s3.setDescription("Salle de reunion direction");
        s3.setTypeSalle(TypeSalle.REUNION);
        s3.setCapacite(15);
        s3.setActive(true);
        s3.setReservations(new ArrayList<>());
        demoSalles.add(s3);

        Salle s4 = new Salle();
        s4.setId(4);
        s4.setCode("D404");
        s4.setDescription("Salle de travaux pratiques info");
        s4.setTypeSalle(TypeSalle.TRAVAUX_PRACTIQUES);
        s4.setCapacite(25);
        s4.setActive(true);
        s4.setReservations(new ArrayList<>());
        demoSalles.add(s4);

        Salle s5 = new Salle();
        s5.setId(5);
        s5.setCode("E505");
        s5.setDescription("Salle evenement etudiant");
        s5.setTypeSalle(TypeSalle.EVENEMENT_ETUDIANT);
        s5.setCapacite(200);
        s5.setActive(true);
        s5.setReservations(new ArrayList<>());
        demoSalles.add(s5);

        Salle s6 = new Salle();
        s6.setId(6);
        s6.setCode("F606");
        s6.setDescription("Salle de cours TD electronique");
        s6.setTypeSalle(TypeSalle.COURS);
        s6.setCapacite(60);
        s6.setActive(false);
        s6.setReservations(new ArrayList<>());
        demoSalles.add(s6);
    }

    public List<Salle> getDemoSalles() {
        return demoSalles;
    }

    public void afficherSalles() {
        System.out.println("=== Liste des salles en memoire ===");
        for (Salle salle : demoSalles) {
            System.out.println("[" + salle.getId() + "] "
                + salle.getCode() + " - "
                + salle.getDescription() + " ("
                + salle.getTypeSalle() + ")");
        }
        System.out.println("Total : " + demoSalles.size() + " salles");
    }
}
