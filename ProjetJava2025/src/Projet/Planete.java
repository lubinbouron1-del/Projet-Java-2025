package Projet;

public class Planete extends Astre {

    public Planete(double masse, double rayon,
                   Vecteur positionInitiale,
                   Vecteur vitesseInitiale) {

        super(masse, rayon, positionInitiale, vitesseInitiale);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }

    @Override
    public void affiche() {
        System.out.println(
            "Planète : masse=" + getMasse()
            + ", position=("
            + getEtat().getPremier().getX() + ", "
            + getEtat().getPremier().getY() + ", "
            + getEtat().getPremier().getZ() + ")"
        );
    }
}