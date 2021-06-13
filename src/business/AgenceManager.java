package business;

import Persistence.AgencePersistence;
import value_object.Agence;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenceManager extends VoitureManager{
    private ArrayList<Agence> agences;
    private AgencePersistence agencePersistence;

    public AgenceManager(ArrayList<Agence> agences, ArrayList<Voiture> voitures, AgencePersistence agencePersistence){
        super(voitures);
        this.agences = agences;
        this.agencePersistence = agencePersistence;

    }

    public void add_agence(String rue, String ville, String codepostal, int id, String name, String phone, String longitude,
                           String lattitude) throws SQLException {
        Agence agence = new Agence(rue, ville, codepostal, id, name, phone, longitude, lattitude);
        if (!this.agences.contains(agence)) {
            int leid = agencePersistence.insertAgence(agence);
            agence.setId(leid);
            this.agences.add(agence);
        }
    }

    public void add_agence(Agence agence) throws SQLException {
        if (!this.agences.contains(agence)) {
            int leid = agencePersistence.insertAgence(agence);
            this.agences.add(agence);
            agence.setId(leid);
        }
    }

    public ArrayList<Voiture> get_voiture_available_by_agence_id(int id) throws SQLException {
        ArrayList<Voiture> voitures = new ArrayList<>();
        for (Voiture voiture: super.getVoitures()){
            if (voiture.getAgence().getId() == id){
                if (voiture.getState() == Enumeration.State.Libre){
                    voitures.add(voiture);
                }
            }
        }
        return voitures;
    }

    public ArrayList<Agence> getAgences() throws SQLException {
        agences = agencePersistence.getAgences();
        return agences;
    }

}