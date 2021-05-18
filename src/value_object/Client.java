package value_object;

public class Client extends Personne{
    private final int id;
    private Voiture location;
    private Fidelite fidelite;

    public Client(Personne personne, int id){
        super(personne.getName(), personne.getSurname(), personne.getEmail(), personne.getAdresse(), personne.getPhone());
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

    public int getId() {
        return id;
    }

    public Fidelite getFidelite() {
        return fidelite;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                super.toString() +
                ", location=" + location +
                ", fidelite=" + fidelite +
                '}';
    }
}
