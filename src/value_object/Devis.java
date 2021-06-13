package value_object;
import java.util.Date;

public class Devis{
    private int id;
    private Date debut;
    private Date fin;
    private Facture facture;
    private final Voiture voiture;
    private final Client client;

    public Devis(Voiture voiture, Client client, Date debut, int id){
        this.id = id;
        this.debut = debut;
        this.voiture = voiture;
        this.client = client;
    }
    public Devis(Voiture voiture, Client client, Date debut, Date fin, int id){
        this.id = id;
        this.debut = debut;
        this.voiture = voiture;
        this.client = client;
    }
    public Devis(Voiture voiture, Client client, Date debut, Date fin){
        this.id = -1;
        this.fin = fin;
        this.debut = debut;
        this.voiture = voiture;
        this.client = client;
    }


    public int getId() {
        return id;
    }

    public Date getFin() {
        return fin;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Facture getFacture() {
        return facture;
    }

    public Client getClient() {
        return client;
    }

    public Date getDebut() {
        return debut;
    }

    public Voiture getVoiture() {
        return voiture;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", debut=" + debut +
                ", fin=" + fin +
                ", facture=" + facture +
                ", voiture=" + voiture +
                ", client=" + client +
                '}';
    }
}
