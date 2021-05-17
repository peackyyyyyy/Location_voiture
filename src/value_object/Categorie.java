package value_object;

import value_object.model.Enumeration;

import java.util.List;

public class Categorie {
    private final Enumeration.Categorie name;
    private final int price;
    private final int caution;
    private List<Voiture> voiture;

    Categorie(Enumeration.Categorie name, int price, int caution){
        this.name = name;
        this.price = price;
        this.caution = caution;
    }

    public List<Voiture> getVoiture() {
        return voiture;
    }

    public void setVoiture(List<Voiture> voiture) {
        this.voiture = voiture;
    }

    public Enumeration.Categorie getName() {
        return name;
    }


    public int getCaution() {
        return caution;
    }

    public int getTarif() {
        return price;
    }


}
