package value_object;

public class Location {

    Voiture voiture;
    Client client;

    Location(Voiture voiture, Client client){
        this.voiture = voiture;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Voiture getVoiture() {
        return voiture;
    }
}
