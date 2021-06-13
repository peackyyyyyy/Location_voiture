package IHM;

import Persistence.EmployePersistence;
import Persistence.JdbcConnexion;
import value_object.Employe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChauffeurLogin extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JPanel panel = new JPanel(new GridLayout(3, 1));
    private JLabel loginlabel = new JLabel("Login");
    private JLabel mdplabel = new JLabel("Mot de passe");
    private JTextField loginTextField = new JTextField();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPasswordField mdpField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JCheckBox showPassword = new JCheckBox("Montrer Mot de passe");
    private ArrayList<Employe> listeemploye =new ArrayList<Employe>();
    private JButton button1=new JButton("Retour");;



    public ChauffeurLogin() throws ClassNotFoundException, SQLException {
        JdbcConnexion jdbc = new JdbcConnexion();
        Statement con = jdbc.getConn();
        Connection connexion = jdbc.getConnexion();
        EmployePersistence ep = new EmployePersistence(con,connexion);
        ArrayList<Employe> listeemploye =ep.getEmployes();
        this.listeemploye=listeemploye;
        this.setTitle("Page de connexion Chauffeur");
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
        button1.setBounds(150, 0, 100, 30);
        loginlabel.setBounds(50, 150, 100, 30);
        mdplabel.setBounds(50, 220, 100, 30);
        loginTextField.setBounds(150, 150, 150, 30);
        mdpField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        panel.add(loginlabel);
        panel.add(mdplabel);
        panel.add(loginTextField);
        panel.add(mdpField);
        panel.add(showPassword);
        panel.add(loginButton);
        panel.add(resetButton);
        panel.add(button1);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        button1.addActionListener(this);
    }
    public void changerMenu(){
        this.setContentPane(this.panel);
        this.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String loginText;
            String mdpText;
            loginText = loginTextField.getText();
            mdpText = mdpField.getText();
            int c = 0;
            for (Employe employe : listeemploye) {

                if (loginText.equalsIgnoreCase(employe.getLogin()) && mdpText.equalsIgnoreCase(employe.getMdp()) && (employe.getType().equals("admin") || employe.getType().equals("chauffeur"))) {
                    dispose();
                    try {
                        Chauffeur.main(null);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    c=1;
                    break;
                } else {
                    continue;
                }
            }
            if (c==0) {
                JOptionPane.showMessageDialog(this, "Login ou Mot de passe invalide");
            }


        }
        if (e.getSource() == button1) {
            dispose();
            ChoiceUser admin=new ChoiceUser();
        }
        if (e.getSource() == resetButton) {
            loginTextField.setText("");
            mdpField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                mdpField.setEchoChar((char) 0);
            } else {
                mdpField.setEchoChar('*');
            }


        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JFrame jFrame = new AdminLogin();
        jFrame.setVisible(true);
    }
}
