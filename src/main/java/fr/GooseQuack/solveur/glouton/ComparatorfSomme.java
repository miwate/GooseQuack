package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */


public class ComparatorfSomme implements Comparator<Objet> {

    @Override
    public int compare(Objet o1, Objet o2) {
        // Calcul du maximum des couts
        int sommeCouts1 = SacADosCalculs.sommeCouts(o1.getCouts());
        int sommeCouts2 = SacADosCalculs.sommeCouts(o2.getCouts());

        double f_somme1 = o1.getUtilite() / (double) sommeCouts1;
        double f_somme2 = o2.getUtilite() / (double) sommeCouts2;

        if (f_somme1 > f_somme2) {
            return -1; // Ordre décroissant
        }
        else if (f_somme1 < f_somme2) {
            return 1;
        }
        else {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
    }

}
