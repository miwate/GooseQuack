package fr.GooseQuack.sacados;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Tests de la classe SacADos
 *
 * @author William (miwate)
 * @version 1.0
 */
class SacADosTest {

    /**
     * getBudgets : renvoit les mêmes valeurs
     */
    @Test
    @DisplayName("getBudgets : renvoit les mêmes valeurs")
    void testGetBudgetsValeurs() {
        int[] budgets = {123, 456, 87945132};


        List<Objet> objets = new ArrayList<>();
        SacADos sac = new SacADos(3, budgets, objets);

        assertArrayEquals(new int[]{123, 456, 87945132}, sac.getBudgets());
    }



    /**
     * getBudgets : protection de la liste
     */
    @Test
    @DisplayName("getBudgets : protection de la liste")
    void testGetBudgetsCopy() {
        int[] budgets = {123, 456, 87945132};


        List<Objet> objets = new ArrayList<>();
        SacADos sac = new SacADos(3, budgets, objets);

        int[] budgetsBis = sac.getBudgets();

        budgetsBis[0] = 78;
        budgetsBis[1] = 561;
        budgetsBis[2] = 847661;

        assertArrayEquals(new int[]{123, 456, 87945132}, sac.getBudgets());
    }


    /**
     * getBudgets : 80 dimensions
     */
    @Test
    @DisplayName("getBudgets : 80 dimensions")
    void testGetBudgets80Dim() {
        int[] budgets = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,
            65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80};
        

        List<Objet> objets = new ArrayList<>();
        SacADos sac = new SacADos(10, budgets, objets);

        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,78,79,80}, sac.getBudgets());
    }


    /**
     * getBudgets : protection de la liste + copie
     */
    @Test
    @DisplayName("getBudgets : protection de la liste + copie")
    void testGetBudgetsCopy2() {
        int[] budgets = {123, 456, 87945132, 841532545};


        List<Objet> objets = new ArrayList<>();
        SacADos sac = new SacADos(4, budgets, objets);

        int[] b1 = sac.getBudgets();
        int[] b2 = sac.getBudgets();

        b1[0] = 0;
        b1[1] = 0;
        b1[2] = 0;
        b1[3] = 0;

        assertArrayEquals(new int[]{0, 0, 0, 0}, b1);
        
        assertArrayEquals(new int[]{123, 456, 87945132, 841532545}, b2);
        assertArrayEquals(new int[]{123, 456, 87945132, 841532545}, sac.getBudgets());
    }
}
