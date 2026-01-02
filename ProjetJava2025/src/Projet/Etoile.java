package Projet;

public class Etoile extends Astre {

    // Constante gravitationnelle (à adapter à ton système d’unités)
    private static final double G = 6.67430e-11;

    /**
     * Constructeur d'une étoile (astre attracteur fixe)
     */
    public Etoile(double masse, double rayon, Vecteur position) {
        // vitesse initiale nulle
        super(masse, rayon, position, new Vecteur(0, 0, 0));
    }

    /**
     * Calcule la force gravitationnelle exercée par l'étoile sur un autre astre
     * selon la loi de Newton
     */
    public Vecteur forceAttraction(Astre autre) {

        Vecteur posEtoile = this.getEtat().getPremier();
        Vecteur posAutre  = autre.getEtat().getPremier();

        // Composantes du vecteur distance r = etoile -> autre
        double dx = posAutre.getX() - posEtoile.getX();
        double dy = posAutre.getY() - posEtoile.getY();
        double dz = posAutre.getZ() - posEtoile.getZ();

        Vecteur r = new Vecteur(dx, dy, dz);
        double distance = r.getNorme();

        // Sécurité numérique (évite division par zéro)
        if (distance == 0) {
            return new Vecteur(0, 0, 0);
        }

        // Intensité de la force gravitationnelle
        double intensite = G * this.getMasse() * autre.getMasse()
                         / (distance * distance);

        // Vecteur unitaire u = r / ||r||
        Vecteur u = r.multiply(1.0 / distance);

        // Force exercée sur l'astre "autre" (dirigée vers l'étoile)
        return u.multiply(-intensite);
    }

    /**
     * L'étoile est supposée fixe : son mouvement est négligé
     */
    @Override
    public void update(double dt) {
        // volontairement vide
    }

    @Override
    public void affiche() {
        System.out.println(
            "Étoile : masse = " + getMasse()
            + ", position = (" 
            + getEtat().getPremier().getX() + ", "
            + getEtat().getPremier().getY() + ", "
            + getEtat().getPremier().getZ() + ")"
        );
    }
}