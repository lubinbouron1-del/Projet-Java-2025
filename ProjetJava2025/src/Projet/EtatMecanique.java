package Projet;

public class EtatMecanique {
    private Vecteur position;
    private Vecteur vitesse;

    public EtatMecanique(Vecteur position, Vecteur vitesse) {
        this.position = position;
        this.vitesse = vitesse;
    }

    // Getters et setters clairs
    public Vecteur getPosition() { return position; }
    public void setPosition(Vecteur position) { this.position = position; }

    public Vecteur getVitesse() { return vitesse; }
    public void setVitesse(Vecteur vitesse) { this.vitesse = vitesse; }

    @Override
    public String toString() {
        return "Etat(position=" + position + ", vitesse=" + vitesse + ")";
    }
}