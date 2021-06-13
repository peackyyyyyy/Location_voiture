package Persistence;

import value_object.model.Enumeration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarburantPersistence extends JdbcConnexion{

    Statement conn;
    public CarburantPersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    /**
     * Retourne un carburant par rapport à ub id
     * @param id l'id du carburant voulu
     * @return
     */
    public Enumeration.Carburant getCarburantAvecId(int id){
        switch(id){
            case 1:
                return Enumeration.Carburant.Gazole;
            case 2:
                return Enumeration.Carburant.Essence;
            case 3:
                return Enumeration.Carburant.SP95;
            case 4:
                return Enumeration.Carburant.GPL;
            case 5:
                return Enumeration.Carburant.Electrique;
        }
        return null;
    }

    /**
     * Retourne l'id d'un carburant
     * @param carbu le carburant recherché
     * @return
     */
    public int getIdCarbu(Enumeration.Carburant carbu){
        switch (carbu){
            case Gazole -> {
                return 1;
            }
            case Essence -> {
                return 2;
            }
            case SP95 -> {
                return 3;
            }
            case GPL -> {
                return 4;
            }
            case Electrique -> {
                return 5;
            }

        }
        return -1;
    }

    /**
     * Methode qui liste tous les carburants
     * @return une liste de carburant
     */
    public ArrayList<Enumeration.Carburant> getCarburants(){
        ArrayList<Enumeration.Carburant> liste = new ArrayList<>();
        liste.add(Enumeration.Carburant.Gazole);
        liste.add(Enumeration.Carburant.Essence);
        liste.add(Enumeration.Carburant.SP95);
        liste.add(Enumeration.Carburant.GPL);
        liste.add(Enumeration.Carburant.Electrique);
        return liste;
    }
}
