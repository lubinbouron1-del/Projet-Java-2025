package ui;
import Projet.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class SimulationFrame extends JFrame {

    public SimulationFrame(SystemeStellaire systeme) {
        setTitle("Simulation Système Stellaire");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        SimulationPanel simPanel = new SimulationPanel(systeme);
        ConfigPanel configPanel = new ConfigPanel(systeme);

        add(simPanel, BorderLayout.CENTER);
        add(configPanel, BorderLayout.EAST);

        setVisible(true);

        // CORRECTION: Pas de temps beaucoup plus petit + timer plus rapide
        new Timer(16, e -> {  // 16ms = ~60 FPS
            // Faire plusieurs petits pas au lieu d'un gros
            for (int i = 0; i < 100; i++) {
                systeme.update(100); // 100 secondes par pas (au lieu de 0.01)
            }
            simPanel.repaint();
        }).start();
    }
}