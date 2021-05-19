package test;

import business.DevisManager;
import value_object.*;
import value_object.Categorie.Economique;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestDevisManager {
    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        ICategorie eco = new Economique();
        Voiture voiture = new Voiture(1,"marque", "model", 15, false, true, Enumeration.Carburant.SP95, false);
        voiture.setCategorie(luxe);
        Adresse adresse = new Adresse("rue", "ville", "06600");
        Personne personne = new Personne("name", "surname", "mail", adresse, "0658526192");
        Client client = new Client(personne, 1);
        Voiture voiture2 = new Voiture(2,"marque2", "model2", 15, false, true, Enumeration.Carburant.SP95, false);
        voiture2.setCategorie(eco);
        Adresse adresse2 = new Adresse("rue2", "ville2", "06600");
        Personne personne2 = new Personne("name2", "surname2", "mail2", adresse2, "0658526192");
        Client client2 = new Client(personne2, 2);
        Date today = new Date();
        Date yesterday = new Date();
        ArrayList<Devis> devisArrayList = new ArrayList<>();
        DevisManager devisManager = new DevisManager(devisArrayList);
        devisManager.add_devi(voiture, client, yesterday, 1);
        devisManager.add_devi(voiture2, client2, yesterday, 2);
        for (Devis devis : devisArrayList) {
            System.out.println(devis);
        }



    }
}
