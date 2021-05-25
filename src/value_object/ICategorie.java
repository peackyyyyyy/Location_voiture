package value_object;


import value_object.model.Enumeration;

import java.util.List;

public interface ICategorie{
    List<Voiture> getVoiture();
    void setVoiture(List<Voiture> voiture);
    Enumeration.Categorie getName();
    int getCaution();
    int getTarif();
    String toString();

}
