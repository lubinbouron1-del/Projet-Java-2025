package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.DAO;

public class Fenetre_AjoutPlanete  extends JFrame implements ActionListener{
	
	 JLabel zoneTexte;
	 JTextField zoneSaisie;
	 JButton boutonOK;
	
	public Fenetre_AjoutPlanete() {
		setTitle("Création de planète");
		setSize(300,300);
		this.setResizable(false);
		
		
		GridBagLayout layoutAjout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel panelAjout = new JPanel(layoutAjout);
		panelAjout.setBackground(Color.lightGray);
		panelAjout.setPreferredSize(new Dimension(300,300));
		
		zoneTexte = new JLabel("Saisir le nom de la planètes");
		zoneSaisie = new JTextField("");
		zoneSaisie.setPreferredSize(new Dimension(150,20));
		zoneSaisie.addActionListener(new ActionListener_zoneSaisie());
		boutonOK = new JButton("Ok");
		boutonOK.addActionListener(new ActionListener_Bouton());
		
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panelAjout.add(zoneTexte,gbc);
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panelAjout.add(zoneSaisie,gbc);
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panelAjout.add(boutonOK,gbc);
		
		this.getContentPane().add(BorderLayout.NORTH,panelAjout);
		
		this.setVisible(true);
		
	}
	
	
	class ActionListener_zoneSaisie implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			DAO.ajouterElement(zoneSaisie.getText(), "planetes");
			JOptionPane.showMessageDialog(null, "Planète ajouter avec succès !\n Modifiez ses paramètres (nul par défaut) grâce au bouton associé.");
		}
	}
	class ActionListener_Bouton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	 
	
}
