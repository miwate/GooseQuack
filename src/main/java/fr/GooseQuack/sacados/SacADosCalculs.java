package fr.GooseQuack.sacados;

import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitaire implémentant la somme et le maximum d'un tableau.
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */

public class SacADosCalculs {

    /**
     * Donne la somme des coûts
     *
     * @param couts le tableau des coûts
     * @return la somme des coûts, ou 0 si le tableau est null ou vide
     */
    public static int sommeCouts(int[] couts) {
        if (couts == null || couts.length == 0) {
            return 0;
        }
        return Arrays.stream(couts).sum();
    }

    /**
     * Donne le maximum des coûts
     *
     * @param couts le tableau des coûts
     * @return le coût maximum, ou 1 si le tableau est null ou vide
     */
    public static int maxCouts(int[] couts) {
        if (couts == null || couts.length == 0) {
            return 1;
        }
        return Arrays.stream(couts).max().orElse(1);
    }

    public static int sommeUtilite(List<Objet> objets) {
        if (objets == null || objets.isEmpty()) {
            return 0;
        }

        int somme = 0;
        for (Objet o : objets) {
            somme += o.getUtilite();
        }
        return somme;
    }
}
