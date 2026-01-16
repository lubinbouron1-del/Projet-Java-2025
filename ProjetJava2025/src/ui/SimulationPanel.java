package ui;
import javax.swing.*;
import Projet.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel {

    private SystemeStellaire systeme;

    public SimulationPanel(SystemeStellaire systeme) {
        this.systeme = systeme;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Astre> astres = systeme.getAstres();

        for (Astre a : astres) {
            Vecteur pos = a.getEtat().getPosition();

            int x = (int) (getWidth()/2 + pos.getX()/1e8);
            int y = (int) (getHeight()/2 + pos.getY()/1e8);
            int size = (int) Math.max(2, a.getRayon()/1e6);

            if (a instanceof Etoile) g.setColor(Color.YELLOW);
            else if (a instanceof Planete) {
                Planete p = (Planete) a;
                g.setColor(p.estHabitable() ? Color.GREEN : Color.BLUE);
            }
            else g.setColor(Color.GRAY);

            g.fillOval(x - size/2, y - size/2, size, size);

            // Dessiner trajectoire
            g.setColor(Color.WHITE);
            List<Vecteur> traj = a.getTrajectoire();
            for (int i = 1; i < traj.size(); i++) {
                Vecteur p1 = traj.get(i-1);
                Vecteur p2 = traj.get(i);
                g.drawLine(
                    (int)(getWidth()/2 + p1.getX()/1e8),
                    (int)(getHeight()/2 + p1.getY()/1e8),
                    (int)(getWidth()/2 + p2.getX()/1e8),
                    (int)(getHeight()/2 + p2.getY()/1e8)
                );
            }
        }
    }
}