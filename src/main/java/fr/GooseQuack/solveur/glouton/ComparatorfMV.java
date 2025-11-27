package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADosCalculs;

/**
 * Classe implémentant le critère formel de comparaison par "l’ensemble des dimensions avec le plus gros dépassement de budget" (f_mv).
 *
 * @author Drys (lidr05)
 * @author William (miwate)
 * 
 * @version 1.0
 */


/**
 * Comprateur pour la méthode à retrait sur f_mv
 */
public class ComparatorfMV implements Comparator<Objet> {



        // A CHANGER
    @Override
    public int compare(Objet o1, Objet o2) {
        
        int sommeCouts1 = SacADosCalculs.sommeCouts(o1.getCouts());
        int sommeCouts2 = SacADosCalculs.sommeCouts(o2.getCouts());

        // Produit en croix (preuve dans le rapport)
        long produit1 = (long) o1.getUtilite() * sommeCouts2;
        long produit2 = (long) o2.getUtilite() * sommeCouts1;

        int result = Long.compare(produit2, produit1); //Ordre décroissant

        //On utilise l'utilité si il y a égalité
        if (result == 0) {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
        return result;
    }

}

