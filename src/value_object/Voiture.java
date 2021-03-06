package value_object;

import value_object.model.Enumeration;

public class Voiture {
    private int id;
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
    private Agence agence_a_etre;

    public void setId(int id) {
        this.id = id;
    }

    public void setAgence_a_etre(Agence agence_a_etre) {
        this.agence_a_etre = agence_a_etre;
    }

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

    public Voiture(int id, String marque, String model, int kilometers, boolean vitesse, boolean clim, Enumeration.Carburant carburant, boolean endommage, Agence agence, Agence agence_a_etre){
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.vitesse = vitesse;
        this.clim = clim;
        this.carburant = carburant;
        this.endommage = endommage;
        this.agence = agence;
        this.agence_a_etre = agence_a_etre;
    }

    public Voiture(int id, String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, Agence agence, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.agence = agence;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
    }

    public Voiture(int id, String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, Agence agence, Agence agence_a_etre, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.agence = agence;
        this.agence_a_etre = agence_a_etre;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
    }

    public Voiture(int id, String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, Agence agence, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state,  Agence agence_a_etre) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.agence = agence;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
        this.agence_a_etre = agence_a_etre;
    }


    public Voiture(String marque, String model, int kilometers, boolean endommage, boolean vitesse, boolean clim, Agence agence,Agence agence_a_etre, ICategorie categorie, Enumeration.Carburant carburant, Enumeration.State state) {
        this.marque = marque;
        this.model = model;
        this.kilometers = kilometers;
        this.endommage = endommage;
        this.vitesse = vitesse;
        this.clim = clim;
        this.agence = agence;
        this.agence_a_etre = agence_a_etre;
        this.categorie = categorie;
        this.carburant = carburant;
        this.state = state;
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

    public Agence getAgence_a_etre() {
        return agence_a_etre;
    }

    @Override
    public String toString() {
        return model;
    }

}