package fr.GooseQuack.sacados;

import java.util.List;


/**
 * Classe d'un sac à dos (problème du sac à dos multidimensionnel).
 * 
 * @author William (miwate)
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
}
