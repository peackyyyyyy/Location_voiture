package value_object.Categorie;

import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.List;

public class Economique implements ICategorie {
    private static Enumeration.Categorie name;
    private static int price;
    private static int caution;

    public Economique(){
        name = Enumeration.Categorie.Economique;
        price = 50;
        caution = 500;
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
