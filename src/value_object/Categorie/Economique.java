package value_object.Categorie;

import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.List;

public class Economique implements ICategorie {
    private static Enumeration.Categorie name;
    private static int price;
    private static int caution;
    private List<Voiture> voiture;

    public Economique(){
        name = Enumeration.Categorie.Economique;
        price = 50;
        caution = 500;
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
