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
import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException{
		JdbcConnexion jdbc = new JdbcConnexion();

        Statement con = jdbc.getConn();
        Connection connexion = jdbc.getConnexion();
        EmployePersistence ep = new EmployePersistence(con,connexion);
        ArrayList<Employe> listeemploye =ep.getEmployes();
        
        System.out.println("1:Administrateur,2:Agent,3:Utilisateur");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        switch (id) {
		case 1:
			LoginFrame admin=new LoginFrame(listeemploye);
			break;
		case 2:
			System.out.println("Agent");
			break;
		case 3:
			System.out.println("Utilisateur");
			break;

		default:
			break;
		}
		
		//ChoiceUser frame=new ChoiceUser();
		
        

	}

}
