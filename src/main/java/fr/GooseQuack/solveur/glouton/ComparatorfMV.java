package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

/**
 * Classe implémentant le critère formel de comparaison par "l’ensemble des dimensions avec le plus gros dépassement de budget" (f_mv).
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */


public class ComparatorfMV implements Comparator<Objet> {

        // A CHANGER
    @Override
    public int compare(Objet o1, Objet o2) {
        
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

