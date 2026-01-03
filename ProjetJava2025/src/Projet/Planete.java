package Projet;

import java.util.Map;

public class Planete extends Astre implements Habitable {

    private Map<String, Double> compositionAtmosphere; // % des gaz
    private double pression;    // Pa
    private double temperature; // K

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
    }

    /* ===== Habitabilité ===== */
    @Override
    public boolean estHabitable() {

        boolean conditionsPhysiques =
                temperature >= 250 && temperature <= 320 &&
                pression >= 50_000 && pression <= 200_000;

        boolean atmosphereValide =
                compositionAtmosphere.entrySet().stream()
                        .filter(e -> e.getKey().equals("O2") || e.getKey().equals("N2"))
                        .allMatch(e ->
                                (e.getKey().equals("O2") && e.getValue() > 15.0) ||
                                (e.getKey().equals("N2") && e.getValue() > 50.0)
                        );

        return conditionsPhysiques && atmosphereValide;
    }

    @Override
    public double graviteSurface() {
        final double G = 6.67430e-11;
        return G * getMasse() / (getRayon() * getRayon());
    }

    @Override
    public double temperatureSurface() {
        return temperature;
    }

    /* ===== Update ===== */
    @Override
    public void update(double dt, java.util.List<Astre> autres) {
        super.update(dt, autres);
    }

    /* ===== Affichage ===== */
    @Override
    public void affiche() {
        System.out.println(
            "Planète : masse=" + getMasse() +
            ", rayon=" + getRayon() +
            ", position=" + getEtat().getPosition() +
            ", habitable=" + estHabitable()
        );
    }

    /* ===== Getters ===== */
    public Map<String, Double> getCompositionAtmosphere() {
        return compositionAtmosphere;
    }

    public double getPression() {
        return pression;
    }

    public double getTemperature() {
        return temperature;
    }
}
