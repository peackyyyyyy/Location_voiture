package value_object;

import java.util.List;

public class Categorie {
    protected String name;
    protected int tarif;
    protected int caution;
    protected List<Voiture> voiture;

    Categorie(String name, int tarif, int caution, List<Voiture> voiture){
        this.name = name;
        this.tarif = tarif;
        this.caution = caution;
        this.voiture = voiture;
    }

    public List<Voiture> getVoiture() {
        return voiture;
    }

    public int getCaution() {
        return caution;
    }
    public int getTarif() {
        return caution;
    }

}
