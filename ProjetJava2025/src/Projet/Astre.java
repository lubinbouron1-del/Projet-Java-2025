package Projet;
import java.util.ArrayList;
import java.util.List;

public abstract class Astre {
    /* ===== Attributs ===== */
    private double masse;
    private double rayon;
    private EtatMecanique etat;
    private ArrayList<Vecteur> trajectoire;
    private ArrayList<Vecteur> forces;
    private List<Astre> satellites;
    private static final int MAX_TRAJECTOIRE = 5000; // Limite mémoire
    
    /* ===== Constructeur ===== */
    public Astre(double masse, double rayon, Vecteur positionInitiale, Vecteur vitesseInitiale) {
        this.masse = masse;
        this.rayon = rayon;
        this.etat = new EtatMecanique(positionInitiale, vitesseInitiale);
        this.trajectoire = new ArrayList<>();
        trajectoire.add(positionInitiale);
        this.forces = new ArrayList<>();
        this.satellites = new ArrayList<>();
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
        // Limiter la taille pour éviter OutOfMemoryError
        if (trajectoire.size() > MAX_TRAJECTOIRE) {
            trajectoire.remove(0);
        }
    }
    
    /* ===== Forces ===== */
    public void ajouterForce(Vecteur f) {
        forces.add(f);
    }
    
    protected Vecteur sommeForces() {
        return forces.stream()
                     .reduce(new Vecteur(0, 0, 0),
                             (a, b) -> a.add(b));
    }
    
    /* ===== Mise à jour physique (Euler) ===== */
    public void update(double dt, List<Astre> autres) {
        // Calcul des forces gravitationnelles provenant des autres astres
        autres.stream()
            .filter(a -> a != this)
            .map(a -> a.forceGravitationnelle(this))
            .forEach(this::ajouterForce);
        
        // Euler
        Vecteur position = etat.getPosition();
        Vecteur vitesse = etat.getVitesse();
        Vecteur acceleration = sommeForces().multiply(1.0 / masse);
        Vecteur nouvelleVitesse = vitesse.add(acceleration.multiply(dt));
        Vecteur nouvellePosition = position.add(nouvelleVitesse.multiply(dt));
        
        etat.setPosition(nouvellePosition);
        etat.setVitesse(nouvelleVitesse);
        ajouterPointTrajectoire(nouvellePosition);
        
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
    
    /* ===== Getters/Setters ===== */
    public double getMasse() { return masse; }
    public double getRayon() { return rayon; }
    public EtatMecanique getEtat() { return etat; }
    
    public void setMasse(double masse) {
        this.masse = masse;
    }
    
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(masse=" + masse + ", rayon=" + rayon + ", pos=" + etat.getPosition() + ")";
    }
}