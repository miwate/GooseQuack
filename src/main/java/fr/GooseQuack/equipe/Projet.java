package fr.GooseQuack.equipe;

import java.util.Objects;

public class Projet {

    private final String titre;

    private final String description;

    private final Secteur secteur;

    private int benefice;

    private final int[] couts;

    public Projet(String _titre, String _description, Secteur _secteur) {
        
        Objects.requireNonNull(_titre, "titre must not be null");
        this.titre = _titre;

        Objects.requireNonNull(_description, "description must not be null");
        this.description = _description;

        Objects.requireNonNull(_secteur, "titre must not be null");
        this.secteur = _secteur;

        this.benefice = 0;

        this.couts = new int[Cout.nbTypes()];
    }


    // Getters
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public int getBenefice() {
        return benefice;
    }

    public int getCout(Cout _type) {
        Objects.requireNonNull(_type, "type must not be null");
        return couts[_type.ordinal()];
    }


    // Setters
    public void setBenefice(int _benefice) {
        if (_benefice < 0) {
            throw new IllegalArgumentException("benefice must be positive");
        }
        this.benefice = _benefice;
    }

}