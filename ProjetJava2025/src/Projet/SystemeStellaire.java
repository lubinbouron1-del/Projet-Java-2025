package Projet;

import java.util.ArrayList;
import java.util.List;

public class SystemeStellaire {

    private List<Astre> astres;

    public SystemeStellaire() {
        this.astres = new ArrayList<>();
    }

    /** Ajouter un astre (planète, étoile...) */
    public void ajouterAstre(Astre a) {
        astres.add(a);
    }

    /** Supprimer un astre */
    public void supprimerAstre(Astre a) {
        astres.remove(a);
    }

    /** Retourne la liste des astres */
    public List<Astre> getAstres() {
        return astres;
    }

    /** Mise à jour de tous les astres (Euler) */
    public void update(double dt) {
        // On met à jour chaque astre avec la liste complète
        for (Astre a : astres) {
            a.update(dt, astres);
        }
    }

    /** Affiche l’état de tous les astres */
    public void affiche() {
        for (Astre a : astres) {
            a.affiche();
        }
    }
}