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

public class ClientMenu extends JFrame implements ActionListener{
    private final ClientManager clientManager;
    private final DevisManager devisManager;
    private final VoitureManager voitureManager;
    private JTabbedPane tabbedPane1;
    private JPanel Fenetre;
    private JTabbedPane tabbedPane2;
    private JPanel Client;
    private JPanel Devis;
    private JPanel Facture;
    private JPanel Ajouterunclient;
    private JPanel Rechercherunclient;
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
    private JTextField IdRechercheclient;
    private JTextField NameRechercheClient;
    private JTextField SurnameRechercheClient;
    private JButton RechercheClient;
    private JScrollPane ListeRechercheClient;
    private JCheckBox checkBox1;
    private JTabbedPane Listelocation;
    private JTextField idVoiturefield;
    private JTextField idclientfield;
    private JComboBox daylocationdebutbox;
    private JComboBox monthlocationdebutbox;
    private JComboBox yearlocationdebutbox;
    private JComboBox daylocationfinbox;
    private JComboBox monthlocationfinbox;
    private JComboBox yearlocationfinbox;
    private JCheckBox checkdatefinlocation;
    private JButton AjouterLocation;
    private JTextField idLocation;
    private JButton FinLocation;
    private JCheckBox endommageCheckBox;
    private JButton enregistrerButton;
    private JComboBox datedefinlocation_day;
    private JComboBox datedefinlocation_month;
    private JComboBox datedefinlocation_year;
    private JLabel datedefinlabel;
    private JPanel uneVoiture;
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
    private JPanel trouveVoiture;
    private JLabel modelLabel;
    private JTextField modelTextField;
    private JComboBox comboEtat2;
    private JLabel agencelabel;
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
    private JTextField IdLocationFacture;
    private JButton genererFactureButton;
    private JTextPane Facturetexte;
    private JTable ClientTable;
    private JButton ajouterUnClientButton;
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

