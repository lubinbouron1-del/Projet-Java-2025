package ui;
import javax.swing.*;
import Projet.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel {

    private SystemeStellaire systeme;
    private double echelle = 1e9;

    public SimulationPanel(SystemeStellaire systeme) {
        this.systeme = systeme;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Antialiasing pour un meilleur rendu
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        List<Astre> astres = systeme.getAstres();

        for (Astre a : astres) {
            Vecteur pos = a.getEtat().getPosition();

            // Conversion position (centre de l'écran = origine)
            int x = (int) (getWidth()/2 + pos.getX()/echelle);
            int y = (int) (getHeight()/2 + pos.getY()/echelle);

            if (a instanceof Etoile) {
                // ÉTOILE : Croix jaune
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(3));
                
                int tailleX = 20;
                g2d.drawLine(x - tailleX, y, x + tailleX, y);
                g2d.drawLine(x, y - tailleX, x, y + tailleX);
                g2d.fillOval(x - 5, y - 5, 10, 10);
                
            } else if (a instanceof Planete) {
                Planete p = (Planete) a;
                
                // TRAJECTOIRE
                g2d.setStroke(new BasicStroke(2));
                if (p.estHabitable()) {
                    g2d.setColor(new Color(0, 255, 0, 150));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 100));
                }
                
                List<Vecteur> traj = a.getTrajectoire();
                for (int i = 1; i < traj.size(); i++) {
                    Vecteur p1 = traj.get(i-1);
                    Vecteur p2 = traj.get(i);
                    
                    int x1 = (int)(getWidth()/2 + p1.getX()/echelle);
                    int y1 = (int)(getHeight()/2 + p1.getY()/echelle);
                    int x2 = (int)(getWidth()/2 + p2.getX()/echelle);
                    int y2 = (int)(getHeight()/2 + p2.getY()/echelle);
                    
                    g2d.drawLine(x1, y1, x2, y2);
                }
                
                // PLANÈTE
                if (p.estHabitable()) {
                    g2d.setColor(Color.GREEN);
                } else {
                    g2d.setColor(Color.CYAN);
                }
                
                g2d.fillOval(x - 6, y - 6, 12, 12);
                
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(x - 6, y - 6, 12, 12);
            }
        }
        
        // Informations
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
        int yOffset = 20;
        int nbPlanetes = 0;
        for (Astre a : astres) {
            if (a instanceof Planete) nbPlanetes++;
        }
        g2d.drawString("⭐ Étoiles: 1 | 🪐 Planètes: " + nbPlanetes, 10, yOffset);
        
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Échelle: 1 px = " + String.format("%.2e", echelle) + " m", 10, getHeight() - 20);
    }
}