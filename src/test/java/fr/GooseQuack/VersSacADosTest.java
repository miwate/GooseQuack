package fr.GooseQuack;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.GooseQuack.equipe.Cout;
import fr.GooseQuack.equipe.Projet;
import fr.GooseQuack.equipe.Secteur;
import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Tests JUnit pour la classe VersSacADos
 * 
 * @author William (miwate)
 * @version 1.0
 */
class VersSacADosTest {

    private List<Projet> projetsTest;

    /**
     * Initialiser les projets avant chaque test
     */
    @BeforeEach
    void setup() {
        projetsTest = new ArrayList<>();

        // Projet sport
        Projet pSport = new Projet("Marathon", "Il faut courir", Secteur.SPORT);
        pSport.setBenefice(3000);
        pSport.setCout(Cout.ECONOMIQUE, 2000);
        pSport.setCout(Cout.SOCIAL, 1000);
        pSport.setCout(Cout.ENVIRONNEMENTAL, 200);
        projetsTest.add(pSport);

        // Projet culture
        Projet pCulture = new Projet("Exposition Spéciale", "Exposition sur les canards mais spéciale", Secteur.CULTURE);
        pCulture.setBenefice(4500);
        pCulture.setCout(Cout.ECONOMIQUE, 1000);
        pCulture.setCout(Cout.SOCIAL, 2000);
        pCulture.setCout(Cout.ENVIRONNEMENTAL, 1500);
        projetsTest.add(pCulture);

        // Projet santé
        Projet pSante = new Projet("Vaccination Spéciale", "Un vaccin mais un peu spécial", Secteur.SANTE);
        pSante.setBenefice(7000);
        pSante.setCout(Cout.ECONOMIQUE, 2500);
        pSante.setCout(Cout.SOCIAL, 1500);
        pSante.setCout(Cout.ENVIRONNEMENTAL, 1000);
        projetsTest.add(pSante);

        // Projet éducation
        Projet pEducation = new Projet("École Spéciale", "École de magie noire (mais spéciale)", Secteur.EDUCATION);
        pEducation.setBenefice(6500);
        pEducation.setCout(Cout.ECONOMIQUE, 1800);
        pEducation.setCout(Cout.SOCIAL, 1000);
        pEducation.setCout(Cout.ENVIRONNEMENTAL, 700);
        projetsTest.add(pEducation);

        // Projet attractivité
        Projet pAttractivite = new Projet("Parc Spécial", "Un parc mais spécial", Secteur.ATTRACTIVITE_ECONOMIQUE);
        pAttractivite.setBenefice(7500);
        pAttractivite.setCout(Cout.ECONOMIQUE, 2200);
        pAttractivite.setCout(Cout.SOCIAL, 1000);
        pAttractivite.setCout(Cout.ENVIRONNEMENTAL, 1400);
        projetsTest.add(pAttractivite);
    }

    // ------------------------ versTypeCout --------------------------

    /**
     * versTypeCout check de la création d'un projet seul
     */
    @Test
    @DisplayName("versTypeCout : check de la création d'un projet seul")
    void testVersTypeCoutCheckSolo() {
        List<Projet> solo = new ArrayList<>();
        Projet p = new Projet("solo", "solo", Secteur.ATTRACTIVITE_ECONOMIQUE);

        p.setBenefice(84952130);
        p.setCout(Cout.ECONOMIQUE, 1);
        p.setCout(Cout.SOCIAL, 1);
        p.setCout(Cout.ENVIRONNEMENTAL, 1);
        solo.add(p);

        SacADos sac = VersSacADos.versTypeCout(solo, 1, 1, 1);
        Objet obj = sac.getObjets().get(0);

        assertNotNull(sac);

        assertEquals(3, sac.getDimension());
        assertEquals(1, sac.getObjets().size());

        assertEquals(84952130, obj.getUtilite());

        assertArrayEquals(new int[]{1, 1, 1}, obj.getCouts());
    }

