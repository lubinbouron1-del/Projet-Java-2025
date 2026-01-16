package Interface;

import javax.swing.*;
import java.awt.event.*;
import java.beans.*;
import java.awt.*;


public class FenetrePrincipale extends JFrame implements ActionListener {
	
	JButton ajout_planete;
	JButton supp_planete;
	
	
	public FenetrePrincipale() {
		setTitle("Star System Simulator");
		setSize(1000,750);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	
		GridLayout layoutFonction = new GridLayout(2,5,10,10);
		JPanel panelCommande = new JPanel(layoutFonction);
		panelCommande.setBackground(Color.lightGray);
		panelCommande.setPreferredSize(new Dimension(1000,250));
		
		ajout_planete = new JButton("Cliquez ici pour créer une planète");
		ajout_planete.addActionListener(new ActionListener_ajoutPlanete());
	

		panelCommande.add(ajout_planete);
		
		supp_planete = new JButton("Cliquez ici pour supprimer une planète");
		supp_planete.addActionListener(new ActionListener_suppPlanete());

		panelCommande.add(supp_planete);
		//panelCommande.add(,gbc1);
		//panelCommande.add(,gbc1);
	
	
	
	
	
	
	
		this.getContentPane().add(BorderLayout.NORTH,panelCommande);
	
		this.setVisible(true);
	}
	
	class ActionListener_ajoutPlanete implements ActionListener{
		public void actionPerformed(ActionEvent e1) {
			new Fenetre_AjoutPlanete();
		} 
	}
	
	class ActionListener_suppPlanete implements ActionListener{
		public void actionPerformed(ActionEvent e2) {
			new Fenetre_SuppPlanete();
		}
	}
	
}
