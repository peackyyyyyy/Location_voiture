package value_object;

public class Client extends Personne{
    private int id;
    private Voiture location;
    private Fidelite fidelite;

    Client(String name, String surname, String email, Adresse adresse, String phone){
        super(name, surname, email, adresse, phone);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fidelite getFidelite() {
        return fidelite;
    }
}
