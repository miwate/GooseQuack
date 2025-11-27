package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author Drys (lidr05)
 * @author William (miwate)
 * 
 * @version 1.0
 */


public class ComparatorfMax implements Comparator<Objet> {

    @Override
    public int compare(Objet o1, Objet o2) {
        // Calcul du maximum des couts
        int maxCout1 = SacADosCalculs.maxCouts(o1.getCouts());
        int maxCout2 = SacADosCalculs.maxCouts(o2.getCouts());

        // Produit en croix (preuve dans le rapport)
        long produit1 = (long) o1.getUtilite() * maxCout2;
        long produit2 = (long) o2.getUtilite() * maxCout1;

        int result = Long.compare(produit2, produit1); //Ordre décroissant

        //On utilise l'utilité si il y a égalité
        if (result == 0) {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
        return result;
        }

}
