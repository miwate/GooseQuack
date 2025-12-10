package fr.GooseQuack.sacados;

import java.util.Arrays;

/**
 * Classe d'un objet (du sac à dos).
 * 
 * @author William (miwate)
 * @version 1.0
 */
public class Objet {

    private final int utilite;
    private final int[] couts;

    /**
     * Construit un objet
     *
     * @param utilite l'utilité de l'objet (doit être positif)
     * @param couts le tableau des coûts par dimension (tous les coûts doivent être positifs)
     * @throws IllegalArgumentException si l'utilité est négative
     * @throws IllegalArgumentException si le tableau des coûts est null ou vide
     * @throws IllegalArgumentException si au moins un coût est négatif
     */
    public Objet(int utilite, int[] couts) {

        if (utilite < 0) {
            throw new IllegalArgumentException("L'utilité doit être positive");
        }

        if (couts == null || couts.length == 0) {
            throw new IllegalArgumentException("Le tableau des coûts doit être non vide");
        }

        for (int i = 0; i < couts.length; i++) {
            if (couts[i] < 0) {
                throw new IllegalArgumentException("Tous les coûts doivent être positifs");
            }
        }

        this.utilite = utilite;
        this.couts = Arrays.copyOf(couts, couts.length);
    }

    /**
     * Donne l'utilité de l'objet
     *
     * @return l'utilité
     */
    public int getUtilite() {
        return utilite;
    }

    /**
     * Donne les coûts de l'objet par dimension
     *
     * @return une copie du tableau des coûts
     */
    public int[] getCouts() {
        return Arrays.copyOf(couts, couts.length);
    }

    /**
     * Donne le coût de l'objet pour une dimension donnée
     *
     * @param dimension l'indice de la dimension (doit être entre 0 et le nombre de dimensions - 1)
     * @return le coût pour cette dimension
     * @throws IndexOutOfBoundsException si la dimension est invalide
     */
    public int getCout(int dimension) {
        if (dimension < 0 || dimension >= couts.length) {
            throw new IndexOutOfBoundsException("Dimension invalide : " + dimension + " (doit être entre 0 et " + (couts.length - 1) + ")");
        }
        return couts[dimension];
    }

    /**
     * Donne le nombre de dimensions
     *
     * @return le nombre de dimensions
     */
    public int getNbDimensions() {
        return couts.length;
    }
    
    /**
     * Vérifie l'égalité entre cet objet et un autre objet
     * Deux objets sont égaux s'ils ont la même utilité et les mêmes coûts pour toutes les dimensions
     *
     * @param o l'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Objet objet = (Objet) o;
        return utilite == objet.utilite && java.util.Arrays.equals(couts, objet.couts);
    }

    /**
     * Calcule le code de hash de l'objet
     *
     * @return le code de hachage
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(utilite, java.util.Arrays.hashCode(couts));
    }
}
