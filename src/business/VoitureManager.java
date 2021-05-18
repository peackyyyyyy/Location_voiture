package business;

import value_object.Adresse;
import value_object.Client;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.ArrayList;
import java.util.List;

public class VoitureManager {
    private final ArrayList<Voiture> voitures;

    public VoitureManager(ArrayList<Voiture> voitures){
        this.voitures = voitures;
    }

     public void add_voiture(String marque, String model, int kilometers, ICategorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant, int id, boolean endommage){
        //#todo add voiture to BDD and get id
        Voiture voiture = new Voiture(id, marque, model, kilometers, vitesse, clim, carburant, endommage);
        voiture.setCategorie(categorie);
        if (!this.voitures.contains(voiture)) {
            this.voitures.add(voiture);
        }
     }
    public void delete_voiture_by_id(int id){
        this.voitures.removeIf(voiture -> voiture.getId() == id);
    }

    public void update_voiture_categorie_by_id(int id, ICategorie categorie) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setCategorie(categorie);
            }
        }
    }

    public void update_voiture_endommage_by_id(int id, boolean endommage) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setEndommage(endommage);
            }
        }
    }

    public void update_voiture_kilometers_by_id(int id, int kilometers) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setKilometers(kilometers);
            }
        }
    }

    public void update_voiture_location_by_id(int id, boolean location) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setLocation(location);
            }
        }
    }

    public void update_voiture_reservation_by_id(int id, boolean reservation) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setReservation(reservation);
            }
        }
    }


    public List<Voiture> getVoiture() {
        return voitures;
    }

}
