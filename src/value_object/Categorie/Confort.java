package value_object.Categorie;

import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.util.List;

public class Confort implements ICategorie {
    private static Enumeration.Categorie name;
    private static int price;
    private static int caution;

    public Confort(){
        name = Enumeration.Categorie.Confort;
        price = 75;
        caution = 750;
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
