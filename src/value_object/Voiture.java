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
    private final Agence agence;
    private ICategorie categorie;
    private final Enumeration.Carburant carburant;
    private Enumeration.State state;

    public Voiture(int id, String marque, String model, int kilometers, boolean vitesse, boolean clim, Enumeration.Carburant carburant, boolean endommage, Agence agence){
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.vitesse = vitesse;
        this.clim = clim;
        this.carburant = carburant;
        this.endommage = endommage;
        this.agence = agence;
    }

    public Voiture(int id, String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
    }

    public Voiture(String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state) {
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
        this.id = -1;
    }

    public void setCategorie(ICategorie categorie) {
        this.categorie = categorie;
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

    public Enumeration.State getState() {
        return state;
    }

    public void setState(Enumeration.State state) {
        this.state = state;
    }

    public Agence getAgence() {
        return agence;
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
                ", agence=" + agence +
                ", categorie=" + categorie +
                ", carburant=" + carburant +
                ", state=" + state +
                '}';
    }


}
