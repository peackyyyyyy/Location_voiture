package business;

import Persistence.DevisPersistence;
import value_object.Client;
import value_object.Devis;
import value_object.Facture;
import value_object.Voiture;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class DevisManager {
    private ArrayList<Devis> devis;
    private DevisPersistence devisPersistence;

    /**
     * Le Devis manager contient toutes les méthodes liées aux devis (ajouter, modifier, rechercher ....)
     * A noter que nous considérons un devi comme une "location"
     * @param devis
     * @param devisPersistence
     */

    public DevisManager(ArrayList<Devis> devis, DevisPersistence devisPersistence){
        this.devis = devis;
        this.devisPersistence = devisPersistence;
    }

    /**
     * Ajouter un client en RAM et en BDD
     * @param voiture
     * @param client
     * @param debut
     * @return id du devi
     * @throws SQLException
     */
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

    /**
     * Recuperer un devi par l'id en RAM
     * @param id
     * @return
     */

    public Devis get_devis_by_id(int id){
        for (Devis devis: this.devis){
            if (devis.getId() == id){
                return devis;
            }
        }
        return null;
    }

    /**
     * Mis a jour d'un devi en BDD
     * @param id
     * @param devis
     * @return
     * @throws SQLException
     */
    public int updateClient(int id, Devis devis) throws SQLException {
        return devisPersistence.updateDevis(id,devis);
    }

    /**
     * Gener une facture pour par l'id d'un devi, on recuperer la date de debut et la date de fin multiplié par le prix de la voiture moins la reduction
     * @param id
     */
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

    /**
     * Mis a jour de la date de fin du devi en RAM et en BDD
     * @param id
     * @param fin
     * @throws SQLException
     */


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


    /**
     * Recuperer tous les devis en BDD et le ajouter en RAM
     * @return
     * @throws SQLException
     * @throws ParseException
     */

    public ArrayList<Devis> getDevis() throws SQLException, ParseException {
        devis = devisPersistence.getDevis();
        return devis;
    }

    /**
     * Supprimer en RAM
     * @param id
     * @return
     * @throws SQLException
     */

    public boolean deleteDevis(int id) throws SQLException {
        return devisPersistence.deleteDevis(id);
    }

    @Override
    public String toString() {
        return "DevisManager{" +
                "devis=" + devis +
                '}';
    }
}