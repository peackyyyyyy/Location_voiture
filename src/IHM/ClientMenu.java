package IHM;
import business.ClientManager;
import business.DevisManager;
import business.VoitureManager;
import value_object.*;
import value_object.Categorie.Luxe;
import value_object.model.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ClientMenu extends JFrame implements ActionListener{
    private final ClientManager clientManager;
    private final DevisManager devisManager;
    private final VoitureManager voitureManager;
    private JTabbedPane tabbedPane1;
    private JPanel Fenetre;
    private JTabbedPane tabbedPane2;
    private JPanel Client;
    private JPanel Voiture;
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
    private JTabbedPane tabbedPane3;
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
    private JTable ClientTable;
    private JButton ajouterUnClientButton;
    private JButton rechercherUnClientButton;
    private JButton supprimerUnClientButton;
    private DefaultTableModel model;

    public ClientMenu (ClientManager clientManager, DevisManager devisManager, VoitureManager voitureManager) {
        super();
        this.clientManager = clientManager;
        this.devisManager =  devisManager;
        this.voitureManager = voitureManager;
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

    }

    public void setLayoutManager() {
        Listedesclients.setLayout(null);
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
            this.clientManager.add_client(name, surname, email, new Adresse(rue, ville, codepostal), phone, 1);
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
        else if (e.getSource() == AjouterLocation){
            ICategorie luxe = new Luxe();
            Agence agence = new Agence("rue", "ville", "06", 1, "agence", "0657453434", "longitude", "lattitude");
            this.voitureManager.add_voiture("marque", "model", 15, luxe, false, true, Enumeration.Carburant.SP95, 1, false, agence);
            String id_voiture = idVoiturefield.getText();
            String id_client = idclientfield.getText();
            int day_debut = Integer.parseInt(String.valueOf(daylocationdebutbox.getSelectedItem()));
            int month_debut = Integer.parseInt(String.valueOf(monthlocationdebutbox.getSelectedItem()));
            int year_debut = Integer.parseInt(String.valueOf(yearlocationdebutbox.getSelectedItem()));
            Voiture voiture = this.voitureManager.get_voiture_by_id(Integer.parseInt(id_voiture));
            Client client = this.clientManager.get_client_by_id(Integer.parseInt(id_client));
            Date date_debut = new GregorianCalendar(year_debut, return_month(month_debut), day_debut).getTime();
            //#todo add id in all managers methodes
            this.devisManager.add_devi(voiture, client, date_debut, 1);
            if (checkdatefinlocation.isSelected()){
                int day_fin = Integer.parseInt(String.valueOf(daylocationfinbox.getSelectedItem()));
                int month_fin = Integer.parseInt(String.valueOf(monthlocationfinbox.getSelectedItem()));
                int year_fin = Integer.parseInt(String.valueOf(yearlocationfinbox.getSelectedItem()));
                Date date_fin = new GregorianCalendar(year_debut, return_month(month_fin), day_debut).getTime();
                this.devisManager.update_fin_devis_by_id(1, date_fin);
            }
            JOptionPane.showMessageDialog(this, "Location Effectué");
        }
        else if (e.getSource() == enregistrerButton){

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

    public static void main(String[] args) {
        ArrayList<Client> clientsArrayList = new ArrayList<>();
        ArrayList<Voiture> voitureArrayList = new ArrayList<>();
        ArrayList<Devis> devisArrayList = new ArrayList<>();
        VoitureManager voitureManager = new VoitureManager(voitureArrayList);
        DevisManager devisManager = new DevisManager(devisArrayList);
        ClientManager clientManager = new ClientManager(clientsArrayList);
        JFrame jFrame = new ClientMenu(clientManager, devisManager, voitureManager);
        jFrame.setVisible(true);
    }
}
