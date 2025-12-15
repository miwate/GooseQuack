package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

/**
 * Classe implémentant le critère formel de comparaison par le max (f_max).
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */


public class ComparatorfMax implements Comparator<Objet> {

    /**
     * Compare deux objets selon f_max
     *
     * @param o1 le premier objet
     * @param o2 le deuxième objet
     * @return négatif si o1 est mieux, positif si o2 est mieux, 0 si égaux
     * @throws IllegalArgumentException si o1 ou o2 est null
     */
    @Override
    public int compare(Objet o1, Objet o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Les objets ne doivent pas être null");
        }
        
        // Calcul du maximum des couts
        int maxCout1 = SacADosCalculs.maxCouts(o1.getCouts());
        int maxCout2 = SacADosCalculs.maxCouts(o2.getCouts());

        double f_max1 = o1.getUtilite() / (double) maxCout1;
        double f_max2 = o2.getUtilite() / (double) maxCout2;

        if (f_max1 > f_max2) {
            return -1; // Ordre décroissant
        }
        else if (f_max1 < f_max2) {
            return 1;
        }
        else {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
    }

}
