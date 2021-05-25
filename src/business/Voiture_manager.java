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


    public List<Voiture> getVoiture() {
        return voiture;
    }
}
