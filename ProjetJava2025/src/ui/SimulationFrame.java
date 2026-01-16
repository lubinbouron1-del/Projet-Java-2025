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

        // Boucle de simulation simple
        new Timer(50, e -> {
            systeme.update(1); // dt = 1s
            simPanel.repaint();
        }).start();
    }
}