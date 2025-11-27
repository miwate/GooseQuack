package fr.GooseQuack.solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Classe de la méthode gloutonne par Retrait (en fonction d'un comparateur choisi).
 *  
 * @author Drys (lidr05)
 * @author William (miwate)
 * 
 * @version 1.0
 */


// Méthodes seulement
public class GloutonRetraitSolver {
    
    /**
     * 
     * @param sac Le sac à dos
     * @param comparatorAjout Comparateur de la phase d'ajout
     * @param comparatorRetrait Comparateur pour la phase de retrait
     * @return Une solution du problème
     * @throws IllegalArgumentException Si un argument donné est null
     */
    public List<Objet> methodeParRetrait(SacADos sac, Comparator<Objet> comparatorAjout, Comparator<Objet> comparatorRetrait) {

        if (sac == null) {
            throw new IllegalArgumentException("Le sac ne doit pas être null");
        }

        if (comparatorAjout == null || comparatorRetrait == null) {
            throw new IllegalArgumentException("Les comparateurs ne doivent pas être null");
        }

        int dimension = sac.getDimension();
        int[] budgets = sac.getBudgets();

        // Phase de RETRAIT
        List<Objet> S = new ArrayList<>(sac.getObjets());
        List<Objet> objetsTriTmp = new ArrayList<>(sac.getObjets());
        objetsTriTmp.sort(comparatorRetrait.reversed());

        long[] coutsTotal = calculeCoutsTotaux(S, dimension);

        for (Objet obj : objetsTriTmp) {
            if (depasseBudget(coutsTotal, budgets)) {
                S.remove(obj);
                retireCouts(obj, coutsTotal);
            }
            else {
                break;
            }
        }

        // Phase d'AJOUT
        List<Objet> objetsComp = new ArrayList<>(sac.getObjets());
        objetsComp.removeAll(S);
        objetsComp.sort(comparatorAjout);

        coutsTotal = calculeCoutsTotaux(S, dimension);

        for (Objet o : objetsTriTmp) {
            if (sommeCoutsInfBudget(o, coutsTotal, budgets)) {
                S.add(o);
                ajouteCouts(o, coutsTotal);
            }
        }

        return S;
    }

    /** 
     * Calcule les coûts totaux d'une liste d'objects sur toutes les dimensions
     * 
     * @param objets Une liste d'objets
     * @param dimension Le nombre de dimensions
     * @return Le tableau des coûts totaux de taille dimension
     */
    private long[] calculeCoutsTotaux(List<Objet> objets, int dimension) {
        long[] totaux = new long[dimension];

        for (Objet obj : objets) {
            int[] couts = obj.getCouts();

            for (int l = 0; l < dimension; l++) {
                totaux[l] += couts[l];
            }
        }
        return totaux;
    }

    /**
     * 
     * @param coutsTotaux
     * @param budgets
     * @return true si au moins un budget est dépassé
     */
    private boolean depasseBudget(long[] coutsTotaux, int[] budgets) {
        for (int l = 0; l < budgets.length; l++) {
            if (coutsTotaux[l] > budgets[l]) {
                return true;
            }
        }
        return false;
    }


    /**
     * Vérifie si l'ajout d'un objet respecte les contraintes de budget sur toutes les dimensions.
     *
     * @param o L'objet candidat à ajouter
     * @param coutsTotal Les coûts actuels pour chaque dimension
     * @param budgets Les budgets max pour chaque dimension
     * @return true si l'ajout de l'objet ne dépasse aucun budget, false sinon
     */
    public boolean sommeCoutsInfBudget(Objet o, long[] coutsTotal, int[] budgets) {
        int coutsO[] = o.getCouts();
        int dim = budgets.length;

        for (int l=0; l < dim; l++) {
            if (coutsTotal[l] + coutsO[l] > budgets[l]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ajoute les coûts d'un objet sur toutes les dimensions
     *
     * @param o L'objet dont les coûts doivent être ajoutés
     * @param coutsTotal Les coûts totaux à changer
     */
    private void ajouteCouts(Objet o, long[] coutsTotal){
        int[] coutsO = o.getCouts();
        int dim = coutsTotal.length;

        for (int l = 0; l < dim; l++) {
            coutsTotal[l] += coutsO[l];
        }
    }

    /**
     * Retire les coûts d'un objet sur toutes les dimensions
     *
     * @param o L'objet dont les coûts doivent être retirés
     * @param coutsTotal Les coûts totaux à changer
     */
    private void retireCouts(Objet o, long[] coutsTotal){
        int[] coutsO = o.getCouts();
        int dim = coutsTotal.length;

        for (int l = 0; l < dim; l++) {
            coutsTotal[l] -= coutsO[l];
        }
    }
}
