package fr.GooseQuack.solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * @author Drys (lidr05)
 * @version 1.0
 */

// Méthodes seulement
public class GloutonAjoutSolver {
    
    // Methode Gloutonne par Ajout par Somme ou par Max
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

    // Utilitaire : Contrainte principale de budget
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

    // Utilitaire : Mets à jour les coûts
    private void changementsCouts(Objet o, int[] coutsTotal){
        int[] coutsO = o.getCouts();
        int dim = coutsTotal.length;

        for (int l = 0; l < dim; l++) {
            coutsTotal[l] += coutsO[l];
        }
    }
}