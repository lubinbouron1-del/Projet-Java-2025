package Projet;

public class Vecteur {
    private double x;
    private double y;
    private double z;

    // ----- Constructeurs -----
    // Coordonnées 3D
    public Vecteur(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Coordonnées 2D (z=0)
    public Vecteur(double x, double y) {
        this(x, y, 0.0);
    }

    // ----- Méthode factory : norme + angle dans le plan XY -----
    public static Vecteur fromNormeAngle(double norme, double angle) {
        double x = norme * Math.cos(angle);
        double y = norme * Math.sin(angle);
        return new Vecteur(x, y, 0.0);
    }

    // ----- Getters -----
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    // ----- Méthodes physiques -----
    public double norme() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double angleXY() {
        return Math.atan2(y, x);
    }

    public Vecteur normalize() {
        double n = norme();
        if (n == 0) return new Vecteur(0, 0, 0);
        return this.multiply(1.0 / n);
    }

    public double dot(Vecteur v) {
        return x*v.x + y*v.y + z*v.z;
    }

    public Vecteur cross(Vecteur v) {
        return new Vecteur(
            y*v.z - z*v.y,
            z*v.x - x*v.z,
            x*v.y - y*v.x
        );
    }

    public double distance(Vecteur v) {
        return Math.sqrt(
            (x - v.x)*(x - v.x) +
            (y - v.y)*(y - v.y) +
            (z - v.z)*(z - v.z)
        );
    }

    // ----- Opérations immuables -----
    public Vecteur add(Vecteur v) {
        return new Vecteur(x + v.x, y + v.y, z + v.z);
    }

    public Vecteur multiply(double k) {
        return new Vecteur(x * k, y * k, z * k);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}