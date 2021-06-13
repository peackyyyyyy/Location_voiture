package IHM;
import Persistence.*;
import business.ClientManager;
import business.VoitureManager;
import value_object.*;
import value_object.model.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class ClientMenu extends JFrame implements ActionListener{
    private final ClientManager clientManager;
    private JTabbedPane tabbedPane1;
    private JPanel Fenetre;
    private JTabbedPane tabbedPane2;
    private JPanel Client;
    private JPanel Voiture;
    private JPanel Devis;
    private JPanel Facture;
    private JPanel Ajouterunclient;
    private JPanel Supprimerunclient;
    private JPanel Modifierunclient;
    private JPanel Listedesclients;
    private JTextField Name;
    private JTextField Surname;
    private JTextField Email;
    private JTextField Rue;
    private JTextField Phone;
    private JButton Ajouterclient;
    private JTextField Codepostale;
    private JTextField Ville;
    private JTabbedPane tabbedPane3;
    private JTable tablefind;
    private JComboBox comboModele;
    private JTable ClientTable;
    private JButton ajouterUnClientButton;
    private JButton rechercherUnClientButton;
    private JButton supprimerUnClientButton;
    private  DefaultTableModel modele;
    private  DefaultTableModel mod;
    private  DefaultTableModel mod2;
    private JLabel agenceA_Etre;
    private JLabel marque;
    private JLabel categorie;
    private JLabel etat;
    private JLabel carburant;
    private JLabel clim;
    private JLabel kilometre;
    private JLabel auto;
    private JLabel agence;
    private JLabel endommage;
    private JPanel uneVoiture;
    private JLabel lemodele;
    private JTextField textModele;
    private JTextField textMarque;
    private JTextField textEndommage;
    private JTextField textKilommetre;
    private JComboBox comcoCategorie;
    private JComboBox comboCarburant;
    private JComboBox comboEtat;
    private JComboBox comboAgence;
    private JComboBox comboAgenceAEtre;
    private JCheckBox climCherck;
    private JCheckBox autoCheck;
    private JButton ajouterVoitureButton;
    private JCheckBox endommageCheck;
    private JTextField idTextfield;
    private JTextField modelTextField;
    private JComboBox comboAgence2;
    private JLabel agencelabel;
    private JLabel modelLabel;
    private JComboBox comboEtat2;
    private JButton searchButton;
    private JPanel trouveVoiture;
    private JScrollPane scronnpane;
    private JScrollPane scronnpan;
    private JPanel listevoiture;
    private ClientPersistence clientPersistence;
    private VoiturePersistence voiturePersistence;
    private ArrayList<Voiture> liste;
    private CategoriePersistence categoriePersistence;
    private CarburantPersistence carburantPersistence;
    private AgencePersistence agencePersistence;
    private StatePersistence statePersistence;
    private VoitureManager voitureManager;
    public ClientMenu (ClientManager clientManager, VoitureManager voitureManager, ClientPersistence clientPersistence, VoiturePersistence voiturePersistence, CarburantPersistence carburantPersistence, CategoriePersistence categoriePersistence, StatePersistence statePersistence, AgencePersistence agencePersistence) throws SQLException {
        super();
        this.clientPersistence = clientPersistence;
        this.voitureManager = voitureManager;
        this.voiturePersistence = voiturePersistence;
        this.clientManager = clientManager;
        this.carburantPersistence = carburantPersistence;
        this.categoriePersistence = categoriePersistence;
        this.agencePersistence = agencePersistence;
        this.statePersistence = statePersistence;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Fenetre);
        this.setLayoutManager();
        this.addActionEvent();
        JTable client_table = new JTable();
        // create a table modele and set a Column Identifiers to this modele
        Object[] columns = {"Id", "Nom", "Prenom", "Email", "Adresse", "Phone"};
        modele = new DefaultTableModel();
        modele.setColumnIdentifiers(columns);
        client_table.setModel(modele);
        client_table.setBackground(Color.LIGHT_GRAY);
        client_table.setForeground(Color.black);
        Font font = new Font("",1,14);
        client_table.setFont(font);
        client_table.setRowHeight(30);
        JScrollPane client_pane = new JScrollPane(client_table);
        client_pane.setBounds(0, 0, 1200, 800);
        setClient_table();
        Listedesclients.add(client_pane);



        this.liste = voitureManager.getVoitures();
        voitureManager.setVoitures(liste);

        Voiture vt = liste.get(0);
        lemodele.setText("Modele : " + vt.getModel());
        marque.setText("Marque : " + vt.getMarque());
        kilometre.setText("Nombre de kilomettre : "+ String.valueOf(vt.getKilometers()));
        auto.setText("Automatique : " + String.valueOf(vt.isVitesse()));
        clim.setText("Climatisé : "  + String.valueOf(vt.isClim()));
        endommage.setText("Endommagé : " + String.valueOf(vt.isEndommage()));
        carburant.setText("Carburant : " + vt.getCarburant().toString());
        categorie.setText("Categorie : " + vt.getCategorie().toString());
        etat.setText("Etate : " + vt.getState().toString());
        agence.setText("Agence : " + vt.getAgence().getName());
        agenceA_Etre.setText("Agence a etre : " + vt.getAgence_a_etre().getName());
        comboModele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Voiture vt = (Voiture)comboBox.getSelectedItem();
                lemodele.setText("Modele : " + vt.getModel());
                marque.setText("Marque : " + vt.getMarque());
                kilometre.setText("Nombre de kilomettre : "+ String.valueOf(vt.getKilometers()));
                auto.setText("Automatique : " + String.valueOf(vt.isVitesse()));
                clim.setText("Climatisé : "  + String.valueOf(vt.isClim()));
                endommage.setText("Endommagé : " + String.valueOf(vt.isEndommage()));
                carburant.setText("Carburant : " + vt.getCarburant().toString());
                categorie.setText("Categorie : " + vt.getCategorie().toString());
                etat.setText("Etate : " + vt.getState().toString());
                agence.setText("Agence : " + vt.getAgence().getName());
                agenceA_Etre.setText("Agence a etre : " + vt.getAgence_a_etre().getName());
            }
        });

        Object[] columnss = {"Id", "Modele", "Marque", "Kilometre", "Automatique", "Climatisé","Endommagé","Type de Carburant","Catégorie","Etat","Agence","Agence a etre"};
        this.mod = new DefaultTableModel();
        mod.setColumnIdentifiers(columnss);
        listevoiture = new JPanel();
        listevoiture.setVisible(true);
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
        listevoiture.setSize(1400,1000);
        listevoiture.add(voi_pane);
        tabbedPane3.add("Liste Voiture",listevoiture);
        populateCombo();
        this.pack();
        ajouterVoitureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Voiture voiture = new Voiture(
                            textMarque.getText(),
                            textModele.getText(),
                            Integer.parseInt(textKilommetre.getText()),
                            endommageCheck.isSelected(),
                            autoCheck.isSelected(),
                            climCherck.isSelected(),
                            (Agence) comboAgence.getSelectedItem(),
                            (Agence) comboAgenceAEtre.getSelectedItem(),
                            (ICategorie) comcoCategorie.getSelectedItem(),
                            (Enumeration.Carburant) comboCarburant.getSelectedItem(),
                            (Enumeration.State) comboEtat.getSelectedItem()
                    );
                    int id = voitureManager.add_voiture(voiture);
                    voiture.setId(id);
                    JOptionPane.showMessageDialog(listevoiture, "Voiture Ajouté");
                    addRowTableVoiture(mod,voiture);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextfield.getText();
                String modele = modelTextField.getText();
                Agence agence = (Agence)  comboAgence2.getSelectedItem();
                Enumeration.State etat = (Enumeration.State) comboEtat2.getSelectedItem();
                Optional<Integer> opid;
                Optional<String> opModele;
                Optional<Agence> optionalAgence;
                Optional<Enumeration.State> optionalState;

                if(id.isEmpty()){
                    opid = Optional.empty();
                }
                else {
                    opid = Optional.of(Integer.parseInt(id));
                }

                if(modele.isEmpty()){
                    opModele = Optional.empty();
                }
                else{
                    opModele = Optional.of(modele);
                }
                if(agence == null){
                    optionalAgence = Optional.empty();
                }
                else{
                    optionalAgence = Optional.of(agence);
                }
                if (etat == null){
                    optionalState = Optional.empty();
                }
                else{
                    optionalState = Optional.of(etat);
                }
                ArrayList<Voiture> laliste = voitureManager.findVoiture(opid,opModele,optionalState,optionalAgence);

                JTable tableVoiture = new JTable();
                Object[] columnss = {"Id", "Modele", "Marque", "Kilometre", "Automatique", "Climatisé","Endommagé","Type de Carburant","Catégorie","Etat","Agence","Agence a etre"};
                DefaultTableModel mod2 = new DefaultTableModel();
                mod2.setColumnIdentifiers(columnss);
                tableVoiture.setModel(mod2);

                tableVoiture.setBackground(Color.LIGHT_GRAY);
                tableVoiture.setForeground(Color.black);
                Font font = new Font("",1,14);
                tableVoiture.setFont(font);
                tableVoiture.setRowHeight(30);

                JScrollPane voiturescrol = new JScrollPane(tableVoiture);
                voiturescrol.setBounds(0, 0, 1200, 800);

                try{
                    for (Voiture vt:laliste) {
                        System.out.println(vt.getModel());
                        addRowTableVoiture(mod2,vt);
                    }
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                scronnpane.add(voiturescrol);
            }
        });
    }

    public void setLayoutManager() {
        Listedesclients.setLayout(null);
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
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private void setCombocategorie(){

    }
    private void addRowTableVoiture(DefaultTableModel modele,Voiture vt){
        try {
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
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
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
            }

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    private void setClient_table(){
        try {
            for (Client client: clientManager.getClients()){
                Object[] row = new Object[6];
                row[0] = client.getId();
                row[1] = client.getName();
                row[2] = client.getSurname();
                row[3] = client.getEmail();
                row[4] = client.getAdresse().getVille();
                row[5] = client.getPhone();
                modele.addRow(row);

            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private void addActionEvent() {
        Ajouterclient.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Ajouterclient) {
            String name = Name.getText();
            String surname = Surname.getText();
            String email = Email.getText();
            String rue = Rue.getText();
            String codepostal = Codepostale.getText();
            String ville = Ville.getText();
            String phone = Phone.getText();
            /*this.clientManager.add_client(name, surname, email, new Adresse(rue, ville, codepostal), phone, 1);
            Name.setText("");
            Surname.setText("");
            Email.setText("");
            Rue.setText("");
            Codepostale.setText("");
            Ville.setText("");
            Phone.setText("");*/
            int id = -1;
            try {
                id = clientManager.add_client(name,surname,email,
                        new Adresse(rue,ville,codepostal),
                        phone);
                Object[] row = new Object[6];
                row[0] = id;
                row[1] = name;
                row[2] = surname;
                row[3] = email;
                row[4] = ville;
                row[5] = phone;
                modele.addRow(row);
                JOptionPane.showMessageDialog(this, "Client Ajouté");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

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

        ArrayList<Client> clientsArrayList = new ArrayList<>();
        ClientManager clientManager = new ClientManager(clientsArrayList, clientp);
        VoitureManager voitureManager  = new VoitureManager(vp);
        JFrame jFrame = new ClientMenu(clientManager,voitureManager,clientp,vp,carbup,cp,stp,ap);
        jFrame.setVisible(true);
    }
}
