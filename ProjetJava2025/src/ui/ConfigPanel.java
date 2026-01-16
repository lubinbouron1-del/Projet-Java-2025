package ui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import Projet.*;

public class ConfigPanel extends JPanel {

    private SystemeStellaire systeme;
    private JList<String> listeAstres;
    private DefaultListModel<String> modelAstres;

    private JButton ajouterBtn;
    private JButton supprimerBtn;
    private JButton appliquerBtn;

    private JTextField masseField, rayonField, posXField, posYField, vxField, vyField, pressionField, tempField, o2Field, n2Field;

    public ConfigPanel(SystemeStellaire systeme) {
        this.systeme = systeme;
        setLayout(new BorderLayout());

        modelAstres = new DefaultListModel<>();
        listeAstres = new JList<>(modelAstres);
        refreshListeAstres();
        add(new JScrollPane(listeAstres), BorderLayout.CENTER);

        // Boutons ajouter/supprimer
        JPanel btnPanel = new JPanel(new GridLayout(3, 1));
        ajouterBtn = new JButton("Ajouter Planète");
        supprimerBtn = new JButton("Supprimer");
        appliquerBtn = new JButton("Appliquer Modifs");
        btnPanel.add(ajouterBtn);
        btnPanel.add(supprimerBtn);
        btnPanel.add(appliquerBtn);
        add(btnPanel, BorderLayout.NORTH);

        // Panel propriétés
        JPanel propPanel = new JPanel(new GridLayout(10, 2));
        propPanel.add(new JLabel("Masse (kg)")); masseField = new JTextField(); propPanel.add(masseField);
        propPanel.add(new JLabel("Rayon (m)")); rayonField = new JTextField(); propPanel.add(rayonField);
        propPanel.add(new JLabel("Position X (m)")); posXField = new JTextField(); propPanel.add(posXField);
        propPanel.add(new JLabel("Position Y (m)")); posYField = new JTextField(); propPanel.add(posYField);
        propPanel.add(new JLabel("Vitesse X (m/s)")); vxField = new JTextField(); propPanel.add(vxField);
        propPanel.add(new JLabel("Vitesse Y (m/s)")); vyField = new JTextField(); propPanel.add(vyField);
        propPanel.add(new JLabel("Pression (Pa)")); pressionField = new JTextField(); propPanel.add(pressionField);
        propPanel.add(new JLabel("Température (K)")); tempField = new JTextField(); propPanel.add(tempField);
        propPanel.add(new JLabel("O2 (%)")); o2Field = new JTextField(); propPanel.add(o2Field);
        propPanel.add(new JLabel("N2 (%)")); n2Field = new JTextField(); propPanel.add(n2Field);

        add(propPanel, BorderLayout.SOUTH);

        // Listeners
        ajouterBtn.addActionListener(e -> ajouterPlanete());
        supprimerBtn.addActionListener(e -> supprimerAstre());
        appliquerBtn.addActionListener(e -> appliquerModifications());
        listeAstres.addListSelectionListener(e -> afficherProprietes());
    }

    private void refreshListeAstres() {
        modelAstres.clear();
        for (Astre a : systeme.getAstres()) {
            if (a instanceof Planete) {
                Planete p = (Planete) a;
                modelAstres.addElement("Planète (hab: " + p.estHabitable() + ")");
            } else {
                modelAstres.addElement("Étoile");
            }
        }
    }

