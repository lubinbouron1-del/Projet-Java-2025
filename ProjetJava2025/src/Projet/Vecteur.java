package Projet;

public class Vecteur {

	private double x;
	private double y;
	private double z;
	private double angle;
	private double norme;



	private Point pointApplication;

	//Vecteurs avec des coords
	public Vecteur(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.norme = Math.sqrt(x * x + y * y + z * z);
		this.angle =  Math.atan2(y, x);
	}

	//Vecteur avec 2 points
	public Vecteur(Point p, Point q) {
		this.pointApplication = p;
		this.x = q.getX() - p.getX();
		this.y = q.getY() - p.getY();
		this.z = q.getZ() - p.getZ();
		this.norme = Math.sqrt(x * x + y * y + z * z);
		this.angle =  Math.atan2(y, x);
	}
	//vecteur avec angle
	public Vecteur(Point p, double norme, double angle) {
		this.pointApplication = p;
		this.angle = angle; 
		this.norme = norme;
		this.x = norme * Math.cos(angle);
		this.y = norme * Math.sin(angle);
		this.z = 0;

	}

	//Getters et Setters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}


	public double getNorme() {
		return this.norme;
	}

	/* ===== Angle (dans le plan XY) ===== */
	public double getAngle() {
		return this.angle;
	}


	public Vecteur add(Vecteur v) {
		return new Vecteur(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	public Vecteur multiply(double k) {
		return new Vecteur(k * x, k * y, k * z);
	}
}