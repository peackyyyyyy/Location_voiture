package value_object;

public class Client extends Personne{
    protected int id;
    protected Voiture location;
    protected Fidelite fidelite;

    Client(int id, String name, String surname, String email, Adresse adresse, String phone){
        super(name, surname, email, adresse, phone);
        this.id = id;
    }

    public void setLocation(Voiture location) {
        this.location = location;
    }

    public void setFidelite(Fidelite fidelite) {
        this.fidelite = fidelite;
    }

    public Voiture getLocation() {
        return location;
    }

    public Fidelite getFidelite() {
        return fidelite;
    }
}
