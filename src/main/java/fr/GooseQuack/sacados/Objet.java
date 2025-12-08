package fr.GooseQuack.sacados;

import java.util.Arrays;

/**
 * Classe d'un objet (du sac à dos).
 * 
 * @author William (miwate)
 * @version 1.0
 */
public class Objet {
    
    /**
     * Entier naturel : utilité de l'objet.
     */
    private final int utilite;

    /**
     * Tableau d'entiers naturels : coûts de l'objet par dimension.
     * couts[i] : coût de l'objet dimension i
     */
    private final int[] couts;

    /**
     * Constructeur d'un objet.
     *
     * @param utilite l'utilité de l'objet (doit être positif)
     * @param couts le tableau des coûts de l'objet par dimension (tous les coûts doivent être positifs)
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
        // J'utilise un final, il faut éviter d'exposer les tableaux
        this.couts = Arrays.copyOf(couts, couts.length);
    }

    public int getUtilite() {
        return utilite;
    }

    public int[] getCouts() {
        return Arrays.copyOf(couts, couts.length);
    }

    /**
     * Retourne le coût de l'objet pour une dimension donnée.
     *
     * @param dimension l'indice de la dimension (doit être entre 0 et le nombre de dimensions - 1)
     * @return le coût de l'objet pour cette dimension
     * @throws IndexOutOfBoundsException si la dimension est invalide (trop petite, trop grande)
     */
    public int getCout(int dimension) {
        if (dimension < 0 || dimension >= couts.length) {
            throw new IndexOutOfBoundsException("Dimension invalide : " + dimension + " (doit être entre 0 et " + (couts.length - 1) + ")");
        }
        return couts[dimension];
    }

    public int getNbDimensions() {
        return couts.length;
    }
    
}
