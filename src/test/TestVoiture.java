package test;

import value_object.Agence;
import value_object.ICategorie;
import value_object.Categorie.Luxe;
import value_object.Voiture;
import value_object.model.Enumeration;

public class TestVoiture {

    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        Agence agence = new Agence("rue1", "ville1", "06", 1, "agence1", "0657453434", "longitude1", "lattitude1");
        Voiture voiture = new Voiture(2,"marque", "model", 15, false, true, Enumeration.Carburant.SP95, false, agence);
        voiture.setCategorie(luxe);

        int tarif = voiture.getCategorie().getTarif();
        if (tarif==100){
            System.out.println("Trueeee");
        }


    }
}
