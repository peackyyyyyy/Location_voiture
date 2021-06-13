package business;

import Persistence.DevisPersistence;
import value_object.Client;
import value_object.Devis;
import value_object.Facture;
import value_object.Voiture;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DevisManager {
    private final ArrayList<Devis> devis;
    private DevisPersistence devisPersistence;

    public DevisManager(ArrayList<Devis> devis, DevisPersistence devisPersistence){
        this.devis = devis;
        this.devisPersistence = devisPersistence;
    }
    public Devis add_devi(Voiture voiture, Client client, Date debut) throws SQLException {
        Devis devi = new Devis(voiture, client, debut);
        if (!this.devis.contains(devi)) {
            int leid = devisPersistence.insertDevis(devi);
            devi.setId(leid);
            this.devis.add(devi);
            return devi;
        }
        return null;
    }

    public void add_devi(Devis devis) throws SQLException {
        if (!this.devis.contains(devis)) {
            int leid = devisPersistence.insertDevis(devis);
            devis.setId(leid);
            this.devis.add(devis);
        }
    }

    public void delete_devis_by_id(int id){
        this.devis.removeIf(devis -> devis.getId() == id);
    }

    public Devis get_devis_by_id(int id){
        for (Devis devis: this.devis){
            if (devis.getId() == id){
                return devis;
            }
        }
        return null;
    }

    public void generate_facture_by_id(int id){
        int temp;
        for (Devis devi : this.devis) {
            if (devi.getId() == id) {
                try {
                    temp = devi.getClient().getFidelite().getFin().compareTo(new Date()); //check if the end of the subscription
                }
                catch (Exception ignored){
                    temp = -1;
                }
                if(temp>0){
                    devi.setFacture(new Facture(devi.getVoiture().getCategorie().getTarif(), devi.getDebut(), devi.getFin(), devi.getClient().getFidelite().getReduction()));
                }
                else {
                    devi.setFacture(new Facture(devi.getVoiture().getCategorie().getTarif(), devi.getDebut(), devi.getFin()));
                }
            }
        }
    }

    public void update_fin_devis_by_id(int id, Date fin) throws SQLException {
        for (Devis devi : this.devis) {
            if (devi.getId() == id) {
                devi.setFin(fin);
                devisPersistence.updateDevis(id,devi);
            }
        }
    }

    public void update_debut_devis_by_id(int id, Date debut) {
        for (Devis devi : this.devis) {
            if (devi.getId() == id) {
                devi.setFin(debut);
            }
        }
    }


    public ArrayList<Devis> getDevis() {
        return devis;
    }

    @Override
    public String toString() {
        return "DevisManager{" +
                "devis=" + devis +
                '}';
    }
}