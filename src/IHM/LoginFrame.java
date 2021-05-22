package IHM;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LoginFrame extends JFrame implements ActionListener {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JPanel panel = new JPanel(new GridLayout(3, 1));              
	    JLabel loginlabel = new JLabel("Login");
	    JLabel mdplabel = new JLabel("Mot de passe");
	    JTextField loginTextField = new JTextField();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JPasswordField mdpField = new JPasswordField();
	    JButton loginButton = new JButton("LOGIN");
	    JButton resetButton = new JButton("RESET");
	    JCheckBox showPassword = new JCheckBox("Montrer Mot de passe");


	    public LoginFrame() {
	    	this.setTitle("Page de connexion");
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
	    }

	    public void addActionEvent() {
	        loginButton.addActionListener(this);
	        resetButton.addActionListener(this);
	        showPassword.addActionListener(this);
	    }


	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == loginButton) {
	            String loginText;
	            String mdpText;
	            loginText = loginTextField.getText();
	            mdpText = mdpField.getText();
	            if (loginText.equalsIgnoreCase("tm") && mdpText.equalsIgnoreCase("tm")) {
	            	dispose();
	            	Menu menu= new Menu();
	            	menu.changerMenu();
	            } else {
	                JOptionPane.showMessageDialog(this, "Login ou Mot de passe invalide");
	            }

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
    }
    
