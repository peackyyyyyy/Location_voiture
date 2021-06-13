package Persistence;

import value_object.model.Enumeration;

import java.sql.SQLException;
import java.sql.Statement;

public class StatePersistence {


    Statement conn;
    public StatePersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    public Enumeration.State getStateAvecId(int id){
        switch(id){
            case 1:
                return Enumeration.State.Reserver;
            case 2:
                return Enumeration.State.Louer;
            case 3:
                return Enumeration.State.Libre;

        }
        return null;
    }

    public int getIdState(Enumeration.State state){
        switch (state){
            case Reserver -> {
                return 1;
            }
            case Louer -> {
                return 2;
            }
            case Libre -> {
                return 3;
            }

        }
        return -1;
    }
}
