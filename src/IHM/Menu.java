package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	Container container = getContentPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JButton clientButton = new JButton("Client");
    JButton voitureButton = new JButton("Voiture");
    JButton devisButton = new JButton("Devis");
    JButton factureButton = new JButton("Facture");
    JLabel informationsLabel = new JLabel("Serum calcium and immunoreactive parathyroid hormone levels increase within the normal range in 80% of patients during the first four weeks of lithium carbonate administration and may rise above normal in 10% after long-term therapy. Since the lithium ion in vitro makes the parathyroid cell less sensitive to calcium, and since several lithium carbonate-treated patients with parathyroid adenomas have been described, it has been suggested that the lithium ion can stimulate parathyroid growth. The data are inconclusive, however, since the adenomas could be sporadic and there has been no direct proof of increased parathyroid mass or biologic activity. Based on the available studies, we have formulated a reasonable scheme for monitoring calcium metabolism during lithium carbonate treatment. Proper treatment of hypercalcemic lithium carbonate-treated patients remains uncertain, but we have outlined some tentative management guidelines.");
    ImageIcon icone = new ImageIcon("background.png");
    JLabel image = new JLabel(icone);
    
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
    	clientButton.setBounds(50, 150, 100, 25);
    	voitureButton.setBounds(250, 150, 100, 25);
    	devisButton.setBounds(450, 150, 100, 25);
    	factureButton.setBounds(650, 150, 100, 25);
    	informationsLabel.setBounds(50, 200, 650, 50);
    }

    public void addComponentsToContainer() {
        container.add(clientButton);
        container.add(voitureButton);
        container.add(devisButton);
        container.add(factureButton);
        container.add(informationsLabel);
        container.add(image);
    }
    public void informationsEmploy() {
    	informationsLabel.setText("infos employé");
    	informationsLabel.setText("");
    	
    }
    public void changerMenu(){
        this.setContentPane(this.container);
        this.revalidate();
    }

}
