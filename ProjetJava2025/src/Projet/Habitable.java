package Projet;

public interface Habitable {

    // Indique si l'objet est habitable ou non
    boolean estHabitable();

    // Gravité à la surface (utile pour habitabilité)
    double graviteSurface();

    // Température moyenne en surface
    double temperatureSurface();
}