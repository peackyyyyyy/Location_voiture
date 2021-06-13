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

    /**
     * L'agence manager contient toutes les méthodes liées aux agences (ajouter, modifier, rechercher ....)
     * @param agences  liste de toutes les agences
     * @param voitures  listes des voitures diponible dans les agences
     * @param agencePersistence la classe agence persistence est responsable de l'interaction avec la BDD pour la table agence
     */

    public AgenceManager(ArrayList<Agence> agences, ArrayList<Voiture> voitures, AgencePersistence agencePersistence){
        super(voitures);
        this.agences = agences;
        this.agencePersistence = agencePersistence;

    }

    /**
     * Ajouter une agence en base et en mémoire
     * @param rue
     * @param ville
     * @param codepostal
     * @param id
     * @param name
     * @param phone
     * @param longitude
     * @param lattitude
     * @throws SQLException
     */

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

    /**
     * Retourner les voitures disponible dans l'agence
     * @param id id de l'agence en question
     * @return list de voitures
     * @throws SQLException
     */

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

    /**
     * Recuperers toutes les agences dans la BDD pour les mettre en RAM
     * @return  list d'agences
     * @throws SQLException
     */

    public ArrayList<Agence> getAgences() throws SQLException {
        agences = agencePersistence.getAgences();
        return agences;
    }

}