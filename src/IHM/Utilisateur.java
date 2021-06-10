package IHM;

import Persistence.*;
import business.ClientManager;
import value_object.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utilisateur extends JFrame implements ActionListener{
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JPanel Voiture;
    private JPanel Client;
    private JPanel Devis;
    private JPanel Facture;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JComboBox comboBox1;
    private JLabel endommage;
    private JLabel model1;
    private JLabel marque;
    private JLabel etat;
    private JLabel carburant;
    private JLabel categorie;
    private JLabel clim;
    private JLabel automatique;
    private JLabel kilometres;
    private JLabel agence;
    private JLabel agenceaetre;

    public Utilisateur() {
    	this.setTitle("Page de connexion");
    	this.setVisible(true);
    	this.setBounds((int) (screenSize.width/2.5),10,350,600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setLayoutManager();
    	this.add(panel, BorderLayout.CENTER);
    	this.addComponentsToContainer();          
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        this.setContentPane(panel);
        this.pack();
        this.setLayoutManager();
        this.addComponentsToContainer();
    }

    private void addComponentsToContainer() {
    	panel.add(Client);
    	panel.add(Voiture);
    	panel.add(Devis);
    	panel.add(Facture);
		
	}
	private void setLayoutManager() {
		panel.setLayout(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void changerMenu(){
        this.setContentPane(this.panel);
        this.revalidate();
    }
	public static void main(String[] args) {
		Utilisateur jFrame = new Utilisateur();
        jFrame.setVisible(true);
    }
}
