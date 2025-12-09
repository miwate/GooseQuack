package fr.GooseQuack.sacados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author William (miwate)
 * @version 1.0
 */
public class SacADos {

    private final int dimension;

    private final int[] budgets;

    private final List<Objet> objets;

    /**
     * Construit un sac à dos multidimensionnel
     *
     * @param dimension le nombre de dimensions
     * @param budgets les budgets par dimension
     * @param objets la liste des objets
     */
    public SacADos(int dimension, int[] budgets, List<Objet> objets) {
        this.dimension = dimension;
        this.budgets = budgets.clone();

        if (objets != null) {
            this.objets = new ArrayList<>(objets);
        }
        else {
            this.objets = new ArrayList<>();
        }
    }

    /**
     * Donne le nombre de dimensions
     *
     * @return le nombre de dimensions
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Donne les budgets par dimension
     *
     * @return une copie du tableau des budgets (une copie pour protection)
     */
    public int[] getBudgets() {
        return Arrays.copyOf(budgets, budgets.length);
    }

    /**
     * Donne la liste des objets disponibles
     *
     * @return une liste immuable des objets
     */
    public List<Objet> getObjets() {
        return Collections.unmodifiableList(objets);
    }
    
    // Pas de setters car variables de classe final
}
