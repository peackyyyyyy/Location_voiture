package test;

import Persistence.*;
import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.SQLException;
import java.sql.Statement;

public class TstPersitence {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcConnexion jdbc = new JdbcConnexion();

        Statement con = jdbc.getConn();

        CategoriePersistence cp = new CategoriePersistence(con);
        CarburantPersistence carbup = new CarburantPersistence(con);
        FidelitePersistence fp = new FidelitePersistence(con);
        VoiturePersistence vp = new VoiturePersistence(con,cp,carbup);
        ClientPersistence clientp = new ClientPersistence(con,vp,fp);
        EmployePersistence ep = new EmployePersistence(con);
        System.out.println(ep.getEmployeAvecId(1));
    }
}
