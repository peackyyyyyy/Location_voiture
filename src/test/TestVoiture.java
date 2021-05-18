package test;

import value_object.*;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

public class TestVoiture {

    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        Voiture voiture1 = new Voiture(1, "BMW", "A1", 15, false, true, Enumeration.Carburant.SP95, false);
        voiture1.setCategorie(luxe);

        int tarif = voiture1.getCategorie().getTarif();
        if (tarif==100){
            System.out.println("Trueeee");
        }
        System.out.println(voiture1);
        Client client= new Client(new Personne("Theo", "MASTOCK", "mastock@gmail.com", new Adresse("rue de mastock", "Villiers sur marne", "94350"), "0606060606"),1);
        

    }
}
