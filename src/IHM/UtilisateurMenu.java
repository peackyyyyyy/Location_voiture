package IHM;
import Persistence.*;
import business.ClientManager;
import business.DevisManager;
import business.VoitureManager;
import value_object.*;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

public class UtilisateurMenu extends JFrame implements ActionListener{
    private final ClientManager clientManager;
    private final DevisManager devisManager;
    private final VoitureManager voitureManager;
    private JPanel Fenetre;
    private JPanel Listedesclients;
    private JPanel Devis;
    private JPanel Client;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField Name;
    private JTextField Surname;
    private JTextField Email;
    private JTextField Rue;
    private JTextField Phone;
    private JTextField Codepostale;
    private JTextField Ville;
    private JTextField IdRechercheclient;
    private JTextField NameRechercheClient;
    private JTextField SurnameRechercheClient;
    private JButton RechercheClient;
    private JScrollPane ListeRechercheClient;
    private JTabbedPane Listelocation;
    private JTextField idVoiturefield;
    private JTextField idclientfield;
    private JCheckBox checkdatefinlocation;
    private JTextField idLocation;
    private JButton FinLocation;
    private JCheckBox endommageCheckBox;
    private JButton enregistrerButton;
    private JLabel datedefinlabel;
    private JLabel agenceA_Etre;
    private JLabel kilometre;
    private JLabel clim;
    private JLabel carburant;
    private JLabel etat;
    private JLabel marque;
    private JLabel categorie;
    private JLabel auto;
    private JLabel agence;
    private JLabel endommage;
    private JComboBox comboModele;
    private JLabel lemodele;
    private JTextField textModele;
    private JTextField textMarque;
    private JTextField textKilommetre;
    private JComboBox comcoCategorie;
    private JComboBox comboCarburant;
    private JComboBox comboEtat;
    private JComboBox comboAgence;
    private JComboBox comboAgenceAEtre;
    private JCheckBox climCherck;
    private JCheckBox autoCheck;
    private JCheckBox endommageCheck;
    private JButton ajouterVoitureButton;
    private JTextField modelTextField;
    private JComboBox comboEtat2;
    private JComboBox comboAgence2;
    private JButton searchButton;
    private JTextField idTextfield;
    private JScrollPane scronnpane;
    private JPanel Voiture;
    private JPanel listeVoiture;
    private JTextField textfiledIdTobutton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JPanel paneldelistelocation;
    private JButton button1;
    private JTextField IdLocationFacture;
    private JButton genererFactureButton;
    private JTextPane Facturetexte;
    private JTable ClientTable;
    private JButton rechercherUnClientButton;
    private JButton supprimerUnClientButton;
    private DefaultTableModel model;
    private DefaultTableModel model_locations;
    private DefaultTableModel mod;
    private DefaultTableModel mod2;
    private ClientPersistence clientPersistence;
    private VoiturePersistence voiturePersistence;
    private CarburantPersistence carburantPersistence;
    private CategoriePersistence categoriePersistence;
    private AgencePersistence agencePersistence;
    private StatePersistence statePersistence;
    private JTable tablefind;

    public UtilisateurMenu (ClientManager clientManager, DevisManager devisManager, VoitureManager voitureManager, ClientPersistence clientPersistence, VoiturePersistence voiturePersistence, CarburantPersistence carburantPersistence, CategoriePersistence categoriePersistence, StatePersistence statePersistence, AgencePersistence agencePersistence) throws SQLException {
        super();
        this.clientPersistence = clientPersistence;
        this.voitureManager = voitureManager;
        this.voiturePersistence = voiturePersistence;
        this.clientManager = clientManager;
        this.carburantPersistence = carburantPersistence;
        this.categoriePersistence = categoriePersistence;
        this.agencePersistence = agencePersistence;
        this.statePersistence = statePersistence;
        this.devisManager = devisManager;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Fenetre);
        this.pack();
        this.setLayoutManager();
        this.addActionEvent();
        JTable client_table = new JTable();
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Id", "Nom", "Prénom", "Email", "Adresse", "Phone", "Fidélité"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        client_table.setModel(model);
        client_table.setBackground(Color.LIGHT_GRAY);
        client_table.setForeground(Color.black);
        Font font = new Font("",1,14);
        client_table.setFont(font);
        client_table.setRowHeight(30);
        JScrollPane client_pane = new JScrollPane(client_table);
        client_pane.setBounds(0, 0, 1200, 800);
        setClient_table();
        Listedesclients.add(client_pane);

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        JTable location_table = new JTable();
        Object[] columns_location = {"Id", "Id Client", "Nom", "Prenom", "Id Voiture", "Model", "Marque", "Début", "Fin"};
        model_locations = new DefaultTableModel();
        model_locations.setColumnIdentifiers(columns_location);
        location_table.setModel(model_locations);
        location_table.setBackground(Color.LIGHT_GRAY);
        location_table.setForeground(Color.black);
        Font font_location = new Font("",1,14);
        location_table.setFont(font_location);
        location_table.setRowHeight(30);
        JScrollPane location_pane = new JScrollPane(location_table);
        location_pane.setBounds(0, 0, 1400, 1000);
        setLocation_table();
        paneldelistelocation.add(location_pane);

