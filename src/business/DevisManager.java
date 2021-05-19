package business;

import value_object.Client;
import value_object.Devis;
import value_object.Facture;
import value_object.Voiture;

import java.util.ArrayList;
import java.util.Date;

public class DevisManager {
    private final ArrayList<Devis> devis;

    public DevisManager(ArrayList<Devis> devis){
        this.devis = devis;
    }
    public void add_devi(Voiture voiture, Client client, Date debut, int id){
        //#todo add devis to BDD and get id
        Devis devi = new Devis(voiture, client, debut, id);
        if (!this.devis.contains(devi)) {
            this.devis.add(devi);
        }
    }

    public void delete_devis_by_id(int id){
        this.devis.removeIf(devis -> devis.getId() == id);
    }

    public Facture generate_facture_by_id(int id){
        for (Devis devi : this.devis) {
            if (devi.getId() == id) {
                return new Facture(devi.getVoiture().getCategorie().getTarif(), devi.getClient().getFidelite().getReduction(), devi.getDebut(), devi.getFin());
            }
        }
        return null;
    }

    public void update_fin_devis_by_id(int id, Date fin) {
        for (Devis devi : this.devis) {
            if (devi.getId() == id) {
                devi.setFin(fin);
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
