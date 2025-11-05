package fr.GooseQuack.equipe;

import java.util.Objects;

public class Projet {

    private final String titre;

    private final String description;

    private final Secteur secteur;

    private int benefice;

    private final int[] couts;

    public Projet(String titre, String description, Secteur secteur) {
        
        Objects.requireNonNull(titre, "titre must not be null");
        this.titre = titre;

        Objects.requireNonNull(description, "description must not be null");
        this.description = description;

        Objects.requireNonNull(secteur, "titre must not be null");
        this.secteur = secteur;

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

    public int getCout(Cout type) {
        Objects.requireNonNull(type, "type must not be null");
        return couts[type.ordinal()];
    }


    // Setters
    public void setBenefice(int benefice) {
        if (benefice < 0) {
            throw new IllegalArgumentException("benefice must be positive");
        }
        this.benefice = benefice;
    }

}