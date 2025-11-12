package fr.GooseQuack.equipe;

/**
 * Enumération des secteurs d'activité.
 * 
 * @author William (miwate)
 * @version 1.0
 */
public enum Secteur {
    SPORT("Sport"),
    SANTE("Santé"),
    CULTURE("Culture"),
    EDUCATION("Éducation"),
    ATTRACTIVITE_ECONOMIQUE("Attractivité Économique");

    private final String nom;

    Secteur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

}
