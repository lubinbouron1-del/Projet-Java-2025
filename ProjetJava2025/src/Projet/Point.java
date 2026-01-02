package Projet;

public class Point {
    private double x;
    private double y;
    private double z;

    // Constructeur 2D (z = 0)
    public Point(double x, double y) {
        this(x, y, 0.0);
    }

    // Constructeur 3D
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters / Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
