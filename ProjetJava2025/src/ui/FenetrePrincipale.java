package ui;

import javax.swing.*;
import java.awt.*;
import Projet.SystemeStellaire;

public class FenetrePrincipale extends JFrame {

    public FenetrePrincipale(SystemeStellaire systeme) {

        setTitle("Star System Simulator");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        PanneauSimulation panneau = new PanneauSimulation(systeme.getAstres());
        add(panneau, BorderLayout.CENTER);

        // --- Animation ---
        Timer timer = new Timer(20, e -> {
            systeme.update(1000); // pas de temps
            panneau.repaint();
        });
        timer.start();

        setVisible(true);
    }
}