package app;

import Projet.*;
import ui.SimulationFrame;
import dao.DAO;
import java.util.Map;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {

        SystemeStellaire systeme = new SystemeStellaire();

        // Charger l'étoile
        Etoile soleil = new Etoile(1.989e30, 696_340_000, new Vecteur(0,0),1500);
        systeme.ajouterAstre(soleil);

        // Charger une planète depuis le DAO
        Planete terre = DAO.loadPlanete("Terre");
        if (terre != null) systeme.ajouterAstre(terre);

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> new SimulationFrame(systeme));
    }
}