    /**
     * Test de versTypeCout check de la création
     */
    @Test
    @DisplayName("versTypeCout : conversion des 5 types de projets")
    void testVersTypeCoutCheck() {
        SacADos sac = VersSacADos.versTypeCout(projetsTest, 10000, 6000, 4000);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertArrayEquals(new int[]{10000, 6000, 4000}, sac.getBudgets());
        assertEquals(5, sac.getObjets().size());

        // Vérification de tous les 5 objets
        Objet obj1 = sac.getObjets().get(0);
        assertEquals(3000, obj1.getUtilite());
        assertArrayEquals(new int[]{2000, 1000, 200}, obj1.getCouts());

        Objet obj2 = sac.getObjets().get(1);
        assertEquals(4500, obj2.getUtilite());
        assertArrayEquals(new int[]{1000, 2000, 1500}, obj2.getCouts());

        Objet obj3 = sac.getObjets().get(2);
        assertEquals(7000, obj3.getUtilite());
        assertArrayEquals(new int[]{2500, 1500, 1000}, obj3.getCouts());

        Objet obj4 = sac.getObjets().get(3);
        assertEquals(6500, obj4.getUtilite());
        assertArrayEquals(new int[]{1800, 1000, 700}, obj4.getCouts());

        Objet obj5 = sac.getObjets().get(4);
        assertEquals(7500, obj5.getUtilite());
        assertArrayEquals(new int[]{2200, 1000, 1400}, obj5.getCouts());
    }

    

