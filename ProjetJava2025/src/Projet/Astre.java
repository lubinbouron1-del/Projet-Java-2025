package Projet;

import java.util.ArrayList;

public abstract class Astre {

    /* ===== Attributs ===== */
    private double rayon;
    private double masse;

    private ArrayList<Vecteur> forces;
    private int nbPoints;

    // Position et vitesse de l'astre à un instant t
    private Doublet<Vecteur, Vecteur> etat;

    // Trajectoire
    private ArrayList<Double> trajectoireX;
    private ArrayList<Double> trajectoireY;

    //Constructeur
    public Astre(double masse, double rayon, Vecteur positionInitiale, Vecteur vitesseInitiale) {
                
        this.masse = masse;
        this.rayon = rayon;
        this.forces = new ArrayList<>();
        this.nbPoints = 0;

        this.etat = new Doublet<>(positionInitiale, vitesseInitiale);

        this.trajectoireX = new ArrayList<>();
        this.trajectoireY = new ArrayList<>();

        // Position initiale
        trajectoireX.add((Double) positionInitiale.getX());
        trajectoireY.add((Double) positionInitiale.getY());
    }

    //Trajectoire
    public ArrayList<Double> trajectoireX() {
        return trajectoireX;
    }

    public ArrayList<Double> trajectoireY() {
        return trajectoireY;
    }

    protected void ajouterPointTrajectoire(Vecteur position) {
        trajectoireX.add((Double) position.getX());
        trajectoireY.add((Double) position.getY());
    }

    //gestion des Forces
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

    //methode d'Euler pour approximer les positions
    public void update(double dt) {

        Vecteur position = etat.getPremier();
        Vecteur vitesse  = etat.getSecond();

        // a = F / m ( Principe fondamental de la dynamique)
        Vecteur acceleration = sommeForces().multiply(1.0 / masse);

        // v(t+dt)
        vitesse = vitesse.add(acceleration.multiply(dt));

        // x(t+dt)
        position = position.add(vitesse.multiply(dt));

        // Mise à jour de l'état
        etat = new Doublet<>(position, vitesse);

        // Sauvegarde de la trajectoire
        ajouterPointTrajectoire(position);

        nbPoints++;
        forces.clear();
    }

    //Methodes Abstraites
    public abstract void affiche();

    //getters
    public double getRayon() {
        return rayon;
    }

    public double getMasse() {
        return masse;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public Doublet<Vecteur, Vecteur> getEtat() {
        return etat;
    }

//test 2

}