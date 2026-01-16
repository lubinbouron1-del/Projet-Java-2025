package app;

import Projet.*;
import ui.SimulationFrame;
import dao.DAO;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SystemeStellaire systeme = new SystemeStellaire();

        // Charger l'étoile (Soleil)
        Etoile soleil = new Etoile(1.989e30, 6.9634e8, new Vecteur(0, 0), 5778);
        systeme.ajouterAstre(soleil);
        System.out.println("Étoile ajoutée: " + soleil.getEtat().getPosition());

        // Charger une planète depuis le DAO
        Planete terre = DAO.loadPlanete("Terre");
        if (terre != null) {
            systeme.ajouterAstre(terre);
            System.out.println("Terre chargée depuis DB: " + terre.getEtat().getPosition());
        } else {
            // Si pas de DB, créer une Terre par défaut
            System.out.println("DB non disponible, création Terre par défaut");
            
            java.util.Map<String, Double> atmTerre = new java.util.HashMap<>();
            atmTerre.put("O2", 21.0);
            atmTerre.put("N2", 78.0);
            
            Planete terreDefaut = new Planete(
                5.972e24,                    // masse (kg)
                6.371e6,                     // rayon (m)
                new Vecteur(1.496e11, 0),    // position: 150 millions de km
                new Vecteur(0, 29780),       // vitesse orbitale
                atmTerre,
                101_325,                     // pression (Pa)
                288                          // température (K)
            );
            systeme.ajouterAstre(terreDefaut);
            System.out.println("Terre créée: " + terreDefaut.getEtat().getPosition());
        }
        
        // Afficher le nombre d'astres
        System.out.println("Nombre d'astres dans le système: " + systeme.getAstres().size());

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> new SimulationFrame(systeme));
    }
}