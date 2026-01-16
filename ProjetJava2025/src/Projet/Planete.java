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

        // Vérification explicite de O2 et N2
        double o2 = compositionAtmosphere.getOrDefault("O2", 0.0);
        double n2 = compositionAtmosphere.getOrDefault("N2", 0.0);
        
        boolean atmosphereValide = (o2 > 15.0) && (n2 > 50.0);

        return conditionsPhysiques && atmosphereValide;
    }

    @Override
    public double graviteSurface() {
        return Constantes.G * getMasse() / (getRayon() * getRayon());
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
            "Planète : masse=" + getMasse() + " kg" +
            ", rayon=" + getRayon() + " m" +
            ", position=" + getEtat().getPosition() +
            ", temp=" + temperature + " K" +
            ", pression=" + pression + " Pa" +
            ", habitable=" + estHabitable()
        );
    }

    /* ===== Getters/Setters ===== */
    public Map<String, Double> getCompositionAtmosphere() {
        return compositionAtmosphere;
    }
    
  
    public void setCompositionAtmosphere(Map<String, Double> compositionAtmosphere) {
        this.compositionAtmosphere = compositionAtmosphere;
    }

    public double getPression() {
        return pression;
    }
    
    public void setPression(double pression) {
        this.pression = pression;
    }

    public double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}