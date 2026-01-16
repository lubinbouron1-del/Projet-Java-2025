package Projet;

import java.util.List;

public class Etoile extends Astre {

    private static final double G = 6.67430e-11; // Constante gravitationnelle
    
    private double temperatureSurface;
    /* ===== Constructeur ===== */
    public Etoile(double masse, double rayon, Vecteur positionInitiale, double temperatureSurface) {
        super(masse, rayon, positionInitiale, new Vecteur(0, 0, 0));
        this.temperatureSurface = temperatureSurface;
    }
    public double getTemperatureSurface() {
        return temperatureSurface;
    }

    /* ===== Force gravitationnelle ===== */
    @Override
    public Vecteur forceGravitationnelle(Astre autre) {
        Vecteur posEtoile = this.getEtat().getPosition();
        Vecteur posAutre  = autre.getEtat().getPosition();

        // Vecteur distance
        Vecteur r = new Vecteur(
            posAutre.getX() - posEtoile.getX(),
            posAutre.getY() - posEtoile.getY(),
            posAutre.getZ() - posEtoile.getZ()
        );

        double distance = r.norme();

        if (distance == 0) return new Vecteur(0, 0, 0); // Sécurité

        // Intensité F = G*m1*m2 / r²
        double intensite = G * this.getMasse() * autre.getMasse() / (distance * distance);

        // Vecteur unitaire u = r / ||r||
        Vecteur u = r.normalize();

        // Force dirigée vers l'étoile
        return u.multiply(-intensite);
    }

    /* ===== Update ===== */
    @Override
    public void update(double dt, List<Astre> autres) {
        // Si on veut l'étoile fixe, on laisse vide
        // Pour N-corps réaliste, on pourrait calculer forces et update position
    }

    /* ===== Affichage ===== */
    @Override
    public void affiche() {
        System.out.println("Étoile : masse=" + getMasse() + ", rayon=" + getRayon()
                + ", position=" + getEtat().getPosition());
    }
}