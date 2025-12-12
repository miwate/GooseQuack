package fr.GooseQuack;

import java.io.IOException;
import java.util.ArrayList;
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
import fr.GooseQuack.sacados.SacADosCalculs;
import fr.GooseQuack.solveur.glouton.ComparatorfMV;
import fr.GooseQuack.solveur.glouton.ComparatorfMax;
import fr.GooseQuack.solveur.glouton.ComparatorfSomme;
import fr.GooseQuack.solveur.glouton.GloutonAjoutSolver;
import fr.GooseQuack.solveur.glouton.GloutonRetraitSolver;

/**
 * Classe : Main.
 * 
 * @author Drys (lidr05)
 * 
 * @version 1.0
 */

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
                        System.out.println("{1} Simulation d'une équipe municipale PAR DÉFAUT");
                        System.out.println("{2} Simulation d'une équipe municipale PAR DÉFAUT (Budget PERSONNALISÉ)");
                        System.out.println("{3} Simulation d'une équipe municipale PERSONNALISÉ");

                        int parDefOuPerso = -1;
                        try {
                            String entreeDefOuPerso = scanner.nextLine().trim();
                            if (!entreeDefOuPerso.isEmpty()) {
                                parDefOuPerso = Integer.parseInt(entreeDefOuPerso);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Erreur (Type de l'entrée) : " + e.getMessage());
                        }
                        switch (parDefOuPerso) {
                            case 1 :
                                simulationEquipe(scanner);
                                break;
                            case 2 : 
                                simulationEquipeBudgetPerso(scanner);
                                break;
                            case 3 : 
                                simulationEquipePerso(scanner);
                                break;
                            default :
                                System.out.println("Veuillez saisir un nom correspondant aux choix disponibles (1 à 3) !");
                        }

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

    // Choix 1.1 : Simulation par défaut (budget default)
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
        
        equipe.cycleSimulation();
        System.out.println("Nombre de projets : " + equipe.getProjets().size());

        // Conversion Vers Sac A Dos : PERSONNALISATION DU BUDGET 
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

    // Choix 1.2 : Simulation par défaut (budget PERSO)
    public static void simulationEquipeBudgetPerso(Scanner scanner) {
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
        
        equipe.cycleSimulation();
        System.out.println("Nombre de projets : " + equipe.getProjets().size());

        // Conversion Vers Sac A Dos : PERSONNALISATION DU BUDGET 
        System.out.println("Création des budgets...");
        int budgetEconomique = entier(scanner, "le budget économique");
        int budgetSocial = entier(scanner, "le budget social");
        int budgetEnvironnemental = entier(scanner, "le budget environnemental");
        System.out.println("\tBudgets :\nEconomique : " + budgetEconomique + "\nSocial : " + budgetSocial + "\nEnvironnemental : " + budgetEnvironnemental);

        try {
            SacADos sac = VersSacADos.versTypeCout(equipe.getProjets(), budgetEconomique, budgetSocial, budgetEnvironnemental);
            executionSolveur(sac, scanner);
        } catch (Exception e) {
            System.err.println("Erreur (conversion vers Sac à Dos) : " + e.getMessage());
        }
    }

    // Choix 1.3 : Simulation d'une équipe PERSONNALISÉE
    public static void simulationEquipePerso(Scanner scanner) {
        System.out.println("CRÉATION DE L'ÉQUIPE :");
        EquipeMunicipale equipe = new EquipeMunicipale();

        try {
            // 1. Création de l'élu
            System.out.println("-- Création de l'élu :");
            String nomElu = texte(scanner, "son nom");
            String prenomElu = texte(scanner, "son prénom");
            int ageElu = entier(scanner, "son âge");
            Elu elu = new Elu(nomElu, prenomElu, ageElu);
            equipe.setElu(elu);
            
            // 2. Création des Evaluateurs
            System.out.println("-- Création des Évaluateurs :");

            // Evaluateur économique :
            System.out.println("---- Création de l'évaluateur économique :");
            equipe.addEvaluateur(new Evaluateur(
                texte(scanner, "le nom de l'évaluateur économique"), 
                texte(scanner, "son prénom"), 
                entier(scanner, "son âge"),
                Cout.ECONOMIQUE
                )
            );

            // Evaluateur social :
            System.out.println("---- Création de l'évaluateur social :");
            equipe.addEvaluateur(new Evaluateur(
                texte(scanner, "le nom de l'évaluateur social"), 
                texte(scanner, "son prénom"), 
                entier(scanner, "son âge"),
                Cout.SOCIAL
                )
            );

            // Evaluateur environnemental :
            System.out.println("---- Création de l'évaluateur environnemental :");
            equipe.addEvaluateur(new Evaluateur(
                texte(scanner, "le nom de l'évaluateur environnemental"), 
                texte(scanner, "son prénom"), 
                entier(scanner, "son âge"),
                Cout.ENVIRONNEMENTAL    
                )
            );

            // 3. Création des Experts
            System.out.println("-- Création des Experts :");
            boolean ajoutExperts = true;

            while (ajoutExperts) {
                System.out.println("---- Nouvel Expert : ");

                // D'abord les secteurs :
                List<Secteur> secteursExpert = new ArrayList<>();
                boolean ajoutSecteurs = true;
                System.out.println("Choix des compétences de l'expert : ");

                while (ajoutSecteurs) {
                    Secteur secteur = quelSecteur(scanner);
                    if (secteur != null && !secteursExpert.contains(secteur)) {
                        secteursExpert.add(secteur);
                        System.out.println("\t Secteur ajouté !");
                    }
                    else if (secteur == null) {
                        System.out.println("\t Secteur invalide.");
                    }
                    else {
                        System.out.println("\t Secteur déjà existant.");
                    }
                    
                    System.out.println("\t Souhaitez-vous ajouter un secteur pour cet expert ? (entrer N pour refuser)");
                    String nouvSectOuiNon = scanner.nextLine().trim();
                    if (nouvSectOuiNon.equalsIgnoreCase("n")) {
                        ajoutSecteurs = false;
                    } 
                }

                Secteur[] secteursExpertArray = secteursExpert.toArray(new Secteur[0]);
                equipe.addExpert(new Expert(
                    texte(scanner, "le nom de l'expert"), 
                    texte(scanner, "son prénom"), 
                    entier(scanner, "son âge"),
                    secteursExpertArray
                    )
                );

                System.out.println("\t Souhaitez-vous ajouter nouvel expert ? (entrer N pour refuser)");
                String nouvExpertOuiNon = scanner.nextLine().trim();
                if (nouvExpertOuiNon.equalsIgnoreCase("n")) {
                    ajoutExperts = false;
                } 
            }
        
        } catch (Exception e) {
            System.err.println("Erreur (création de l'équipe) : " + e.getMessage());
            return;
        }

        equipe.cycleSimulation();
        System.out.println("Nombre de projets : " + equipe.getProjets().size());

        // Conversion Vers Sac A Dos : PERSONNALISATION DU BUDGET 
        System.out.println("Création des budgets...");
        int budgetEconomique = entier(scanner, "le budget économique");
        int budgetSocial = entier(scanner, "le budget social");
        int budgetEnvironnemental = entier(scanner, "le budget environnemental");
        System.out.println("==== Budgets : ====\nEconomique : " + budgetEconomique + "\nSocial : " + budgetSocial + "\nEnvironnemental : " + budgetEnvironnemental);
        System.err.println("");

        try {
            SacADos sac = VersSacADos.versTypeCout(equipe.getProjets(), budgetEconomique, budgetSocial, budgetEnvironnemental); 
            executionSolveur(sac, scanner);
        } catch (Exception e) {
            System.err.println("Erreur (conversion vers Sac à Dos) : " + e.getMessage());
        }
    
    }



     // UTILITAIRE POUR 1.3
    public static String texte(Scanner scanner, String str) {
        System.out.println("\t Entrez " + str + " : ");
        String text = scanner.nextLine().trim();
        while (text.isEmpty()) {
            System.out.println("Le texte ne peut pas être vide. Recommencez.");
            text = scanner.nextLine().trim();
        }
        return text;
    }

    public static Secteur quelSecteur(Scanner scanner) {
        System.out.println("\tListe des Secteurs :");
        System.out.println("1. SPORT");
        System.out.println("2. SANTE");
        System.out.println("3. CULTURE");
        System.out.println("4. EDUCATION");
        System.out.println("5. ATTRACTIVITE ECONOMIQUE");

        String entree = scanner.nextLine().trim();
        try {
            int choix = Integer.parseInt(entree);
            switch (choix) {
                case 1 :
                    return Secteur.SPORT;
                case 2 :
                    return Secteur.SANTE;
                case 3 :
                    return Secteur.CULTURE;
                case 4 :
                    return Secteur.EDUCATION;
                case 5 :
                    return Secteur.ATTRACTIVITE_ECONOMIQUE;
                default :
                    return null;
            }
        } catch (Exception e) {
            return null;
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
                System.out.println("== Exécution : Hill Climbing");
                System.out.println("==== Génération de la solution initiale... (par MAX) ====");
                
                // Génération de la solution initiale
                GloutonAjoutSolver solutionAjout = new GloutonAjoutSolver();
                ComparatorfMax comparatorMax = new ComparatorfMax();
                List<Objet> solutionInitiale = solutionAjout.MethodeParAjout(sac, comparatorMax);
                int utiliteInitiale = SacADosCalculs.sommeUtilite(solutionInitiale);

                System.out.println("Solution initiale créée.");

                // Hill Climbing    
                System.out.println("\tLancement de l'algorithme Hill Climbing...");
                HillClimbingSolver hillClimbing = new HillClimbingSolver();

                solutionS = hillClimbing.solve(sac, solutionInitiale);
                int utiliteS = SacADosCalculs.sommeUtilite(solutionS);

                int gain = utiliteS - utiliteInitiale;
                if (gain > 0) {
                    System.out.println("--> Amélioration de +" + gain + " !");
                }
                else {
                    System.out.println("--> Aucune amélioration, maximum déjà atteint.");
                }

                break;
            default :
                System.out.println("Méthode non Implémentée.");
                return;
        }
    

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

    
    // UTILITAIRE : Choix de l'utilisateur pour le Critere utilise
    private static int entier(Scanner scanner, String infoVoulue) {
        int valeur = 0;
        boolean valeurPositive = false;

        System.out.println("\tEntrez " + infoVoulue + " : ");

        while (valeurPositive == false) {
            String input = scanner.nextLine().trim();
            try {
                valeur = Integer.parseInt(input);
                if (valeur > 0) {
                    valeurPositive = true;
                }
                else {
                    System.out.println(" **** Entrez un budget POSITIF !!! **** ");
                }
            } catch (NumberFormatException e) {
                    System.out.println(" **** Entrez un budget ENTIER !!! **** ");
            }
        }
        return valeur;
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
                System.out.println("/////// Choix inconnu, utilisons le critère de f_Somme (par défaut)");
                return new ComparatorfSomme();
        }
    }
}

