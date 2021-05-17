package value_object;
import java.util.Date;

public class Devis {
    private final Date duree;
    private float price;
    private final Voiture voiture;
    private final Client client;

    public Devis(Voiture voiture, Client client, Date duree){
        this.duree = duree;
        this.voiture = voiture;
        this.client = client;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public Date getDuree() {
        return duree;
    }

    public Voiture getVoiture() {
        return voiture;
    }
}
