package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

public class ComparatorfMax implements Comparator<Objet> {

    @Override
    public int compare(Objet o1, Objet o2) {
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
