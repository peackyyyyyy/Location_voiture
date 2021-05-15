package value_object;

import value_object.model.Enumeration;

public class Voiture {
    protected String marque;
    protected String model;
    protected int kilometers;
    protected boolean vitesse;
    protected boolean clim;
    protected Categorie categorie;
    protected Enumeration.Carburant carburant;
    protected boolean reservation;
    protected boolean location;

    Voiture(String marque, String model, int kilometers, Categorie categorie, boolean vitesse, boolean clim, Enumeration.Carburant carburant){
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.categorie = categorie;
        this.vitesse = vitesse;
        this.clim = clim;
        this.carburant = carburant;
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

    public Categorie getCategorie() {
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

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }
}
