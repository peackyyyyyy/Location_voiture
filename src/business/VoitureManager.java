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

    public VoitureManager(){ }

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

    public void updateVoiture(int id, Voiture voiture) throws SQLException {
        voiturePersistence.updateVoiture(id,voiture);
        delete_voiture_by_id(id);
        voitures.add(voiture);
    }

    public Voiture get_voiture_by_id(int id){
        for (Voiture voiture: this.voitures){
            if (voiture.getId() == id){
                return voiture;
            }
        }
        return null;
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

    public void update_voiture_state_by_id(int id, Enumeration.State state) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getId() == id) {
                voiture.setState(state);
            }
        }
    }

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

    private ArrayList<Voiture> try_list_id(int id, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (id==vt.getId()){
                newresult.add(vt);
            }
        }
        return newresult;
    }

    private ArrayList<Voiture> try_list_agence(Agence agence, ArrayList<Voiture> result){
        ArrayList<Voiture> newresult = new ArrayList<>();
        for (Voiture vt: result){
            if (agence.getId() == vt.getAgence().getId()){
                newresult.add(vt);
            }
        }
        return newresult;
    }

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