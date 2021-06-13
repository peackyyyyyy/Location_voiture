package business;

import value_object.Agence;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.ArrayList;
import java.util.List;

public class AgenceManager extends VoitureManager{
    private final ArrayList<Agence> agences;

    public AgenceManager(ArrayList<Agence> agences, ArrayList<Voiture> voitures){
        super(voitures);
        this.agences = agences;
    }

    public void add_agence(String rue, String ville, String codepostal, int id, String name, String phone, String longitude,
                           String lattitude){
        //#todo add voiture to BDD and get id
        Agence agence = new Agence(rue, ville, codepostal, id, name, phone, longitude, lattitude);
        if (!this.agences.contains(agence)) {
            this.agences.add(agence);
        }
    }
    public ArrayList<Voiture> get_voiture_available_by_agence_id(int id){
        ArrayList<Voiture> voitures = new ArrayList<>();
        for (Voiture voiture: this.getVoiture()){
            if (voiture.getAgence().getId() == id){
                if (voiture.getState() == Enumeration.State.Libre){
                    voitures.add(voiture);
                }
            }
        }
        return voitures;
    }

    public ArrayList<Agence> getAgences() {
        return agences;
    }

}
