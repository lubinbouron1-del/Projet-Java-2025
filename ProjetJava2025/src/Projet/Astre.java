package Projet;

import java.util.ArrayList;
import java.util.List;

public abstract class Astre {

    /* ===== Attributs ===== */
    private double masse;
    private double rayon;

    private EtatMecanique etat;                // Position et vitesse
    private ArrayList<Vecteur> trajectoire;    // Historique pour affichage
    private ArrayList<Vecteur> forces;         // Forces appliquées
    private int nbPoints;                       // Nombre de points de trajectoire

    private List<Astre> satellites;            // Satellites orbitant autour de l'astre

    /* ===== Constructeur ===== */
    public Astre(double masse, double rayon, Vecteur positionInitiale, Vecteur vitesseInitiale) {
        this.masse = masse;
        this.rayon = rayon;
        this.etat = new EtatMecanique(positionInitiale, vitesseInitiale);
        this.trajectoire = new ArrayList<>();
        trajectoire.add(positionInitiale);
        this.forces = new ArrayList<>();
        this.satellites = new ArrayList<>();
        this.nbPoints = 0;
    }

    /* ===== Gestion des satellites ===== */
    public void ajouterSatellite(Astre sat) {
        satellites.add(sat);
    }

    public List<Astre> getSatellites() {
        return satellites;
    }

    /* ===== Trajectoire ===== */
    public ArrayList<Vecteur> getTrajectoire() {
        return trajectoire;
    }

    protected void ajouterPointTrajectoire(Vecteur position) {
        trajectoire.add(position);
    }

    /* ===== Forces ===== */
    public void ajouterForce(Vecteur f) {
        forces.add(f);
    }

    protected Vecteur sommeForces() {
        Vecteur somme = new Vecteur(0, 0, 0);
        for (Vecteur f : forces) {
            somme = somme.add(f);
        }
        return somme;
    }

    /* ===== Mise à jour physique (Euler) ===== */
    // On fournit une liste d'autres astres pour calcul des forces gravitationnelles
    public void update(double dt, List<Astre> autres) {
        // Calcul des forces gravitationnelles provenant des autres astres
        for (Astre autre : autres) {
            if (autre != this) {
                ajouterForce(autre.forceGravitationnelle(this));
            }
        }

        // Euler
        Vecteur position = etat.getPosition();
        Vecteur vitesse  = etat.getVitesse();

        Vecteur acceleration = sommeForces().multiply(1.0 / masse);
        Vecteur nouvelleVitesse = vitesse.add(acceleration.multiply(dt));
        Vecteur nouvellePosition = position.add(nouvelleVitesse.multiply(dt));

        etat.setPosition(nouvellePosition);
        etat.setVitesse(nouvelleVitesse);

        ajouterPointTrajectoire(nouvellePosition);

        nbPoints++;
        forces.clear();

        // Update des satellites
        for (Astre sat : satellites) {
            sat.update(dt, autres);
        }
    }

    /* ===== Méthodes abstraites ===== */
    public abstract void affiche();

    // À surcharger dans les sous-classes (ex: Etoile) si nécessaire
    public Vecteur forceGravitationnelle(Astre autre) {
        return new Vecteur(0, 0, 0); // Par défaut : aucune force
    }

    /* ===== Getters ===== */
    public double getMasse() { return masse; }
    public double getRayon() { return rayon; }
    public int getNbPoints() { return nbPoints; }
    public EtatMecanique getEtat() { return etat; }
}