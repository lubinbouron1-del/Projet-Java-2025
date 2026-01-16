package Projet;

import java.util.List;

public class Etoile extends Astre {

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
        Vecteur posAutre = autre.getEtat().getPosition();

        // Vecteur de l'astre VERS l'étoile (attraction)
        Vecteur r = new Vecteur(
            posEtoile.getX() - posAutre.getX(),
            posEtoile.getY() - posAutre.getY(),
            posEtoile.getZ() - posAutre.getZ()
        );

        double distance = r.norme();

        if (distance == 0) return new Vecteur(0, 0, 0); // Sécurité

        // Intensité F = G*m1*m2 / r²
        double intensite = Constantes.G * this.getMasse() * autre.getMasse() / (distance * distance);

        // Vecteur unitaire u = r / ||r||
        Vecteur u = r.normalize();

        // Force dirigée vers l'étoile (CORRECTION: pas de signe négatif)
        return u.multiply(intensite);
    }

    /* ===== Update ===== */
    @Override
    public void update(double dt, List<Astre> autres) {
        // Si on veut l'étoile fixe, on laisse vide
        // Pour N-corps réaliste, on pourrait calculer forces et update position
        // super.update(dt, autres); // Décommenter pour étoile mobile
    }

    /* ===== Affichage ===== */
    @Override
    public void affiche() {
        System.out.println("Étoile : masse=" + getMasse() + " kg, rayon=" + getRayon() 
                + " m, position=" + getEtat().getPosition() + ", temp=" + temperatureSurface + " K");
    }
}