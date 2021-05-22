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
        StatePersistence stp = new StatePersistence(con);
        FidelitePersistence fp = new FidelitePersistence(con,connexion);
        VoiturePersistence vp = new VoiturePersistence(con,connexion,cp,carbup,stp);
        ClientPersistence clientp = new ClientPersistence(con,connexion,vp,fp);
        EmployePersistence ep = new EmployePersistence(con,connexion);
        DevisPersistence dep = new DevisPersistence(connexion,con,vp,clientp);

        Client cl = new Client("marie",
                "loutre",
                "lou@gmail.com",
                new Adresse("la rue","la ville","codepostal"),
                "06",
                new Voiture(
                        "marque",
                        "model",
                        200,
                        true,
                        true,
                        true,
                        new Confort(),
                        Enumeration.Carburant.Gazole,
                        Enumeration.State.Reserver),
                null);

       Employe employe = new Employe(
                "mari",
                "lou",
                "lou@gmail.com",
                new Adresse("lrgrgrgrgrgggrgr","la ville","codepostal"),
                "06",
                "login","mdp"
        );

        Voiture voi = new Voiture(
                "marque",
                "model",
                200,
                true,
                true,
                true,
                new Confort(),
                Enumeration.Carburant.Gazole,
                Enumeration.State.Reserver);

        Devis lede = new Devis(voi,
                cl,
                Utilities.strToDate("2021-11-22"),
                Utilities.strToDate("2021-6-22")
        );
        System.out.println(vp.insertVoiture(voi));

        for (Devis d:dep.getDevis()) {
           System.out.println(d);
        }
    }
}
