package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import value_object.Employe;
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

public class ChoiceUser extends JFrame implements ActionListener{
	JPanel panel = new JPanel(new GridLayout(3, 1));  
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JButton administrateurButton = new JButton("Administrateur");
    JButton agentButton = new JButton("Agent");
    JButton utilisateurButton = new JButton("Utilisateur");
    
    public ChoiceUser() {
    	this.setTitle("Page de connexion");
    	this.setVisible(true);
    	this.setBounds((int) (screenSize.width/2.5),10,350,600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setLayoutManager();
    	this.add(panel, BorderLayout.CENTER);
    	this.setLocationAndSize();
    	this.addComponentsToContainer();
        this.addActionEvent();             
    }
	public void setLayoutManager() {
    	panel.setLayout(null);
    }

    public void setLocationAndSize() {
    	administrateurButton.setBounds(100, 200, 100, 30);
    	agentButton.setBounds(100, 300, 100, 30);
    	utilisateurButton.setBounds(100, 400, 100, 30);


    }

    public void addComponentsToContainer() {
    	panel.add(administrateurButton);
    	panel.add(agentButton);
    	panel.add(utilisateurButton);
    }

    public void addActionEvent() {
    	administrateurButton.addActionListener(this);
    	agentButton.addActionListener(this);
    	utilisateurButton.addActionListener(this);
    }

	/**
	 *
	 * @param e
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == administrateurButton) {
        	try {
        		dispose();
				AdminLogin admin=new AdminLogin();
				admin.changerMenu();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if (e.getSource() == agentButton) {
			try {
				dispose();
				ChauffeurLogin chauffeur=new ChauffeurLogin();
				chauffeur.changerMenu();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if (e.getSource() == utilisateurButton) {
        	dispose();
			try {
				UtilisateurMenu.main(null);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}
        }
    }

	/**
	 * M??thode qui permet de changer de jpanel
	 */
	public void changerMenu(){
        this.setContentPane(this.panel);
        this.revalidate();
    }
}
