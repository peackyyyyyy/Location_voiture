package value_object;


import value_object.model.Enumeration;

import java.util.List;

public interface ICategorie{
    Enumeration.Categorie getName();
    int getCaution();
    int getTarif();
    String toString();

}
