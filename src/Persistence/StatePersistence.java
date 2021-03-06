package Persistence;

import value_object.model.Enumeration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatePersistence {


    Statement conn;
    public StatePersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    /**
     * Retourne un état par rapport à un id
     * @param id l'id de l'etat souhaité
     * @return l'etat souhaité
     */
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

    /**
     * Retourne un id par rapport à un etat
     * @param state l'eat que l'on recherche
     * @return l'id de l'etat souhaité
     */
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

    /**
     * Liste tous les états
     * @return la liste des états
     */
    public ArrayList<Enumeration.State> getStats(){
        ArrayList<Enumeration.State> liste = new ArrayList<>();
        liste.add(Enumeration.State.Reserver);
        liste.add(Enumeration.State.Louer);
        liste.add(Enumeration.State.Libre);
        return liste;
    }
}
