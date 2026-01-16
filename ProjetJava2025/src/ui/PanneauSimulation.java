package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Projet.Astre;
import Projet.Vecteur;

public class PanneauSimulation extends JPanel {

    private List<Astre> astres;
    private double echelle = 1e-9; // conversion m → pixels

    public PanneauSimulation(List<Astre> astres) {
        this.astres = astres;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        for (Astre a : astres) {

            // --- Trajectoire ---
            g2.setColor(Color.DARK_GRAY);
            List<Vecteur> traj = a.getTrajectoire();
            for (int i = 1; i < traj.size(); i++) {
                int x1 = cx + (int)(traj.get(i - 1).getX() * echelle);
                int y1 = cy - (int)(traj.get(i - 1).getY() * echelle);
                int x2 = cx + (int)(traj.get(i).getX() * echelle);
                int y2 = cy - (int)(traj.get(i).getY() * echelle);
                g2.drawLine(x1, y1, x2, y2);
            }

            // --- Astre ---
            Vecteur pos = a.getEtat().getPosition();
            int x = cx + (int)(pos.getX() * echelle);
            int y = cy - (int)(pos.getY() * echelle);

            int r = Math.max(4, (int)Math.log10(a.getRayon()));
            g2.setColor(Color.WHITE);
            g2.fillOval(x - r, y - r, 2*r, 2*r);
        }
    }
}