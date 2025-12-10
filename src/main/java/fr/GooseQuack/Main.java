package fr.GooseQuack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.GooseQuack.equipe.Cout;
import fr.GooseQuack.equipe.Elu;
import fr.GooseQuack.equipe.EquipeMunicipale;
import fr.GooseQuack.equipe.Evaluateur;
import fr.GooseQuack.equipe.Expert;
import fr.GooseQuack.equipe.Secteur;
import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;
import fr.GooseQuack.solveur.glouton.ComparatorfMax;
import fr.GooseQuack.solveur.glouton.ComparatorfSomme;
import fr.GooseQuack.solveur.glouton.GloutonAjoutSolver;
import fr.GooseQuack.solveur.glouton.GloutonRetraitSolver;
import jdk.jshell.spi.SPIResolutionException;


public class Main {

    public static void main(String[] args) {
        System.out.println("Running main...");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("========================= \n Goose Quack\n =========================");

        while (running) {
            try {
                System.out.println("MENU :");
                System.out.println("1. Simulation d'une équipe municipale");
                System.out.println("2. Résolution d'un problème de maximisation à partir d'un fichier");
                System.out.println("3. Quitter.");

                if (scanner.hasNextLine()) {
                    String entree = scanner.nextLine();
                    int choix = Integer.parseInt(entree);

                    switch (choix) {
                        case 1 :
                            simulationEquipe(scanner);
                            break;
                        case 2 :
                            resolutionFichier(scanner);
                            break;
                        case 3 :
                            running = false;
                            System.out.println("Fin de l'exécution.");
                            break;
                        default :
                            System.out.println("Veuillez saisir un nom correspondant aux choix disponibles !");
                    }
                }
                else {
                    System.err.println("Erreur : Pas d'entrée détectée. Fin.");
                    running = false;
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Erreur : Nombre entier attendu.");
            } catch (Exception e) {
                System.err.println("Erreur : " + e.getMessage());
                // e.printStackTrace();
            }
        }
        scanner.close();
    }

    // Choix 1 :
    public static void simulationEquipe(Scanner scanner) {
        // Instances d'exemple :
        Elu elu = new Elu("Gilbert", "Hugo", 34);
        Evaluateur evaluateurEco = new Evaluateur("Gilbart","Huga", 33, Cout.ECONOMIQUE);
        Evaluateur evaluateurSocial = new Evaluateur("Gilbort","Hugues", 32, Cout.SOCIAL);
        Evaluateur evaluateurEnvironnement = new Evaluateur("Gilbirt","Hugwo", 31, Cout.ENVIRONNEMENTAL);
        Expert expertSportSante = new Expert("Gilbertss", "Hugoss", 30, Secteur.SANTE, Secteur.SANTE);
        Expert expertCultureEco= new Expert("Gilbertce", "Hugoce", 31, Secteur.CULTURE, Secteur.ATTRACTIVITE_ECONOMIQUE);

        // Ajout des membres dans l'equipe :
        EquipeMunicipale equipe = new EquipeMunicipale();
        try {
            equipe.setElu(elu);
            equipe.addEvaluateur(evaluateurEco);
            equipe.addEvaluateur(evaluateurSocial);
            equipe.addEvaluateur(evaluateurEnvironnement);
            equipe.addExpert(expertSportSante);
            equipe.addExpert(expertCultureEco);
        } catch (Exception e) {
            System.err.println("Echec de la construction de l'équipe : " + e.getMessage()); 
        }
        
        System.out.println("/////// DÉBUT : Cycle de Simulation //////");
        equipe.cycleSimulation();
        System.out.println("/////// FIN : Cycle de Simulation //////");
        System.out.println("Nombre de projets : " + equipe.getProjets().size());

        // Conversion Vers Sac A Dos
        System.out.println("Création des budgets...");
        int budgetEconomique = 1000;
        int budgetSocial = 1000;
        int budgetEnvironnemental = 1000;
        System.out.println("Budgets :\nEconomique - " + budgetEconomique + "\nSocial - " + budgetSocial + "\nEnvironnemental - " + budgetEnvironnemental);

        try {
            SacADos sac = VersSacADos.versTypeCout(equipe.getProjets(), budgetEconomique, budgetSocial, budgetEnvironnemental);
            executionSolveur(sac, scanner);
        } catch (Exception e) {
            System.err.println("Erreur (conversion vers Sac à Dos) : " + e.getMessage());
        }
    }

    public static void executionSolveur(SacADos sac, Scanner scanner) {
        System.out.println("== Menu - Choix du solveur ==");
        System.out.println("1. Méthode Gloutonne par Ajout (Tri par \"Somme\")");
        System.out.println("2. Méthode Gloutonne par Ajout (Tri par \"Max\")");
        System.out.println("3. Méthode Gloutonne par Retrait (Tri par \"Somme\")");
        System.out.println("4. Méthode Gloutonne par Retrait (Tri par \"Max\")");

        int choix = Integer.parseInt(scanner.nextLine());
        List<Objet> solutionS = new ArrayList<>();

        // long debut = System.currentTimeMillis();

        // Instanciation : Comparateurs
        ComparatorfSomme comparatorfSomme = new ComparatorfSomme();
        ComparatorfMax comparatorfMax = new ComparatorfMax();

        GloutonAjoutSolver solverAjout = new GloutonAjoutSolver();
        GloutonRetraitSolver solverRetrait = new GloutonRetraitSolver();

        switch (choix) {
            case 1 :
                solutionS = solverAjout.MethodeParAjout(sac, comparatorfSomme);
                break;
            case 2 :
                solutionS = solverAjout.MethodeParAjout(sac, comparatorfMax);
                break;
            
            case 3 :
                solutionS = solverRetrait.methodeParRetrait(sac, comparatorfSomme, comparatorfSomme);
                break;
            case 4 :
                solutionS = solverRetrait.methodeParRetrait(sac, comparatorfMax, comparatorfMax);
                break;
            default :
                System.out.println("Méthode non Implémentée.");
                return;
        }
        
        // long fin = System.currentTimeMillis();

        if (solutionS != null) {
            int utiliteTotale = 0;
            for (Objet o : solutionS) {
                utiliteTotale += o.getUtilite();
            }
            System.out.println("== Résultats ==");
            System.out.println("Solution : " + solutionS.size() + " objets, d'utilité totale : " + utiliteTotale);
            System.out.println("== Fin ==");
        }

    }

    // Choix 2 :
    private static void resolutionFichier(Scanner scanner) {
        System.out.println("== Chargement du fichier ==");
        System.out.println("Saisir le chemin du fichier : ");
        
        String chemin = scanner.nextLine();

        try {
            SacADos sac = VersSacADos.loadFichier(chemin);
            System.out.println("Dimensions : " + sac.getDimension() + "\nNombre d'objets : " + sac.getObjets().size());
            executionSolveur(sac, scanner);

        } catch (IOException e) {
            System.err.println("Erreur (lecture du fichier) : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur (fichier invalide) : " + e.getMessage());
        }
    }
}

