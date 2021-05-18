package test;

import business.VoitureManager;
import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.ArrayList;

public class TestVoitureManager {
    public static void main(String[] args) {
        ICategorie luxe = new Luxe();
        ArrayList<Voiture> voitureArrayList = new ArrayList<>();
        VoitureManager voitureManager = new VoitureManager(voitureArrayList);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 1, false);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 2, false);
        voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 3, false);
        voitureManager.delete_voiture_by_id(2);
        for (Voiture voiture : voitureArrayList) {
            System.out.println(voiture.getId());
        }

    }

}
