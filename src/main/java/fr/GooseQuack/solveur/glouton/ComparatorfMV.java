package fr.GooseQuack.solveur.glouton;

import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;
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
 * Comparateur pour la méthode à retrait sur f_mv
 */
public class ComparatorfMV implements Comparator<Objet> {

    private final SacADos sac;
    
    private final List<Objet> solutionCourante;

    /**
     * Construit un comparator f_mv
     * 
     * @param sac le sac à dos
     * @param solutionCourante la solution courante pour calculer les dépassements
     * @throws IllegalArgumentException si le sac ou la solution courante est null
     */
    public ComparatorfMV(SacADos sac, List<Objet> solutionCourante) {
        if (sac == null) {
            throw new IllegalArgumentException("Le sac ne doit pas être null");
        }

       if (solutionCourante == null) {
            throw new IllegalArgumentException("La solution courante ne doit pas être null");
       }

       this.sac = sac;
       this.solutionCourante = solutionCourante;

    }

    /**
     * Compare deux objets selon f_mv
     *
     * @param o1 le premier objet
     * @param o2 le deuxième objet
     * @return négatif si o1 est mieux, positif si o2 est mieux, 0 si égaux
     * @throws IllegalArgumentException si o1 est null ou o2 est null
     */
    @Override
    public int compare(Objet o1, Objet o2) {

        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Les objets ne doivent pas être null");
        }

        int dimension = sac.getDimension();
        int[] budgets = sac.getBudgets();

        // Calculer les dépassements de budget pour chaque dimension
        int[] coutsActuels = new int[dimension];

        for (Objet obj : solutionCourante) {
            int[] couts = obj.getCouts();

            for (int l = 0; l < dimension; l++) {
                coutsActuels[l] += couts[l];
            }
        }

        long[] depassements = new long[dimension];

        for (int l = 0; l < dimension; l++) {
            long diff = (long) coutsActuels[l] - budgets[l];

            if (diff > 0) {
                depassements[l] = diff;
            }
            else {
                depassements[l] = 0;
            }
        }


        // Trouver le dépassement max
        long maxDepassement = 0;

        for (int l = 0; l < dimension; l++) {
            if (depassements[l] > maxDepassement) {
                maxDepassement = depassements[l];
            }
        }


        // Trouver les dimensions avec dépassement max

        int cMax1 = 0, cMax2 = 0;

        // Pas de dépassement, on utilise le max des coûts
        if (maxDepassement == 0) {
            cMax1 = SacADosCalculs.maxCouts(o1.getCouts());
            cMax2 = SacADosCalculs.maxCouts(o2.getCouts());
        }
        // Trouver le max des coûts parmi les dimensions avec dépassement maximal
        else {
            for (int l = 0; l < dimension; l++) {
                if (depassements[l] == maxDepassement) {

                    int cout1 = o1.getCout(l);
                    int cout2 = o2.getCout(l);

                    if (cout1 > cMax1) {
                        cMax1 = cout1;
                    }
                    if (cout2 > cMax2) {
                        cMax2 = cout2;
                    }
                }
            }
        }

        // On utilise le produit en croix pour éviter la division (preuve dans le rapport)
        long produit1 = (long) o1.getUtilite() * cMax2;
        long produit2 = (long) o2.getUtilite() * cMax1;

        int result = Long.compare(produit2, produit1); // Ordre décroissant

        // On utilise l'utilité si il y a égalité
        if (result == 0) {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
        return result;
    }

}