    /*
        // Tests des algorithmes gloutons
        List<Objet> objetsTest = new ArrayList<>();

        objetsTest.add(new Objet(300, new int[] {100, 290, 25}));
        objetsTest.add(new Objet(840, new int[] {1000, 400, 15}));
        objetsTest.add(new Objet(300, new int[] {400, 120, 5}));
        objetsTest.add(new Objet(100, new int[] {50, 5, 3}));
        objetsTest.add(new Objet(1200, new int[] {770, 880, 40}));
        objetsTest.add(new Objet(300, new int[] {100, 290, 30}));
        objetsTest.add(new Objet(100, new int[] {10, 210, 9}));

        int[] bud = {1100,500,150};

        SacADos sac = new SacADos(3, bud, objetsTest);

        System.out.println("Budget test : " + Arrays.toString(bud));

        for (int l = 0; l < objetsTest.size(); l++) {
            Objet obj = objetsTest.get(l);
            System.out.printf("Objet %d \t Utilité %d \t Coûts {%d,%d,%d}\n", l, obj.getUtilite(), obj.getCouts()[0], obj.getCouts()[1], obj.getCouts()[2]);
        }

        GloutonRetraitSolver solver = new GloutonRetraitSolver();
        ComparatorfSomme compSomme = new ComparatorfSomme();
        ComparatorfMax compMax = new ComparatorfMax();

        // Méthode Par Retrait par f_Somme
        List<Objet> res1 = solver.methodeParRetrait(sac, compSomme, compSomme);
        System.out.println("ComparatorfSomme : " + res1.size() + " objets");
        int util1 = 0;
        for (Objet o : res1) {
            util1 += o.getUtilite();
        }
        System.out.println("\t Utilité totale : " + util1);

        // Méthode Par Retrait par f_Max
        List<Objet> res2 = solver.methodeParRetrait(sac, compMax, compMax);
        System.out.println("ComparatorfMax : " + res2.size() + " objets");
        int util2 = 0;
        for (Objet o : res2) {
            util2 += o.getUtilite();
        }
        System.out.println("\t Utilité totale : " + util2);

    }

}

*/