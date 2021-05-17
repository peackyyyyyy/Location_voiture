package value_object;
import value_object.model.Enumeration;
import java.util.Enumeration;

public interface ICategorie{
    public default Categorie get_categorie_by_name(String name){
    }

    private Categorie create_luxe_categorie(){
        return new Categorie(Enumeration.Categorie.Luxe, 100, 1000);
    }

    private Categorie create_confort_categorie(){
        return new Categorie(Enumeration.Categorie.Confort, 75, 750);
    }
    private Categorie create_economique_categorie(){
        return new Categorie(Enumeration.Categorie.Economique, 50, 500);
    }
}
