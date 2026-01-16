package app;

import Projet.*;
import ui.SimulationFrame;
// import dao.DAO; // ← Commentez cette ligne
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SystemeStellaire systeme = new SystemeStellaire();

        // Charger l'étoile (Soleil)
        Etoile soleil = new Etoile(1.989e30, 6.9634e8, new Vecteur(0, 0), 5778);
        systeme.ajouterAstre(soleil);
        System.out.println("Étoile ajoutée: " + soleil.getEtat().getPosition());

        // DÉSACTIVER LA DB - Créer directement la Terre
        System.out.println("Création Terre sans DB");
        
        java.util.Map<String, Double> atmTerre = new java.util.HashMap<>();
        atmTerre.put("O2", 21.0);
        atmTerre.put("N2", 78.0);
        
        Planete terre = new Planete(
            5.972e24,                    // masse (kg)
            6.371e6,                     // rayon (m)
            new Vecteur(1.496e11, 0),    // position: 150 millions de km
            new Vecteur(0, 29780),       // vitesse orbitale
            atmTerre,
            101_325,                     // pression (Pa)
            288                          // température (K)
        );
        systeme.ajouterAstre(terre);
        System.out.println("Terre créée: " + terre.getEtat().getPosition());
        
        // Afficher le nombre d'astres
        System.out.println("Nombre d'astres: " + systeme.getAstres().size());

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> new SimulationFrame(systeme));
    }
}