package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	Container container = getContentPane();
    JLabel userLabel = new JLabel("Menu");
    JLabel passwordLabel = new JLabel("Mot de passe");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("page 2");
    JButton resetButton = new JButton("page 2");
    JCheckBox showPassword = new JCheckBox("Montrer Mot de passe");
    
    public Menu() {
    	this.setTitle("Menu");
    	this.setVisible(true);
    	this.setBounds((int) (screenSize.width/5),10,screenSize.width/2,screenSize.height);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setLayoutManager();
    	this.setLocationAndSize();
    	this.addComponentsToContainer();

    }

	public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }
    public void changerMenu(){
        this.setContentPane(this.container);
        this.revalidate();
    }

}
