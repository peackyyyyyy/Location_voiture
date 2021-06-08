package IHM;

import Persistence.*;
import value_object.Voiture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Chauffeur extends JFrame implements ActionListener{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel Voiture;
    private JPanel listevoiture;
    private JTable table1;
    private JComboBox comboBox1;
    private JLabel model;
    private JLabel marque;
    private JLabel kilometres;
    private JLabel endommage;
    private JLabel automatique;
    private JLabel clim;
    private JLabel categorie;
    private JLabel carburant;
    private JLabel etat;
    private JLabel agence;
    private VoiturePersistence voiturePersistence;

    public Chauffeur(VoiturePersistence voiturePersistence) throws SQLException {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Voiture);
        this.voiturePersistence = voiturePersistence;
        this.pack();
        ArrayList<Voiture> listeVoiture = voiturePersistence.getVoitures();
        for (Voiture vt:listeVoiture) {
            comboBox1.addItem(vt);
        }

        Voiture vt = listeVoiture.get(0);
        model.setText("Modele : " + vt.getModel());
        marque.setText("Marque : " + vt.getMarque());
        kilometres.setText("Nombre de kilomettre : "+ String.valueOf(vt.getKilometers()));
        automatique.setText("Automatique : " + String.valueOf(vt.isVitesse()));
        clim.setText("Climatisé : "  + String.valueOf(vt.isClim()));
        endommage.setText("Endommagé : " + String.valueOf(vt.isEndommage()));
        categorie.setText("Categorie : " + vt.getCategorie().toString());
        etat.setText("Etate : " + vt.getState().toString());

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Voiture vt = (Voiture)comboBox.getSelectedItem();
                model.setText("Modele : " + vt.getModel());
                marque.setText("Marque : " + vt.getMarque());
                kilometres.setText("Nombre de kilomettre : "+ String.valueOf(vt.getKilometers()));
                automatique.setText("Automatique : " + String.valueOf(vt.isVitesse()));
                clim.setText("Climatisé : "  + String.valueOf(vt.isClim()));
                endommage.setText("Endommagé : " + String.valueOf(vt.isEndommage()));
                categorie.setText("Categorie : " + vt.getCategorie().toString());
                etat.setText("Etate : " + vt.getState().toString());
                }
        });
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

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

        JFrame chauffeur=new Chauffeur(vp);
        chauffeur.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
