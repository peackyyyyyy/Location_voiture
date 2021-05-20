package Persistence;

import value_object.ICategorie;
import value_object.model.Enumeration;

import java.sql.SQLException;
import java.sql.Statement;

public class CarburantPersistence extends JdbcConnexion{

    Statement conn;
    public CarburantPersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

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
}
