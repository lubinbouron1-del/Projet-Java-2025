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

    private JTextField masseField, rayonField, vxField, vyField, pressionField, o2Field, n2Field;

    public ConfigPanel(SystemeStellaire systeme) {
        this.systeme = systeme;
        setLayout(new BorderLayout());

        modelAstres = new DefaultListModel<>();
        listeAstres = new JList<>(modelAstres);
        refreshListeAstres();
        add(new JScrollPane(listeAstres), BorderLayout.CENTER);

        // Boutons ajouter/supprimer
        JPanel btnPanel = new JPanel(new GridLayout(3,1));
        ajouterBtn = new JButton("Ajouter Planète");
        supprimerBtn = new JButton("Supprimer");
        appliquerBtn = new JButton("Appliquer Modifs");
        btnPanel.add(ajouterBtn);
        btnPanel.add(supprimerBtn);
        btnPanel.add(appliquerBtn);
        add(btnPanel, BorderLayout.NORTH);

        // Panel propriétés
        JPanel propPanel = new JPanel(new GridLayout(7,2));
        propPanel.add(new JLabel("Masse (kg)")); masseField = new JTextField(); propPanel.add(masseField);
        propPanel.add(new JLabel("Rayon (m)")); rayonField = new JTextField(); propPanel.add(rayonField);
        propPanel.add(new JLabel("Vitesse X (m/s)")); vxField = new JTextField(); propPanel.add(vxField);
        propPanel.add(new JLabel("Vitesse Y (m/s)")); vyField = new JTextField(); propPanel.add(vyField);
        propPanel.add(new JLabel("Pression (Pa)")); pressionField = new JTextField(); propPanel.add(pressionField);
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
            modelAstres.addElement(a instanceof Planete ? "Planète" : "Étoile");
        }
    }

    private void ajouterPlanete() {
        Map<String, Double> atm = new HashMap<>();
        atm.put("O2", 21.0);
        atm.put("N2", 78.0);

        Planete p = new Planete(1e24, 6.4e6, new Vecteur(1e8,0), new Vecteur(0,1e3), atm, 101_325, 288);
        systeme.ajouterAstre(p);
        refreshListeAstres();
    }

    private void supprimerAstre() {
        int idx = listeAstres.getSelectedIndex();
        if(idx >=0) {
            systeme.getAstres().remove(idx);
            refreshListeAstres();
        }
    }

    private void afficherProprietes() {
        int idx = listeAstres.getSelectedIndex();
        if(idx >= 0) {
            Astre a = systeme.getAstres().get(idx);
            masseField.setText(String.valueOf(a.getMasse()));
            rayonField.setText(String.valueOf(a.getRayon()));
            vxField.setText(String.valueOf(a.getEtat().getVitesse().getX()));
            vyField.setText(String.valueOf(a.getEtat().getVitesse().getY()));

            if(a instanceof Planete) {
                Planete p = (Planete)a;
                pressionField.setText(String.valueOf(101_325));
                o2Field.setText(String.valueOf(p.getCompositionAtmosphere().getOrDefault("O2",0.0)));
                n2Field.setText(String.valueOf(p.getCompositionAtmosphere().getOrDefault("N2",0.0)));
            }
        }
    }

    private void appliquerModifications() {
        int idx = listeAstres.getSelectedIndex();
        if(idx >=0) {
            Astre a = systeme.getAstres().get(idx);
            try {
                double masse = Double.parseDouble(masseField.getText());
                double rayon = Double.parseDouble(rayonField.getText());
                double vx = Double.parseDouble(vxField.getText());
                double vy = Double.parseDouble(vyField.getText());

                a.setMasse(masse);
                a.setRayon(rayon);
                a.getEtat().setVitesse(new Vecteur(vx, vy));

                if(a instanceof Planete p) {
                    double o2 = Double.parseDouble(o2Field.getText());
                    double n2 = Double.parseDouble(n2Field.getText());
                    Map<String, Double> atm = new HashMap<>();
                    atm.put("O2", o2);
                    atm.put("N2", n2);
                    p.setCompositionAtmosphere(atm);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,"Valeurs invalides !");
            }
        }
    }
}