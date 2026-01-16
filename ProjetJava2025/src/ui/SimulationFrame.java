package ui;
import Projet.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class SimulationFrame extends JFrame {
    
    private static final double DT = 3600;        // 1 heure par pas
    private static final int STEPS_PER_FRAME = 24; // 24 heures = 1 jour par frame

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

        // 30 FPS, chaque frame = 1 jour de simulation
        new Timer(33, e -> {
            for (int i = 0; i < STEPS_PER_FRAME; i++) {
                systeme.update(DT);
            }
            simPanel.repaint();
        }).start();
    }
}