        voitureManager.setVoitures(voitureManager.getVoitures());

        Voiture vt = voitureManager.getVoitures().get(0);

        Object[] columnss = {"Id", "Modele", "Marque", "Kilometre", "Automatique", "Climatisé","Endommagé","Type de Carburant","Catégorie","Etat","Agence","Agence a etre"};
        this.mod = new DefaultTableModel();
        mod.setColumnIdentifiers(columnss);
        tablefind =  new JTable(mod)
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
        tablefind.setModel(mod);
        tablefind.setBackground(Color.LIGHT_GRAY);
        tablefind.setForeground(Color.black);
        tablefind.setSize(1400,1000);
        Font fonti = new Font("",1,14);
        tablefind.setFont(fonti);
        tablefind.setRowHeight(30);
        tablefind.setVisible(true);

        JScrollPane voi_pane = new JScrollPane(tablefind);
        voi_pane.setBounds(0, 0, 1200, 800);
        voi_pane.setVisible(true);
        setVoiture_table();
        voi_pane.setSize(1400,1000);
        listeVoiture.add(voi_pane);
        populateCombo();
        this.pack();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChoiceUser admin=new ChoiceUser();
            }
        });
    }
    private void setLocation_table(){
        try {
            for (Devis devis: this.devisManager.getDevis()){
                Object[] row = new Object[9];
                row[0] = devis.getId();
                row[1] = devis.getClient().getId();
                row[2] = devis.getClient().getName();
                row[3] = devis.getClient().getSurname();
                row[4] = devis.getVoiture().getId();
                row[5] = devis.getVoiture().getModel();
                row[6] = devis.getVoiture().getMarque();
                row[7] = devis.getDebut();
                row[8] = devis.getFin();
                model_locations.addRow(row);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }


    public void setLayoutManager() {
        Listedesclients.setLayout(null);
        listeVoiture.setLayout(null);
        paneldelistelocation.setLayout(null);
    }

    private void populateCombo() throws SQLException{
        try{
            for (value_object.Voiture vt:voitureManager.getVoitures()) {
                comboModele.addItem(vt);
            }
            for (ICategorie cat: categoriePersistence.getCategories()) {
                comcoCategorie.addItem(cat);
            }
            for(Enumeration.Carburant carburant : carburantPersistence.getCarburants()){
                comboCarburant.addItem(carburant);
            }
            for (value_object.Agence agence: agencePersistence.getAgences()) {
                comboAgence.addItem(agence);
                comboAgenceAEtre.addItem(agence);
                comboAgence2.addItem(agence);
            }
            for (Enumeration.State state: statePersistence.getStats()){
                comboEtat.addItem(state);
                comboEtat2.addItem(state);
            }
            comboEtat2.insertItemAt(null,0);
            comboAgence2.insertItemAt(null,0);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void addRowTableVoiture(DefaultTableModel modele,Voiture vt){
        Object[] row;
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
        modele.addRow(row);
    }

    private void setVoiture_table() throws SQLException {
        mod.setRowCount(0);
        Object[] row;
        for (Voiture vt: voitureManager.getVoitures()) {
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
        }

    }

    private void setClient_table(){
        try {
            for (Client client: this.clientManager.getClients()){
                Object[] row = new Object[7];
                row[0] = client.getId();
                row[1] = client.getName();
                row[2] = client.getSurname();
                row[3] = client.getEmail();
                row[4] = client.getAdresse().getVille();
                row[5] = client.getPhone();
                row[6] = client.client_fidelity();
                model.addRow(row);

            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private void addActionEvent() {
    }


    private int return_month(int i){
        if (i == 1) {
            return GregorianCalendar.JANUARY;
        }
        else if(i == 2){
            return GregorianCalendar.FEBRUARY;
        }
        else if(i == 3){
            return GregorianCalendar.MARCH;
        }
        else if(i == 4){
            return GregorianCalendar.APRIL;
        }
        else if(i == 5){
            return GregorianCalendar.MAY;
        }
        else if(i == 6){
            return GregorianCalendar.JUNE;
        }
        else if(i == 7){
            return GregorianCalendar.JULY;
        }
        else if(i == 8){
            return GregorianCalendar.AUGUST;
        }
        else if(i == 9){
            return GregorianCalendar.SEPTEMBER;
        }
        else if(i == 10){
            return GregorianCalendar.OCTOBER;
        }
        else if(i == 11){
            return GregorianCalendar.NOVEMBER;
        }
        else if(i == 12){
            return GregorianCalendar.DECEMBER;
        }

        return i;
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

        ArrayList<Client> clientsArrayList = new ArrayList<>();
        ArrayList<Voiture> voitureArrayList = new ArrayList<>();
        ArrayList<Devis> devisArrayList = new ArrayList<>();
        VoitureManager voitureManager = new VoitureManager(vp);
        DevisManager devisManager = new DevisManager(devisArrayList,dep);
        ClientManager clientManager = new ClientManager(clientsArrayList,clientp);
        JFrame jFrame = new UtilisateurMenu(clientManager,devisManager,voitureManager,clientp,vp,carbup,cp,stp,ap);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
