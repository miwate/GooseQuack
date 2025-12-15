package fr.GooseQuack.sacados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author William (miwate)
 * @author Drys (lidr05)
 * @version 1.0
 */
public class SacADos {
    
    /**
     * Entier naturel : nombre de dimensions du problème.
     */
    private final int dimension;

    /**
     * Tableau d'entiers naturel : budget du sac à dos par dimension
     */    
    private final int[] budgets;
    private final List<Objet> objets;

    /**
     * Construit un sac à dos
     *
     * @param dimension la dimension du sac
     * @param budgets les budgets du sac 
     * @param objets les objets du sac
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

    // Getters
    /**
     * Donne la dimension du sac
     *
     * @return la dimension du sac
     */
    public int getDimension() {
        return dimension;
    }
    /**
     * Donne les budgets du sac
     *
     * @return les budgets du sac
     */
    public int[] getBudgets() {
        return Arrays.copyOf(budgets, budgets.length);
    }

    /**
     * Donne la liste d'objets du sac
     *
     * @return la liste d'objets du sac
     */
    public List<Objet> getObjets() {
        return Collections.unmodifiableList(objets); // Cree une COPIE IMMUABLE
    }
    
    // Pas de setters car variables de classe final
}
