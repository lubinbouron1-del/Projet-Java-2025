package Projet;

import java.util.Map;

public class Planete extends Astre implements Habitable {

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
    @Override
    public boolean estHabitable() {
        if (temperature < 250 || temperature > 320) return false;
        if (pression < 50000 || pression > 200000) return false;

        double o2 = compositionAtmosphere.getOrDefault("O2", 0.0);
        double n2 = compositionAtmosphere.getOrDefault("N2", 0.0);

        return o2 > 15.0 && n2 > 50.0;
    }

    @Override
    public double graviteSurface() {
        // g = G*M / R²
        final double G = 6.67430e-11;
        return G * getMasse() / (getRayon() * getRayon());
    }

    @Override
    public double temperatureSurface() {
        return temperature;
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