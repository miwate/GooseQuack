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
     * Construit un coût avec son nom.
     * 
     * @param nom le nom du coût (Économique, Social, Environnemental).
     */
    Cout(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    /**
     * Retourne le nombre de types de coûts.
     * 
     * @return le nombre de types de coûts dans l'énumération (normalement 3).
     */
    public static int nbTypes() {
        return values().length;
    }
}
