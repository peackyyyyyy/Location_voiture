package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenu extends JFrame{
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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField7;
    private JButton addButton;
    private JButton ajouterUnClientButton;
    private JButton rechercherUnClientButton;
    private JButton supprimerUnClientButton;

    public ClientMenu() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Fenetre);
        this.pack();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new ClientMenu();
        jFrame.setVisible(true);
    }
}
