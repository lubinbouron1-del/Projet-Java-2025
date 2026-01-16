package Projet;

import java.util.ArrayList;
import java.util.List;

public class SystemeStellaire {

    private List<Astre> astres;

    public SystemeStellaire() {
        astres = new ArrayList<>();
    }

    public void ajouterAstre(Astre a) {
        astres.add(a);
    }

    public void supprimerAstre(Astre a) {
        astres.remove(a);
    }

    public List<Astre> getAstres() {
        return astres;
    }

    public void update(double dt) {
        for (Astre a : astres) {
            a.update(dt, astres);
        }
    }
}s