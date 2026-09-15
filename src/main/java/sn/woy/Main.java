package sn.woy;

import sn.woy.domain.Salle;

public class Main {
    public static void main(String[] args) {
        DemoClass demo = new DemoClass();
        demo.afficherSalles();
        for (Salle salle : demo.getDemoSalles()) {
            System.out.println(salle.getCode() + " -> " + salle.getTypeSalle());
        }
    }
}