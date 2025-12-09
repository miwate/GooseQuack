package fr.GooseQuack.solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Solveur utilisant la méthode gloutonne par ajout
 * 
 * @author Drys (lidr05)
 * @version 1.0
 */

// Méthodes seulement
public class GloutonAjoutSolver {
    
    /**
     * Résout le problème du sac à dos multidimensionnel par la méthode gloutonne par ajout
     *
     * @param sac le sac à dos
     * @param comparator le comparateur utilisé
     * @return une liste d'objets pour la solution gloutonne
     */
    public List<Objet> MethodeParAjout(SacADos sac, Comparator<Objet> comparator) {
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
     * Vérifie si l'ajout d'un objet respecte les contraintes de budget
     *
     * @param o l'objet candidat
     * @param coutsTotal les coûts totaux actuels
     * @param budgets les budgets maximums
     * @return true si l'ajout de l'objet ne dépasse aucun budget, sinon false
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
     * Met à jour les coûts totaux en ajoutant les coûts d'un objet
     *
     * @param o l'objet
     * @param coutsTotal les coûts totaux à mettre à jour
     */
    private void changementsCouts(Objet o, int[] coutsTotal){
        int[] coutsO = o.getCouts();
        int dim = coutsTotal.length;

        for (int l = 0; l < dim; l++) {
            coutsTotal[l] += coutsO[l];
        }
    }
}