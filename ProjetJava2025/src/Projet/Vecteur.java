package Projet;

public class Vecteur {
    private double angle;
    private double norme;
    private Point pointApplication;

    public Vecteur(Point p, double norme, double angle) {
        this.pointApplication = p;
        this.angle = angle;
        this.norme = norme;
    }

    public Vecteur(Point p, Point q) {
        this.pointApplication = p;

        double dx = q.getX() - p.getX();
        double dy = q.getY() - p.getY();
        double dz = q.getZ() - p.getZ();

        // Norme du vecteur
        this.norme = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Angle avec l'horizontale (axe x)
        this.angle = Math.atan2(dy, dx);
    }
}