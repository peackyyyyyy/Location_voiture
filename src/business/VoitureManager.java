package business;

import Persistence.VoiturePersistence;
import value_object.Agence;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class VoitureManager {

    private ArrayList<Voiture> voitures;
    private VoiturePersistence voiturePersistence;

    /**
     * Le Voiture manager contient toutes les méthodes liées aux voitures (ajouter, modifier, rechercher ....)
     * il y a plusieurs constructeurs du aux différents besoins
     * @param voitures
     * @param voiturePersistence
     */

    public VoitureManager(ArrayList<Voiture> voitures, VoiturePersistence voiturePersistence){
        this.voitures = voitures;
        this.voiturePersistence = voiturePersistence;
    }

    public VoitureManager(ArrayList<Voiture> voitures){
        this.voitures = voitures;
    }

    public VoitureManager(VoiturePersistence voiturePersistence){
        this.voiturePersistence = voiturePersistence;
    }

    public void setVoiturePersistence(VoiturePersistence voiturePersistence) {
        this.voiturePersistence = voiturePersistence;
    }

    /**
     * Ajouter une voiture en RAM et en BDD
     * @param marque
     * @param model
     * @param kilometers
     * @param categorie
     * @param vitesse
     * @param clim
     * @param carburant
     * @param endommage
     * @param state
     * @param agence
     * @param agence_a_etre
     * @throws SQLException
     */

    public void add_voiture(String marque, String model, int kilometers, ICategorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant, boolean endommage, Enumeration.State state, Agence agence, Agence agence_a_etre) throws SQLException {
        Voiture voiture = new Voiture(marque,model,kilometers,endommage,vitesse,clim,agence,agence_a_etre,categorie,carburant,state);
        if (!this.voitures.contains(voiture)) {
            int id = voiturePersistence.insertVoiture(voiture);
            voiture.setId(id);
            this.voitures.add(voiture);
        }
    }
    public int add_voiture(Voiture voiture) throws SQLException {
        if (!this.voitures.contains(voiture)) {
            int id = voiturePersistence.insertVoiture(voiture);
            voiture.setId(id);
            this.voitures.add(voiture);
            return id;
        }
        return -1;
    }

    /**
     * Mis a jour d'une voiture
     * @param id
     * @param voiture
     * @throws SQLException
     */

    public void updateVoiture(int id, Voiture voiture) throws SQLException {
        voiturePersistence.updateVoiture(id,voiture);
        delete_voiture_by_id(id);
        voitures.add(voiture);
    }

    /**
     * Récuperer une voiture en RAM par son ID
     * @param id
     * @return
     */

    public Voiture get_voiture_by_id(int id){
        for (Voiture voiture: this.voitures){
            if (voiture.getId() == id){
                return voiture;
            }
        }
        return null;
    }

    /**
     * Supprimer voiture en RAM par ID
     * @param id
     */

    public void delete_voiture_by_id(int id){
        this.voitures.removeIf(voiture -> voiture.getId() == id);
    }

    /**
     * Supprimer voiture en BDD par ID
     * @param id
     */

    public void delete(int id) throws SQLException {
        voiturePersistence.deleteVoiture(id);
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

    public void update_voiture_state_by_id(int id, Enumeration.State state) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setState(state);
            }
        }
    }

    /**
     * Recherche de voitures en RAM en fonction de paramettre entrées. Les paramettres sont optionnel, la méthodes réalise des filtres successif de parametre, dans le cas ou il y en a un.
     * Ce qui permet d'avoir des recherches flexibles
     * @param id
     * @param modele
     * @param etat
     * @param agence
     * @return
     */

    public ArrayList<Voiture> findVoiture(Optional<Integer> id, Optional<String> modele, Optional<Enumeration.State> etat,Optional<Agence> agence){
        ArrayList<Voiture> result = this.voitures;
        if(id.isPresent()){
            result = try_list_id(id.get(),result);
        }
        if(modele.isPresent()){
            result = try_list_modele(modele.get(), result);
        }
        if(etat.isPresent()){
            result = try_list_etat(etat.get(),result);
        }
        if(agence.isPresent()){
            result = try_list_agence(agence.get(),result);
        }
        return result;
    }

    /**
     * Trie la liste de voitures en fonction de l'ID
     * @param id
     * @param result
     * @return list de voitures avec id correspondant
     */

    private ArrayList<Voiture> try_list_id(int id, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (id==vt.getId()){
                newresult.add(vt);
            }
        }
        return newresult;
    }

    /**
     * Trie la list de voitures en fonction de l'agence
     * @param agence
     * @param result
     * @return list de voitures avec agence correspondante
     */

    private ArrayList<Voiture> try_list_agence(Agence agence, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (agence.getId() == vt.getAgence().getId()){
                newresult.add(vt);
            }
        }
        return newresult;
    }

    /**
     * Trie la list de voitures en fonction du model
     * @param name
     * @param result
     * @return list de voitures avec model correspondant
     */

    private ArrayList<Voiture> try_list_modele(String name, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (name.equals(vt.getModel())){
                newresult.add(vt);
            }
        }
        return newresult;
    }

    private ArrayList<Voiture> try_list_etat(Enumeration.State state, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (state.equals(vt.getState())){
                newresult.add(vt);
            }
        }
        return newresult;
    }

    public ArrayList<Voiture> getVoitures() throws SQLException {
        voitures = voiturePersistence.getVoitures();
        return voitures;
    }


    public void setVoitures(ArrayList<Voiture> voitures) {
        this.voitures = voitures;
    }

    @Override
    public String toString() {
        return "VoitureManager{" +
                "voitures=" + voitures +
                '}';
    }
}