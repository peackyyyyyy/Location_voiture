package Persistence;

import value_object.Categorie.Confort;
import value_object.Categorie.Economique;
import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;

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
}
