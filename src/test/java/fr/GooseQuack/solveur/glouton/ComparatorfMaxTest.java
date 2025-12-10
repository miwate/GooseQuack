package fr.GooseQuack.solveur.glouton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.GooseQuack.sacados.Objet;


/**
 * Tests JUnit pour la classe ComparatorfMax
 *
 * @author William (miwate)
 * @version 1.0
 */
class ComparatorfMaxTest {

    private ComparatorfMax comparator;


    @BeforeEach
    void setup() {
        comparator = new ComparatorfMax();
    }

    // ----------- compare sur dimension 1 -----------
    /**
     * compare : o1 meilleur que o2
     */
    @Test
    @DisplayName("compare : o1 meilleur que o2")
    void testCompareO1Best() {

        Objet o1 = new Objet(1000000, new int[]{3, 6, 7});
        Objet o2 = new Objet(50, new int[]{3, 6, 7});


        int result = comparator.compare(o1, o2);
        assertTrue(result < 0);
    }

    /**
     * compare : o2 meilleur que o1
     */
    @Test
    @DisplayName("compare : o2 meilleur que o1")
    void testCompareO2Best() {

        Objet o2 = new Objet(1000000, new int[]{3, 6, 7});
        Objet o1 = new Objet(50, new int[]{3, 6, 7});


        int result = comparator.compare(o1, o2);
        assertTrue(result > 0);
    }


    /**
     * compare : o1 et o2 identiques
     */
    @Test
    @DisplayName("compare : o1 et o2 identiques")
    void testCompareO1O2idem() {

        Objet o2 = new Objet(1000000, new int[]{3, 6, 7});
        Objet o1 = new Objet(1000000, new int[]{3, 6, 7});

        int result = comparator.compare(o1, o2);

        assertEquals(0, result);
    }



    /**
     * compare : o1 et o2 même ratio mais o2 meilleure utilité
     */
    @Test
    @DisplayName("compare : o1 et o2 même ratio mais o2 meilleure utilité")
    void testCompareRatioO2() {

        Objet o1 = new Objet(5000, new int[]{1000});
        Objet o2 = new Objet(15000, new int[]{3000});

        int result = comparator.compare(o1, o2);

        assertTrue(result > 0);
    }

    /**
     * compare : o1 et o2 même ratio mais o1 meilleure utilité
     */
    @Test
    @DisplayName("compare : o1 et o2 même ratio mais o1 meilleure utilité")
    void testCompareRatioO1() {

        Objet o2 = new Objet(5000, new int[]{1000});
        Objet o1 = new Objet(15000, new int[]{3000});

        int result = comparator.compare(o1, o2);

        assertTrue(result < 0);
    }





}
