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

    /**
     * Construit un secteur
     *
     * @param nom le nom du secteur
     */
    Secteur(String nom) {
        this.nom = nom;
    }

    /**
     * Donne le nom du secteur
     *
     * @return le nom du secteur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Donne le nom en texte du secteur
     *
     * @return le nom du secteur
     */
    @Override
    public String toString() {
        return nom;
    }

}
