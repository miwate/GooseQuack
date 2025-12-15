package fr.GooseQuack.solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Classe de la méthode gloutonne par Ajout (en fonction d'un comparateur choisi). 
 * @author Drys (lidr05)
 * @version 1.0
 */

// Méthodes seulement
public class GloutonAjoutSolver {
    
    /**
     * Résout le problème du sac à dos multidimensionnel par la méthode gloutonne par ajout
     *
     * @param sac le sac à dos
     * @param comparator le comparateur (critère utilisé)
     * @return une solution au problème
     * @throws IllegalArgumentException si un argument est null
     */

    public List<Objet> MethodeParAjout(SacADos sac, Comparator<Objet> comparator) {
        if (sac == null) {
            throw new IllegalArgumentException("Le sac ne doit pas être null");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Les comparateurs ne doivent pas être null");
        }

        List<Objet> objetsTriTmp = new ArrayList<>(sac.getObjets());
        objetsTriTmp.sort(comparator);

        List<Objet> S = new ArrayList<>();

        int dimension = sac.getDimension();
        int[] budgets = sac.getBudgets();
        int[] coutsTotal = new int[dimension];

        for (Objet o : objetsTriTmp) {
            if (sommeCoutsInfBudget(o, coutsTotal, budgets)) {
                S.add(o);
                changementsCouts(o, coutsTotal);
            }
        }

        return S;
    }
    
    /**
     * Vérifie si la somme des coûts est bien inférieure au budget
     *
     * @param o objet étudié
     * @param coutsTotal tableau des coûts totaux
     * @param budgets tableau des budgets
     * @return true si la condition est vérifiée, false sinon
     */
    public boolean sommeCoutsInfBudget(Objet o, int[] coutsTotal, int[] budgets) {
        int coutsO[] = o.getCouts();
        int dim = budgets.length;

        for (int l=0; l < dim; l++) {
            if ((long)coutsTotal[l] + coutsO[l] > budgets[l]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Met à jour le tableau des coûts totaux
     *
     * @param o objet étudié
     * @param coutsTotal tableau des coûts totaux
     */
    private void changementsCouts(Objet o, int[] coutsTotal){
        int[] coutsO = o.getCouts();
        int dim = coutsTotal.length;

        for (int l = 0; l < dim; l++) {
            coutsTotal[l] += coutsO[l];
        }
    }
}