    public ClientMenu (ClientManager clientManager, DevisManager devisManager, VoitureManager voitureManager, ClientPersistence clientPersistence, VoiturePersistence voiturePersistence, CarburantPersistence carburantPersistence, CategoriePersistence categoriePersistence, StatePersistence statePersistence, AgencePersistence agencePersistence) throws SQLException {
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
                    JOptionPane.showMessageDialog(listeVoiture, "Voiture Ajouté");
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
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textfiledIdTobutton.getText());
                TableModel model = tablefind.getModel();
                int i;
                for( i = 0; i < tablefind.getRowCount();i++){
                    if(id == (Integer) model.getValueAt(i,0)) {
                        break;
                    }
                }
                try {
                    voitureManager.updateVoiture(id,new Voiture(
                            (String) model.getValueAt(i,2),
                            (String)model.getValueAt(i,1),
                            (Integer) model.getValueAt(i,3),
                            model.getValueAt(i, 6).equals("true"),
                            model.getValueAt(i, 4).equals("true"),
                            model.getValueAt(i, 5).equals("true"),
                            (Agence) model.getValueAt(i,10),
                            (Agence)model.getValueAt(i,11),
                            (ICategorie) model.getValueAt(i,8),
                            (Enumeration.Carburant) model.getValueAt(i,7),
                            (Enumeration.State) model.getValueAt(i,9)
                    ));
                    setVoiture_table();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
        Ajouterclient.addActionListener(this);
        RechercheClient.addActionListener(this);
        AjouterLocation.addActionListener(this);
        FinLocation.addActionListener(this);
        enregistrerButton.addActionListener(this);
        genererFactureButton.addActionListener(this);
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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Ajouterclient) {
            String name = Name.getText();
            String surname = Surname.getText();
            String email = Email.getText();
            String rue = Rue.getText();
            String codepostal = Codepostale.getText();
            String ville = Ville.getText();
            String phone = Phone.getText();
            try {
                this.clientManager.add_client(name, surname, email, new Adresse(rue, ville, codepostal), phone);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Name.setText("");
            Surname.setText("");
            Email.setText("");
            Rue.setText("");
            Codepostale.setText("");
            Ville.setText("");
            Phone.setText("");
            Object[] row = new Object[6];
            row[0] = 1;
            row[1] = name;
            row[2] = surname;
            row[3] = email;
            row[4] = ville;
            row[5] = phone;
            model.addRow(row);
            JOptionPane.showMessageDialog(this, "Client Ajouté");



        }
        else if(e.getSource() == genererFactureButton){
            Facturetexte.setText("");
            String id_location = IdLocationFacture.getText();
            Devis devis;
            try {
                devis = this.devisManager.get_devis_by_id(Integer.parseInt(id_location));
            }
            catch (Exception exeptionfacture){
                JOptionPane.showMessageDialog(this, "Pas de devis pour cette ID");
                IdLocationFacture.setText("");
                return;
            }
            this.devisManager.generate_facture_by_id(devis.getId());
            if (devis.getClient().getFidelite() == null){
                Facturetexte.setText("                  Facture numero "+devis.getId()+"\n\nClient numero "+devis.getClient().getId()+"     Nom: "+devis.getClient().getName()+"\n    Prenom: "+devis.getClient().getSurname()+"\n\nVoiture numero "+devis.getVoiture().getId()+"\n     Marque: "+devis.getVoiture().getMarque()+"\n    Model: "+devis.getVoiture().getModel()+"\n      Prix: "+devis.getVoiture().getCategorie().getTarif()+"\n\n      Début Location: "+devis.getDebut()+"\n      Fin Location: "+devis.getFin()+"\n\n       Montant: "+devis.getFacture().getPrice()+"\n     Montant à payer: "+devis.getFacture().getFinalprice());
            }
            else {
                Facturetexte.setText("                  Facture numero "+devis.getId()+"\n\nClient numero "+devis.getClient().getId()+"\tNom: "+devis.getClient().getName()+"\n\tPrenom: "+devis.getClient().getSurname()+"\n\tFidélité: "+devis.getClient().getFidelite().getReduction()+"\n\nVoiture numero "+devis.getVoiture().getId()+"\n     Marque: "+devis.getVoiture().getMarque()+"\n    Model: "+devis.getVoiture().getModel()+"\n      Prix: "+devis.getVoiture().getCategorie().getTarif()+"\n\n      Début Location: "+devis.getDebut()+"\n\tFin Location: "+devis.getFin()+"\n\n\tMontant: "+devis.getFacture().getPrice()+"\n\tMontant à payer: "+devis.getFacture().getFinalprice());

            }
        }
        else if (e.getSource() == AjouterLocation){
            String id_voiture = idVoiturefield.getText();
            String id_client = idclientfield.getText();
            int day_debut = Integer.parseInt(String.valueOf(daylocationdebutbox.getSelectedItem()));
            int month_debut = Integer.parseInt(String.valueOf(monthlocationdebutbox.getSelectedItem()));
            int year_debut = Integer.parseInt(String.valueOf(yearlocationdebutbox.getSelectedItem()));
            Voiture voiture = this.voitureManager.get_voiture_by_id(Integer.parseInt(id_voiture));
            Client client = this.clientManager.get_client_by_id(Integer.parseInt(id_client));
            Date date_debut = new GregorianCalendar(year_debut, return_month(month_debut), day_debut).getTime();
            Date date_fin = null;
            Devis devis = null;
            try {
                devis = this.devisManager.add_devi(voiture, client, date_debut);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (checkdatefinlocation.isSelected()){
                int day_fin = Integer.parseInt(String.valueOf(daylocationfinbox.getSelectedItem()));
                int month_fin = Integer.parseInt(String.valueOf(monthlocationfinbox.getSelectedItem()));
                int year_fin = Integer.parseInt(String.valueOf(yearlocationfinbox.getSelectedItem()));
                date_fin = new GregorianCalendar(year_fin, return_month(month_fin), day_fin).getTime();
                try {
                    assert devis != null;
                    this.devisManager.update_fin_devis_by_id(devis.getId(), date_fin);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            Object[] row = new Object[9];
            row[0] = devis.getId();
            row[1] = client.getId();
            row[2] = client.getName();
            row[3] = client.getSurname();
            row[4] = voiture.getId();
            row[5] = voiture.getModel();
            row[6] = voiture.getMarque();
            row[7] = date_debut;
            row[8] = date_fin;
            model_locations.addRow(row);
            JOptionPane.showMessageDialog(this, "Location Effectué");
        }
        else if (e.getSource() == enregistrerButton){
            FinLocation.setVisible(false);
            int id = Integer.parseInt(idLocation.getText());
            Devis result = this.devisManager.get_devis_by_id(id);
            if (enregistrerButton.isSelected()){
                result.getVoiture().setEndommage(true);
            }
            int day_fin = Integer.parseInt(String.valueOf(datedefinlocation_day.getSelectedItem()));
            int month_fin = Integer.parseInt(String.valueOf(datedefinlocation_month.getSelectedItem()));
            int year_fin = Integer.parseInt(String.valueOf(datedefinlocation_year.getSelectedItem()));
            Date date_fin = new GregorianCalendar(year_fin, return_month(month_fin), day_fin).getTime();
            try {
                this.devisManager.update_fin_devis_by_id(id, date_fin);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            endommageCheckBox.setVisible(false);
            datedefinlabel.setVisible(false);
            datedefinlocation_day.setVisible(false);
            datedefinlocation_month.setVisible(false);
            datedefinlocation_year.setVisible(false);
            enregistrerButton.setVisible(false);
            FinLocation.setVisible(true);
            idLocation.setText("");
        }
        else if(e.getSource() == FinLocation){
            try {
                int id = Integer.parseInt(idLocation.getText());
                Devis result = this.devisManager.get_devis_by_id(id);
                if (result == null){
                    JOptionPane.showMessageDialog(this, "Il n'y a pas de location pour cet ID");
                    return;
                }
                endommageCheckBox.setVisible(true);
                if(result.getFin() == null){
                    datedefinlabel.setVisible(true);
                    datedefinlocation_day.setVisible(true);
                    datedefinlocation_month.setVisible(true);
                    datedefinlocation_year.setVisible(true);
                    enregistrerButton.setVisible(true);
                }
            }
            catch (Exception exep){
                JOptionPane.showMessageDialog(this, "ID non valable");
                return;
            }

        }
        else if (e.getSource() == RechercheClient) {
            String id = IdRechercheclient.getText();
            String name = NameRechercheClient.getText();
            String surname = SurnameRechercheClient.getText();
            Optional<Integer> opid;
            Optional<String> opname, opsurname;
            if (id.isEmpty()){
                opid = Optional.empty();
            }
            else {
                opid = Optional.of(Integer.parseInt(id));
            }
            if (name.isEmpty()){
                opname = Optional.empty();
            }
            else {
                opname = Optional.of(name);
            }
            if (surname.isEmpty()){
                opsurname = Optional.empty();
            }
            else {
                opsurname = Optional.of(surname);
            }
            ArrayList<Client> clients = clientManager.find_clients(opid, opname, opsurname);

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
            try {
                for (Client client: clients){
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

            ListeRechercheClient.add(client_pane);
            IdRechercheclient.setText("");
            NameRechercheClient.setText("");
            SurnameRechercheClient.setText("");


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
        ArrayList<Voiture> voitureArrayList = new ArrayList<>();
        ArrayList<Devis> devisArrayList = new ArrayList<>();
        VoitureManager voitureManager = new VoitureManager(vp);
        DevisManager devisManager = new DevisManager(devisArrayList,dep);
        ClientManager clientManager = new ClientManager(clientsArrayList,clientp);
        JFrame jFrame = new ClientMenu(clientManager,devisManager,voitureManager,clientp,vp,carbup,cp,stp,ap);
        jFrame.setVisible(true);
    }
}
