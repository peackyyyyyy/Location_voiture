package test;

import value_object.Adresse;
import value_object.Client;
import value_object.ICategorie;
import value_object.Categorie.Luxe;
import value_object.Voiture;
import value_object.model.Enumeration;

public class TestVoiture {

    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        Voiture voiture1 = new Voiture(1,"BMW", "A1", 15, luxe, false, true, Enumeration.Carburant.SP95,true);

        int tarif = voiture1.getCategorie().getTarif();
        if (tarif==100){
            System.out.println("Trueeee");
        }
        System.out.println(voiture1.toString());
        Client client= new Client("Theo", "MASTOCK", "mastock@gmail.com", new Adresse("rue de mastock", "Villiers sur marne", 94350), "0606060606");
        

    }
}
