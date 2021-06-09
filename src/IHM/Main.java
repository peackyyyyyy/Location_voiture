package IHM;

import Persistence.*;

import value_object.*;
import value_object.Categorie.Confort;
import value_object.model.Enumeration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException{
		JdbcConnexion jdbc = new JdbcConnexion();

        Statement con = jdbc.getConn();
        Connection connexion = jdbc.getConnexion();
        EmployePersistence ep = new EmployePersistence(con,connexion);
        ArrayList<Employe> listeemploye =ep.getEmployes();
        System.out.println(ep);
        for (Employe employe : listeemploye) {
			System.out.println(employe.getLogin());
			System.out.println(employe.getMdp());
		}

		LoginFrame frame=new LoginFrame(listeemploye);

        

	}

}
