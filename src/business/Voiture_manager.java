package business;

import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.List;

public class Voiture_manager {
    private final List<Voiture> voiture;

    public Voiture_manager(List<Voiture> voiture){
        this.voiture = voiture;
    }

     public void add_voiture(String marque, String model, int kilometers, ICategorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant, int id){
        //#todo add voiture to BDD and get id
        Voiture voiture = new Voiture(id, marque, model, kilometers, categorie, vitesse, clim, carburant);
        if (!this.voiture.contains(voiture)) {
            this.voiture.add(voiture);
        }
     }
    public void delete_voiture(String marque, String model, int kilometers, ICategorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant){
        Voiture voiture = new Voiture(marque, model, kilometers, categorie, vitesse, clim, carburant);
        if (!this.voiture.contains(voiture)) {
            this.voiture.add(voiture);
        }
    }

    public List<Voiture> getVoiture() {
        return voiture;
    }
}
