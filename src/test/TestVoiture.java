package test;

import value_object.ICategorie;
import value_object.Categorie.Luxe;
import value_object.Voiture;
import value_object.model.Enumeration;

public class TestVoiture {

    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        Voiture voiture = new Voiture(2,"marque", "model", 15, false, true, Enumeration.Carburant.SP95, false);
        voiture.setCategorie(luxe);

        int tarif = voiture.getCategorie().getTarif();
        if (tarif==100){
            System.out.println("Trueeee");
        }


    }
}
