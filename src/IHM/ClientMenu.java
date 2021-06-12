package IHM;
import business.ClientManager;
import value_object.Adresse;
import value_object.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JCheckBox checkBox2;
    private JButton ajouterButton;
    private JTextField textField3;
    private JCheckBox endommagementCheckBox;
    private JTable ClientTable;
    private JButton ajouterUnClientButton;
    private JButton rechercherUnClientButton;
    private JButton supprimerUnClientButton;
    private DefaultTableModel model;

    public ClientMenu (ClientManager clientManager) {
        super();
        this.clientManager = clientManager;
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
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Ajouterclient) {
            System.out.println("okkkkkkkkkk");
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
        ClientManager clientManager = new ClientManager(clientsArrayList);
        JFrame jFrame = new ClientMenu(clientManager);
        jFrame.setVisible(true);
    }
}
