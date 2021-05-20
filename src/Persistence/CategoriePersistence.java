package Persistence;

import value_object.Categorie.Confort;
import value_object.Categorie.Economique;
import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriePersistence extends JdbcConnexion {

    Statement conn;
    public CategoriePersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    public ICategorie getCategorieAvecId(int id) throws SQLException {
        switch (id){
            case(1):
                return new Confort();
            case(2):
                return new Luxe();
            case(3) :
                return new Economique();
        }
        return null;
    }

    public int getIdCategorie(ICategorie categorie){
        if (categorie.getName() == Enumeration.Categorie.Confort)
            return 1;
        if (categorie.getName() == Enumeration.Categorie.Luxe)
            return 2;
        if (categorie.getName() == Enumeration.Categorie.Economique)
            return 3;
        return -1;
    }
}
