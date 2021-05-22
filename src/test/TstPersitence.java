package test;

import Persistence.*;
import value_object.*;
import value_object.Categorie.Confort;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class TstPersitence {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        JdbcConnexion jdbc = new JdbcConnexion();

        Statement con = jdbc.getConn();
        Connection connexion = jdbc.getConnexion();

        CategoriePersistence cp = new CategoriePersistence(con);
        CarburantPersistence carbup = new CarburantPersistence(con);
        FidelitePersistence fp = new FidelitePersistence(con,connexion);
        VoiturePersistence vp = new VoiturePersistence(con,connexion,cp,carbup);
        ClientPersistence clientp = new ClientPersistence(con,connexion,vp,fp);
        EmployePersistence ep = new EmployePersistence(con,connexion);
        DevisPersistence dep = new DevisPersistence(connexion,con,vp,clientp);

        Client cl = new Client("marie",
                "loutre",
                "lou@gmail.com",
                new Adresse("la rue","la ville","codepostal"),
                "06",
                new Voiture(3,"marque",
                        "model",
                        200,
                        new Confort(),
                        true,
                        true,
                        Enumeration.Carburant.Gazole,
                        false),
                null);
        cl.setId(5);

       Employe employe = new Employe(
                "mari",
                "lou",
                "lou@gmail.com",
                new Adresse("lrgrgrgrgrgggrgr","la ville","codepostal"),
                "06",
                "login","mdp"
        );

        Voiture voi = new Voiture(3,"lavraimarque",
                "model",
                200,
                new Confort(),
                true,
                true,
                Enumeration.Carburant.Gazole,
                false);
        Devis lede = new Devis(voi,
                cl,
                Utilities.strToDate("2021-11-22"),
                Utilities.strToDate("2021-6-22")
        );
        System.out.println(dep.deleteDevis(5));
        System.out.println(dep.deleteDevis(6));
        System.out.println(dep.deleteDevis(7));
        System.out.println(dep.deleteDevis(8));

        for (Devis d:dep.getDevis()) {
           System.out.println(d);
        }
    }
}
