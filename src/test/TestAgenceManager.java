package test;

import business.AgenceManager;
import business.VoitureManager;
import value_object.Agence;
import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.ArrayList;

public class TestAgenceManager {
    public static void main(String[] args) {
        /*ICategorie luxe = new Luxe();
        ArrayList<Agence> agenceArrayList = new ArrayList<>();
        ArrayList<Voiture> voitureArrayList = new ArrayList<>();
        Agence agence1 = new Agence("rue1", "ville1", "06", 1, "agence1", "0657453434", "longitude1", "lattitude1");
        Agence agence2 = new Agence("rue2", "ville2", "06", 2, "agence2", "0657453434", "longitude2", "lattitude2");
        VoitureManager voitureManager = new VoitureManager(voitureArrayList);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 1, false, agence1);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 2, false, agence2);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 3, false, agence2);
        AgenceManager agenceManager = new AgenceManager(agenceArrayList, voitureManager.getVoiture());
        ArrayList<Voiture> voitures1 = agenceManager.get_voiture_available_by_agence_id(1);
        for (Voiture voiture: voitures1){
            System.out.println(voiture);
        }
        System.out.println("////////////////////////////////////////////////////////\n");
        ArrayList<Voiture> voitures2 = agenceManager.get_voiture_available_by_agence_id(2);
        for (Voiture voiture: voitures2){
            System.out.println(voiture);
        }*/
    }
}
