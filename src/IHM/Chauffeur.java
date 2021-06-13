package IHM;

import Persistence.*;
import value_object.Agence;
import value_object.Client;
import value_object.Voiture;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Chauffeur extends JFrame implements ActionListener{
    private JTabbedPane tabed;
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
    private JLabel agenceaetre;
    private JButton button1;
    private DefaultTableModel mod;
    private VoiturePersistence voiturePersistence;
    private ArrayList<Voiture> liste;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Chauffeur(VoiturePersistence voiturePersistence) throws SQLException {
        super();
        this.setBounds((int) (screenSize.width/2.5),10,500,600);
        this.setTitle("Chauffeur");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel1.add(tabed);
        panel1.setVisible(true);
        this.setContentPane(panel1);
        tabed.setVisible(true);
        this.voiturePersistence = voiturePersistence;
        this.liste = voiturePersistence.getVoitures();
        for (Voiture vt:liste) {
            comboBox1.addItem(vt);
        }

        Voiture vt = liste.get(0);
        model.setText("Modele : " + vt.getModel());
        marque.setText("Marque : " + vt.getMarque());
        kilometres.setText("Nombre de kilomettre : "+ String.valueOf(vt.getKilometers()));
        automatique.setText("Automatique : " + String.valueOf(vt.isVitesse()));
        clim.setText("Climatisé : "  + String.valueOf(vt.isClim()));
        endommage.setText("Endommagé : " + String.valueOf(vt.isEndommage()));
        carburant.setText("Carburant : " + vt.getCarburant().toString());
        categorie.setText("Categorie : " + vt.getCategorie().toString());
        etat.setText("Etate : " + vt.getState().toString());
        agence.setText("Agence : " + vt.getAgence().getName());
        agenceaetre.setText("Agence a etre : " + vt.getAgence_a_etre().getName());
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
                carburant.setText("Carburant : " + vt.getCarburant().toString());
                categorie.setText("Categorie : " + vt.getCategorie().toString());
                etat.setText("Etate : " + vt.getState().toString());
                agence.setText("Agence : " + vt.getAgence().getName());
                agenceaetre.setText("Agence a etre : " + vt.getAgence_a_etre().getName());
                }
        });

        Object[] columns = {"Id", "Modele", "Marque", "Kilometre", "Automatique", "Climatisé","Endommagé","Type de Carburant","Catégorie","Etat","Agence","Agence a etre"};
        this.mod = new DefaultTableModel();
        mod.setColumnIdentifiers(columns);
        listevoiture = new JPanel();
        listevoiture.setVisible(true);
        table1 =  new JTable(mod)
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component comp = super.prepareRenderer(renderer, row, column);
                Agence value1 = (Agence) getModel().getValueAt(row, 10);
                Agence value2 = (Agence) getModel().getValueAt(row, 11);
                if(value1.getId() !=  value2.getId()){
                    comp.setBackground(Color.red);
                }
                else{
                    comp.setBackground(Color.white);
                }

                return comp;
            }
        };
        table1.setModel(mod);
        table1.setBackground(Color.LIGHT_GRAY);
        table1.setForeground(Color.black);
        Font font = new Font("",1,14);
        table1.setFont(font);
        table1.setRowHeight(30);
        table1.setVisible(true);

        JScrollPane voi_pane = new JScrollPane(table1);
        voi_pane.setBounds(0, 0, 1200, 800);
        voi_pane.setVisible(true);
        setVoiture_table();
        listevoiture.add(voi_pane);
        tabed.add("Liste Voiture",listevoiture);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChoiceUser admin=new ChoiceUser();


            }
        });
    }

    private void setVoiture_table(){
        try {
            Object[] row;
            for (Voiture vt: liste) {
                row = new Object[12];
                row[0] = vt.getId();
                row[1] = vt.getModel();
                row[2] = vt.getMarque();
                row[3] = vt.getKilometers();
                row[4] = vt.isVitesse();
                row[5] = vt.isClim();
                row[6] = vt.isEndommage();
                row[7] = vt.getCarburant();
                row[8] = vt.getCategorie();
                row[9] = vt.getState();
                row[10] = vt.getAgence();
                row[11] = vt.getAgence_a_etre();
                mod.addRow(row);
                table1.getCellRenderer(table1.getRowCount() - 1, 11).getTableCellRendererComponent(table1,vt.getAgence_a_etre().getId() != vt.getAgence().getId(),false, false, table1.getRowCount() - 1, 11).setBackground(Color.red);

            }

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        JdbcConnexion jdbc = new JdbcConnexion();

        Statement con = jdbc.getConn();
        Connection connexion = jdbc.getConnexion();

        CategoriePersistence cp = new CategoriePersistence(con);
        CarburantPersistence carbup = new CarburantPersistence(con);
        StatePersistence stp = new StatePersistence(con);
        FidelitePersistence fp = new FidelitePersistence(con,connexion);
        AgencePersistence ap = new AgencePersistence(con,connexion);
        VoiturePersistence vp = new VoiturePersistence(con,connexion,cp,carbup,stp,ap);
        ClientPersistence clientp = new ClientPersistence(con,connexion,vp,fp);
        EmployePersistence ep = new EmployePersistence(con,connexion);
        DevisPersistence dep = new DevisPersistence(connexion,con,vp,clientp);

        JFrame chauffeur=new Chauffeur(vp);
        chauffeur.setVisible(true);

    }

    public void setLayoutManager() {
        listevoiture.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