    /**
     * Test de versTypeCout pour liste null
     */
    @Test
    @DisplayName("versTypeCout : liste null pour les projets")
    void testVersTypeCoutNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(null, 67, 67, 67);
        });

        assertEquals("La liste des projets doit être non null et non vide", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour liste vide
     */
    @Test
    @DisplayName("versTypeCout : liste de projets vide")
    void testVersTypeCoutVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(new ArrayList<>(), 67, 67, 67);
        });

        assertEquals("La liste des projets doit être non null et non vide", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour budget économisue négatif
     */
    @Test
    @DisplayName("versTypeCout : budget économique négatif")
    void testVersTypeCoutBudgetEcoNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(projetsTest, -1, 67, 67);
        });

        assertEquals("Les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour budget social négatif
     */
    @Test
    @DisplayName("versTypeCout : budget social négatif")
    void testVersTypeCoutBudgetSocNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(projetsTest, 67, -1, 67);
        });

        assertEquals("Les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour budget environnemmental négatif
     */
    @Test
    @DisplayName("versTypeCout : budget environnemental négatif")
    void testVersTypeCoutBudgetEnvNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(projetsTest, 67, 67, -1);
        });

        assertEquals("Les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour liste des projets ni nulle ni vide mais il y a un projet null
     */
    @Test
    @DisplayName("versTypeCout : au moins un projet null dans la liste")
    void testVersTypeCoutProjetNull() {
        projetsTest.add(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versTypeCout(projetsTest, 67, 67, 67);
        });

        assertEquals("Chaque projet de la liste doit être non null", exception.getMessage());
    }

    /**
     * Test de versTypeCout pour budget économique à zéro
     */
    @Test
    @DisplayName("versTypeCout : budget économique à zéro")
    void testVersTypeCoutBudgetEcoZero() {
        SacADos sac = VersSacADos.versTypeCout(projetsTest, 0, 67, 670);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertArrayEquals(new int[]{0, 67, 670}, sac.getBudgets());
    }

    /**
     * Test de versTypeCout pour budget social à zéro
     */
    @Test
    @DisplayName("versTypeCout : budget social à zéro")
    void testVersTypeCoutBudgetSocZero() {
        SacADos sac = VersSacADos.versTypeCout(projetsTest, 6767, 0, 6767);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertArrayEquals(new int[]{6767, 0, 6767}, sac.getBudgets());
    }

    /**
     * Test de versTypeCout pour budget environnemental à zéro
     */
    @Test
    @DisplayName("versTypeCout : budget environnemental à zéro")
    void testVersTypeCoutBudgetEnvZero() {
        SacADos sac = VersSacADos.versTypeCout(projetsTest, 1337, 1337, 0);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertArrayEquals(new int[]{1337, 1337, 0}, sac.getBudgets());
    }

    /**
     * Test de versTypeCout avec un seul projet
     */
    @Test
    @DisplayName("versTypeCout : un seul projet dans la liste")
    void testVersTypeCoutProjetSolo() {
        List<Projet> solo = new ArrayList<>();
        Projet p = new Projet("Single", "solo", Secteur.ATTRACTIVITE_ECONOMIQUE);

        p.setBenefice(65156);
        p.setCout(Cout.ECONOMIQUE, 546);
        p.setCout(Cout.SOCIAL, 546);
        p.setCout(Cout.ENVIRONNEMENTAL, 546);
        solo.add(p);

        SacADos sac = VersSacADos.versTypeCout(solo, 10000, 10000, 10000);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertEquals(1, sac.getObjets().size());

        Objet obj = sac.getObjets().get(0);
        assertEquals(65156, obj.getUtilite());
        assertArrayEquals(new int[]{546, 546, 546}, obj.getCouts());
    }

    /**
     * Test de versTypeCout avec coûts à zéro
     */
    @Test
    @DisplayName("versTypeCout : projet avec coûts à zéro")
    void testVersTypeCoutFree() {
        List<Projet> pFree = new ArrayList<>();
        Projet p = new Projet("Projet free", "free", Secteur.ATTRACTIVITE_ECONOMIQUE);

        p.setBenefice(1000001);
        p.setCout(Cout.ECONOMIQUE, 0);
        p.setCout(Cout.SOCIAL, 0);
        p.setCout(Cout.ENVIRONNEMENTAL, 0);
        pFree.add(p);

        SacADos sac = VersSacADos.versTypeCout(pFree, 10000, 10000, 10000);

        assertNotNull(sac);
        assertEquals(1, sac.getObjets().size());

        Objet obj = sac.getObjets().get(0);
        assertEquals(1000001, obj.getUtilite());
        assertArrayEquals(new int[]{0, 0, 0}, obj.getCouts());
    }



    // ----------------------- versSecteur ------------------------


    /**
     * versSecteur : check un projet uniquement
     */
    @Test
    @DisplayName("versSecteur : ucheck un projet uniquement")
    void testVersSecteurCheckSolo() {
        List<Projet> solo = new ArrayList<>();
        Projet p = new Projet("solo", "solo", Secteur.ATTRACTIVITE_ECONOMIQUE);

        p.setBenefice(84952130);
        p.setCout(Cout.ECONOMIQUE, 1);
        p.setCout(Cout.SOCIAL, 1);
        p.setCout(Cout.ENVIRONNEMENTAL, 1);
        solo.add(p);

        SacADos sac = VersSacADos.versSecteur(solo, 1, 1, 1, 1, 1);
        Objet obj = sac.getObjets().get(0);

        assertNotNull(sac);

        assertEquals(5, sac.getDimension());
        assertEquals(1, sac.getObjets().size());

        assertEquals(84952130, obj.getUtilite());
        
        assertArrayEquals(new int[]{0, 0, 0, 0, 1}, obj.getCouts());
    }


    /**
     * versSecteur : check de la création du sac
     */
    @Test
    @DisplayName("versSecteur : check de la création du sac")
    void testVersSecteurCheck() {
        SacADos sac = VersSacADos.versSecteur(projetsTest, 1, 1, 1, 1, 1);

        assertNotNull(sac);
        assertEquals(sac.getDimension(), 5);
        assertArrayEquals(new int[]{1,1,1,1,1}, sac.getBudgets());

        // Sport
        Objet obj0 = sac.getObjets().get(0);
        assertEquals(3000, obj0.getUtilite());
        assertArrayEquals(new int[]{2000, 0, 0, 0, 0}, obj0.getCouts());

        // Santé
        Objet obj1 = sac.getObjets().get(2);
        assertEquals(7000, obj1.getUtilite());
        assertArrayEquals(new int[]{0, 2500, 0, 0, 0}, obj1.getCouts());

        // Culture
        Objet obj2 = sac.getObjets().get(1);
        assertEquals(4500, obj2.getUtilite());
        assertArrayEquals(new int[]{0, 0, 1000, 0, 0}, obj2.getCouts());


        // Éducation
        Objet obj3 = sac.getObjets().get(3);
        assertEquals(6500, obj3.getUtilite());
        assertArrayEquals(new int[]{0, 0, 0, 1800, 0}, obj3.getCouts());

        // Actractivtité Économique
        Objet obj4 = sac.getObjets().get(4);
        assertEquals(7500, obj4.getUtilite());
        assertArrayEquals(new int[]{0, 0, 0, 0, 2200}, obj4.getCouts());
    }





    /**
     * versSecteur : liste null de projets
     */
    @Test
    @DisplayName("versSecteur : liste null de projets")
    void testVersSecteurNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(null, 1, 1, 1, 1, 1);
        });

        assertEquals("La liste des projets doit être non null et non vide", exception.getMessage());

    }

    /**
     * versSecteur : liste non null de projets mais un projet null
     */
    @Test
    @DisplayName("versSecteur : liste non null de projets mais un projet null")
    void testVersSecteurUnProjetNull() {
        projetsTest.add(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, 1, 1, 67, 1, 1);
        });

        assertEquals("Chaque projet de la liste doit être non null", exception.getMessage());
    }
    

    // Je doute de la pertinence de tous les tester car c'est sur la même ligne de code mais c'est pas grave je le fais quand même
    /**
     * versSecteur : budget négatif pour sport
     */
    @Test
    @DisplayName("versSecteur : budget négatif pour sport")
    void testVersSecteurBudgetSportNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, -1, 0, 0, 0, 0);
        });

        assertEquals("Tous les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * versSecteur : budget négatif pour santé
     */
    @Test
    @DisplayName("versSecteur : budget négatif pour santé")
    void testVersSecteurBudgetSanteNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, 0, -1, 0, 0, 0);
        });

        assertEquals("Tous les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * versSecteur : budget négatif pour culture
     */
    @Test
    @DisplayName("versSecteur : budget négatif pour culture")
    void testVersSecteurBudgetCultNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, 0, 0, -1, 0, 0);
        });

        assertEquals("Tous les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * versSecteur : budget négatif pour éducation
     */
    @Test
    @DisplayName("versSecteur : budget négatif pour éducation")
    void testVersSecteurBudgetEduNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, 0, 0, 0, -1, 0);
        });

        assertEquals("Tous les budgets doivent être positifs", exception.getMessage());
    }

    /**
     * versSecteur : budget négatif pour attractivité économique
     */
    @Test
    @DisplayName("versSecteur : budget négatif pour attractivité économique")
    void testVersSecteurBudgetAttNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VersSacADos.versSecteur(projetsTest, 0, 0, 0, 0, -1);
        });

        assertEquals("Tous les budgets doivent être positifs", exception.getMessage());
    }



    /**
     * versSecteur : plusieurs projets mais du même secteur
     */
    @Test
    @DisplayName("versSecteur : plusieurs projets du même secteur")
    void testVersSecteurMemeSecteur() {
        List<Projet> notreProjet = new ArrayList<>();

        Projet p1 = new Projet("LKJFSDLHQF", "aaaaaaaaaaaaaaa", Secteur.EDUCATION);
        p1.setBenefice(999);
        p1.setCout(Cout.ECONOMIQUE, 999);
        p1.setCout(Cout.SOCIAL, 999);
        p1.setCout(Cout.ENVIRONNEMENTAL, 999);
        notreProjet.add(p1);

        Projet p2 = new Projet("LKJFSDLHQF", "bbbbbbbbbbbbbb", Secteur.EDUCATION);
        p2.setBenefice(999);
        p2.setCout(Cout.ECONOMIQUE, 999);
        p2.setCout(Cout.SOCIAL, 999);
        p2.setCout(Cout.ENVIRONNEMENTAL, 999);
        notreProjet.add(p2);

        SacADos sac = VersSacADos.versSecteur(notreProjet, 999, 999, 999, 999, 999);

        Objet obj1 = sac.getObjets().get(0);

        assertEquals(999, obj1.getUtilite());
        assertArrayEquals(new int[]{0, 0, 0, 999, 0}, obj1.getCouts());

        Objet obj2 = sac.getObjets().get(1);
        
        assertEquals(999, obj2.getUtilite());
        assertArrayEquals(new int[]{0, 0, 0, 999, 0}, obj2.getCouts());
    }



}