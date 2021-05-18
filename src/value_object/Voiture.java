package value_object;

import value_object.model.Enumeration;

public class Voiture {
    private final int id;
    private final String marque;
    private final String model;
    private int kilometers;
    private boolean endommage;
    private final boolean vitesse;
    private final boolean clim;
    private final ICategorie categorie;
    private final Enumeration.Carburant carburant;
    private boolean reservation;
    private boolean location;

    public Voiture(int id, String marque, String model, int kilometers, ICategorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant, boolean endommage){
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.categorie = categorie;
        this.vitesse = vitesse;
        this.clim = clim;
        this.carburant = carburant;
        this.endommage = endommage;
    }

    public Voiture(int id, String marque, String model, int kilometers, boolean vitesse, boolean clim, boolean endommage){
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.vitesse = vitesse;
        this.clim = clim;
        this.endommage = endommage;
        this.categorie = null;
        this. carburant = null;
    }

    public int getId() {
        return id;
    }

    public boolean isEndommage() {
        return endommage;
    }

    public void setEndommage(boolean endommage) {
        this.endommage = endommage;
    }

    public boolean isClim() {
        return clim;
    }

    public boolean isVitesse() {
        return vitesse;
    }

    public Enumeration.Carburant getCarburant() {
        return carburant;
    }

    public ICategorie getCategorie() {
        return categorie;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public boolean isLocation() {
        return location;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", kilometers=" + kilometers +
                ", endommage=" + endommage +
                ", vitesse=" + vitesse +
                ", clim=" + clim +
                ", categorie=" + categorie +
                ", carburant=" + carburant.toString() +
                ", reservation=" + reservation +
                ", location=" + location +
                '}';
    }

}
