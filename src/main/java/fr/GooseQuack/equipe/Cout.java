package fr.GooseQuack.equipe;

/**
 * Classe enum des types de coûts d'un projet municipal.
 *
 * @author William (miwate)
 * @version 1.0
 */
public enum Cout {
    ECONOMIQUE("Économique"),
    SOCIAL("Social"),
    ENVIRONNEMENTAL("Environnemental");

    private final String nom;

    /**
     * Construit un type de coût
     *
     * @param nom le nom du type de coût
     */
    Cout(String nom) {
        this.nom = nom;
    }

    /**
     * Donne le nom du type de coût
     *
     * @return le nom du type de coût
     */
    public String getNom() {
        return nom;
    }

    /**
     * Donne le nom en texte du type de coût
     *
     * @return le nom du type de coût
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Donne le nombre de types de coûts
     *
     * @return le nombre de types de coûts dans l'énumération
     */
    public static int nbTypes() {
        return values().length;
    }
}