    private void ajouterPlanete() {
        // Trouver l'étoile dans le système
        Etoile etoile = null;
        for (Astre a : systeme.getAstres()) {
            if (a instanceof Etoile) {
                etoile = (Etoile) a;
                break;
            }
        }
        
        if (etoile == null) {
            JOptionPane.showMessageDialog(this, "Aucune étoile dans le système !");
            return;
        }
        
        // Position aléatoire autour de l'étoile
        double distance = 1e11 + Math.random() * 2e11;
        double angle = Math.random() * 2 * Math.PI;
        
        double posX = distance * Math.cos(angle);
        double posY = distance * Math.sin(angle);
        
        // CALCUL DE LA VITESSE ORBITALE CIRCULAIRE
        double G = 6.67430e-11;
        double vitesseOrbitale = Math.sqrt(G * etoile.getMasse() / distance);
        
        // Vitesse perpendiculaire à la position
        double vx = -vitesseOrbitale * Math.sin(angle);
        double vy = vitesseOrbitale * Math.cos(angle);
        
        // Atmosphère par défaut
        Map<String, Double> atm = new HashMap<>();
        atm.put("O2", 21.0);
        atm.put("N2", 78.0);

        Planete p = new Planete(
            5e24,
            6.4e6,
            new Vecteur(posX, posY),
            new Vecteur(vx, vy),
            atm, 
            101_325, 
            288
        );
        
        systeme.ajouterAstre(p);
        refreshListeAstres();
        
        System.out.println("Planète ajoutée: pos=(" + posX + ", " + posY + "), vitesse=(" + vx + ", " + vy + ")");
    }

    private void supprimerAstre() {
        int idx = listeAstres.getSelectedIndex();
        if (idx >= 0) {
            systeme.getAstres().remove(idx);
            refreshListeAstres();
        }
    }

    private void afficherProprietes() {
        int idx = listeAstres.getSelectedIndex();
        if (idx >= 0) {
            Astre a = systeme.getAstres().get(idx);
            masseField.setText(String.valueOf(a.getMasse()));
            rayonField.setText(String.valueOf(a.getRayon()));
            posXField.setText(String.valueOf(a.getEtat().getPosition().getX()));
            posYField.setText(String.valueOf(a.getEtat().getPosition().getY()));
            vxField.setText(String.valueOf(a.getEtat().getVitesse().getX()));
            vyField.setText(String.valueOf(a.getEtat().getVitesse().getY()));

            if (a instanceof Planete) {
                Planete p = (Planete) a;
                pressionField.setText(String.valueOf(p.getPression()));
                tempField.setText(String.valueOf(p.getTemperature()));
                o2Field.setText(String.valueOf(p.getCompositionAtmosphere().getOrDefault("O2", 0.0)));
                n2Field.setText(String.valueOf(p.getCompositionAtmosphere().getOrDefault("N2", 0.0)));
            } else {
                pressionField.setText("");
                tempField.setText("");
                o2Field.setText("");
                n2Field.setText("");
            }
        }
    }

    private void appliquerModifications() {
        int idx = listeAstres.getSelectedIndex();
        if (idx >= 0) {
            Astre a = systeme.getAstres().get(idx);
            try {
                double masse = Double.parseDouble(masseField.getText());
                double rayon = Double.parseDouble(rayonField.getText());
                double posX = Double.parseDouble(posXField.getText());
                double posY = Double.parseDouble(posYField.getText());
                double vx = Double.parseDouble(vxField.getText());
                double vy = Double.parseDouble(vyField.getText());

                a.setMasse(masse);
                a.setRayon(rayon);
                a.getEtat().setPosition(new Vecteur(posX, posY));
                a.getEtat().setVitesse(new Vecteur(vx, vy));

                if (a instanceof Planete) {
                    Planete p = (Planete) a;
                    double pression = Double.parseDouble(pressionField.getText());
                    double temp = Double.parseDouble(tempField.getText());
                    double o2 = Double.parseDouble(o2Field.getText());
                    double n2 = Double.parseDouble(n2Field.getText());
                    
                    Map<String, Double> atm = new HashMap<>();
                    atm.put("O2", o2);
                    atm.put("N2", n2);
                    
                    p.setPression(pression);
                    p.setTemperature(temp);
                    p.setCompositionAtmosphere(atm);
                }
                
                JOptionPane.showMessageDialog(this, "Modifications appliquées !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valeurs invalides !");
            }
        }
    }
}