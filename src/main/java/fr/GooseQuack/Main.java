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
import fr.GooseQuack.solveur.glouton.ComparatorfMV;
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


                String entree = scanner.nextLine().trim();
                if (entree.isEmpty()) {
                    continue;
                }
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
                        System.out.println("Veuillez saisir un nom correspondant aux choix disponibles (1 à 3) !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Nombre entier attendu.");
            } catch (java.util.NoSuchElementException e) {
                    System.out.println("Arrêt forcé.");
                    running = false;
            } catch (Exception e) {
                System.err.println("Erreur : " + e.getMessage());
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
        System.out.println("\tBudgets :\nEconomique : " + budgetEconomique + "\nSocial : " + budgetSocial + "\nEnvironnemental : " + budgetEnvironnemental);

        try {
            SacADos sac = VersSacADos.versTypeCout(equipe.getProjets(), budgetEconomique, budgetSocial, budgetEnvironnemental);
            executionSolveur(sac, scanner);
        } catch (Exception e) {
            System.err.println("Erreur (conversion vers Sac à Dos) : " + e.getMessage());
        }
    }

    public static void executionSolveur(SacADos sac, Scanner scanner) {
        System.out.println("== Menu - Choix du solveur ==");
        System.out.println("1. Méthode Gloutonne par Ajout (Tri par \"Somme\" / f_Somme)");
        System.out.println("2. Méthode Gloutonne par Ajout (Tri par \"Max\") / f_Max");
        System.out.println("3. Méthode Gloutonne par Retrait (Retrait & Ajout par \"Somme\" / f_Somme)");
        System.out.println("4. Méthode Gloutonne par Retrait (Retrait & Ajout par \"Max\") / / f_Max");
        System.out.println("5. Méthode Gloutonne par Retrait PERSONNALISÉE");
        System.out.println("6. HIll Climbing");

        int choix = -1;
        try {
            String ligne = scanner.nextLine().trim();
            if (!ligne.isEmpty()) {
                choix = Integer.parseInt(ligne);
            }
        } catch (NumberFormatException e) {
            System.err.println("Erreur (Type de l'entrée) : " + e.getMessage());
        }

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
            case 5 :
                java.util.Comparator<Objet> comparatorAjout = quelComparateur(scanner, sac, "d'ajout");
                java.util.Comparator<Objet> comparatorRetrait = quelComparateur(scanner, sac, "de retrait");

                System.out.println("Exécution de la méthode par Retrait selon les choix de l'user.");
                solutionS = solverRetrait.methodeParRetrait(sac, comparatorAjout, comparatorRetrait);
                break;
            case 6 :
                System.out.println("Hill Climbing PAS ENCORE IMPLÉMENTÉE");
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
        
        String chemin = scanner.nextLine().trim();

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

    // UTILITAIRE : Choix de l'utilisateur pour le Critere utilise
    private static java.util.Comparator<Objet> quelComparateur(Scanner scanner, SacADos sac, String ajoutOuretrait) {
        System.out.println(" -- Quel critère " + ajoutOuretrait + " souhaitez-vous utiliser ? -- ");
        System.out.println("1. f_Somme (Utilité / Somme des coûts)");
        System.out.println("2. f_Max (Utilité / Max des coûts)");
        System.out.println("3. f_MV (Utilité / Max de Violation du budget)");
        
        int choix = -1;
        try {
            String ligne = scanner.nextLine().trim();
            if (!ligne.isEmpty()) {
                choix = Integer.parseInt(ligne);
            }
        } catch (NumberFormatException e) {
            System.err.println("Erreur (Type de l'entrée) : " + e.getMessage());
        }
        
        switch (choix) {
            case 1 : 
                return new ComparatorfSomme();
            case 2 :
                return new ComparatorfMax();
            case 3 :
                return new ComparatorfMV(sac, sac.getObjets());
            default :
                System.out.println("/////// Choix inconnu, utilisons le critère de f_Somme");
                return new ComparatorfSomme();
        }
    }
}

