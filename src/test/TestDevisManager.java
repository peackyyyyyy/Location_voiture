package test;

import business.DevisManager;
import value_object.*;
import value_object.Categorie.Economique;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestDevisManager {
    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        ICategorie eco = new Economique();
        Agence agence = new Agence("rue1", "ville1", "06", 1, "agence1", "0657453434", "longitude1", "lattitude1");
        Voiture voiture = new Voiture(1,"marque", "model", 15, false, true, Enumeration.Carburant.SP95, false, agence);
        voiture.setCategorie(luxe);
        Adresse adresse = new Adresse("rue", "ville", "06600");
        Personne personne = new Personne("name", "surname", "mail", adresse, "0658526192");
        Client client = new Client(personne, 1);
        Voiture voiture2 = new Voiture(2,"marque2", "model2", 15, false, true, Enumeration.Carburant.SP95, false, agence);
        voiture2.setCategorie(eco);
        Adresse adresse2 = new Adresse("rue2", "ville2", "06600");
        Personne personne2 = new Personne("name2", "surname2", "mail2", adresse2, "0658526192");
        Client client2 = new Client(personne2, 2);
        Client client3 = new Client(personne, 3);
        /*client3.setFidelite(new Fidelite(new GregorianCalendar(2018,GregorianCalendar.MAY, 23).getTime(),
                new GregorianCalendar(2020,GregorianCalendar.MAY, 23).getTime(),
                "reduc ne doit pas s'appliquer car expiré, donc meme prix que pour le 1", 500, (float) 0.9, 1));
        Client client4 = new Client(personne2, 4);
        client4.setFidelite(new Fidelite(new GregorianCalendar(2019,GregorianCalendar.MAY, 23).getTime(),
                new GregorianCalendar(2021,GregorianCalendar.MAY, 23).getTime(),
                "reduc de 10% doit se faire car abonnement valable", 500, (float) 0.9, 1));
        /////////////////////////////////////////////////////////
        Date today = new Date();
        Date date1 = new GregorianCalendar(2021,GregorianCalendar.MAY, 23).getTime();
        Date date2 = new GregorianCalendar(2021,GregorianCalendar.MAY, 28).getTime();
        //////////////////////////////////////////////////////////////////
        ArrayList<Devis> devisArrayList = new ArrayList<>();
        DevisManager devisManager = new DevisManager(devisArrayList);
        devisManager.add_devi(voiture, client, today, 1);
        devisManager.add_devi(voiture2, client2, today, 2);
        devisManager.add_devi(voiture, client3, today, 3);
        devisManager.add_devi(voiture2, client4, today, 4);
        devisManager.update_fin_devis_by_id(1, date1);
        devisManager.update_fin_devis_by_id(2, date2);
        devisManager.update_fin_devis_by_id(3, date1);
        devisManager.update_fin_devis_by_id(4, date2);
        devisManager.generate_facture_by_id(1);
        devisManager.generate_facture_by_id(2);
        devisManager.generate_facture_by_id(3);
        devisManager.generate_facture_by_id(4);
        for (Devis devis : devisArrayList) {
            System.out.println(devis);
            // price must me equal to 300 for the first and 400 for the second
        }


*/

    }
}
