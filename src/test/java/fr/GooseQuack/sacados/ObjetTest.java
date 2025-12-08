package fr.GooseQuack.sacados;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit pour la classe Objet
 *
 * @author William (miwate)
 * @version 1.0
 */
class ObjetTest {

    // ------------- Test du constructeur Objet --------------

    /**
     * Test de Objet pour check la bonne création
     */
    @Test
    @DisplayName("Objet : création d'un objet qui marche")
    void testObjet() {
        int[] couts = {15400, 20560, 84900, 8451};
        Objet obj = new Objet(84915, couts);

        assertNotNull(obj);
        assertEquals(84915, obj.getUtilite());
        assertArrayEquals(new int[]{15400, 20560, 84900, 8451}, obj.getCouts());
    }

    /**
     * Test de Objet pour utilité de zéro
     */
    @Test
    @DisplayName("Objet : utilité de zéro")
    void testObjetUtilZero() {
        int[] couts = {84951};
        Objet obj = new Objet(0, couts);

        assertNotNull(obj);
        assertEquals(0, obj.getUtilite());
    }

    /**
     * Test de Objet pour tous les coûts de zéro
     */
    @Test
    @DisplayName("Objet : tous les coûts de zéro")
    void testObjetCoutZero() {
        int[] couts = {0, 0, 0, 0, 0, 0, 0};
        Objet obj = new Objet(88715325, couts);

        assertNotNull(obj);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0}, obj.getCouts());
    }

    /**
     * Test de Objet pour 80 dimensions
     */
    @Test
    @DisplayName("Objet : 80 dimensions")
    void testObjetBcpDim() {
        int[] couts = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80};
        Objet obj = new Objet(1, couts);

        assertNotNull(obj);
        assertEquals(80, obj.getNbDimensions());
        assertArrayEquals(couts, obj.getCouts());
    }

    /**
     * Test de Objet pour utilité négative
     */
    @Test
    @DisplayName("Objet : utilité négative")
    void testObjetUtilNeg() {
        int[] couts = {1};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Objet(-1, couts);
        });

        assertEquals("L'utilité doit être positive", exception.getMessage());
    }

    /**
     * Test de Objet pour liste des coûts null
     */
    @Test
    @DisplayName("Objet : liste null des coûts")
    void testObjetCoutsNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Objet(1, null);
        });

        assertEquals("Le tableau des coûts doit être non vide", exception.getMessage());
    }

    /**
     * Test de Objet pour liste des coûts vide
     */
    @Test
    @DisplayName("Objet : tableau de coûts vide - IllegalArgumentException")
    void testObjetCoutsVide() {
        int[] couts = {};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Objet(1, couts);
        });

        assertEquals("Le tableau des coûts doit être non vide", exception.getMessage());
    }

    /**
     * Test de Objet pour un coût négatif mais la liste est non nulle non vide
     */
    @Test
    @DisplayName("Objet : coût négatif mais la liste est non nulle non vide")
    void testConstructeurPremierCoutNegatif() {
        int[] couts = {1,1,-1,1,1,1,1,1};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Objet(1, couts);
        });

        assertEquals("Tous les coûts doivent être positifs", exception.getMessage());
    }

    /**
     * Test de Objet lorsqu'on essaie de modifier un élément du tableau couts
     */
    @Test
    @DisplayName("Objet : lorsqu'on essaie de modifier un élément du tableau couts")
    void testObjetModifCouts() {
        int[] couts = {1, 1, 1};
        Objet obj = new Objet(8491523, couts);

        couts[0] = 667677;
        couts[1] = 6677;
        couts[2] = 76777;

        assertArrayEquals(new int[]{1, 1, 1}, obj.getCouts());
    }
}
