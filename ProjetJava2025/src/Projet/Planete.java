package Projet;

import java.util.Map;

public class Planete extends Astre {

    private Map<String, Double> compositionAtmosphere; // % des gaz
    private double pression;                            // en Pa
    private double temperature;                         // en K
    private boolean habitable;                          // calculé via méthode

    /* ===== Constructeur ===== */
    public Planete(double masse, double rayon,
                   Vecteur positionInitiale,
                   Vecteur vitesseInitiale,
                   Map<String, Double> compositionAtmosphere,
                   double pression,
                   double temperature) {

        super(masse, rayon, positionInitiale, vitesseInitiale);
        this.compositionAtmosphere = compositionAtmosphere;
        this.pression = pression;
        this.temperature = temperature;
        this.habitable = estHabitable(); // calcul initial
    }

    /* ===== Méthode d’habitalité simplifiée ===== */
    public boolean estHabitable() {
        // Exemple simple : température entre 250K et 320K, pression > 50000 Pa
        if (temperature < 250 || temperature > 320) return false;
        if (pression < 50000 || pression > 200000) return false;
        // Atmosphère doit contenir O2 > 15% et N2 > 50%
        Double oxygene = compositionAtmosphere.getOrDefault("O2", 0.0);
        Double azote = compositionAtmosphere.getOrDefault("N2", 0.0);
        if (oxygene < 15.0 || azote < 50.0) return false;

        return true;
    }

    /* ===== Surcharge force gravitationnelle si nécessaire ===== */
    // Ici, la planète ne génère pas de forces par elle-même dans cette version
    @Override
    public Vecteur forceGravitationnelle(Astre autre) {
        return new Vecteur(0, 0, 0); // pour l’instant, force calculée par Etoile
    }

    /* ===== Update ===== */
    @Override
    public void update(double dt, java.util.List<Astre> autres) {
        super.update(dt, autres);
        habitable = estHabitable(); // recalcul si conditions changent
    }

    /* ===== Affichage ===== */
    @Override
    public void affiche() {
        System.out.println("Planète : masse=" + getMasse() + ", rayon=" + getRayon()
                + ", position=" + getEtat().getPosition()
                + ", habitable=" + habitable);
    }

    /* ===== Getters ===== */
    public Map<String, Double> getCompositionAtmosphere() { return compositionAtmosphere; }
    public double getPression() { return pression; }
    public double getTemperature() { return temperature; }
    public boolean isHabitable() { return habitable; }
}