package fr.GooseQuack.sacados;

import java.util.Arrays;

/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */

public class SacADosCalculs {
    // Pour les couts avec garde-fous
    public static int sommeCouts(int[] couts) {
        if (couts == null || couts.length == 0) {
            return 0;
        }
        return Arrays.stream(couts).sum();
    }

    // Pareillement
    public static int maxCouts(int[] couts) {
        if (couts == null || couts.length == 0) {
            return 1;
        }
        return Arrays.stream(couts).max().orElse(1);
    }
}
