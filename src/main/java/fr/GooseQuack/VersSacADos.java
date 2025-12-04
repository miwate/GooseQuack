package fr.GooseQuack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.GooseQuack.equipe.Projet;
import fr.GooseQuack.equipe.Secteur;
import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;

/**
 * Classe pour convertir des projets en sac à dos
 * 
 * @author Drys (lidr05)
 * @author William (miwate)
 * 
 * @version 1.0
 */
public class VersSacADos {
    
    /**
     * @param projets
     * @param budgetEconomique
     * @param budgetSocial
     * @param budgetEnvironnemental
     * @return
     */
    public static SacADos versTypeCout(List<Projet> projets, int budgetEconomique, int budgetSocial, int budgetEnvironnemental) {
        
        if (projets == null || projets.isEmpty()) {
            throw new IllegalArgumentException("La liste des projets doit être non null et non vide");
        }

        if (budgetEconomique < 0 || budgetSocial < 0 || budgetEnvironnemental < 0) {
            throw new IllegalArgumentException("Les budgets doivent être positifs");
        }

        int budgets[] = new int[] {budgetEconomique, budgetSocial, budgetEnvironnemental};
        int dimension = budgets.length;

        List<Objet> objets = new ArrayList<>();

        for (Projet p : projets) {
            if (p == null) {
                throw new IllegalArgumentException("Chaque projet de la liste doit être non null");
            }

            int utilite = p.getBenefice();

            int couts[] = p.getCouts();

            objets.add(new Objet(utilite, couts));
        }

        return new SacADos(dimension, budgets, objets);
    }


    /**
     * @param projets
     * @param budgetSport
     * @param budgetSante
     * @param budgetCulture
     * @param budgetEducation
     * @param budgetAttractivite
     * @return
     */
    public static SacADos versSecteur(List<Projet> projets, int budgetSport, int budgetSante, int budgetCulture, int budgetEducation, int budgetAttractivite) {

        if (projets == null || projets.isEmpty() ) {
            throw new IllegalArgumentException("La liste des projets doit être non null et non vide");
        }

        if (budgetSport < 0 || budgetSante < 0 || budgetCulture < 0 || budgetEducation < 0 || budgetAttractivite < 0) {
            throw new IllegalArgumentException("Tous les budgets doivent être positifs");
        }

        int[] budgets = new int[] {budgetSport, budgetSante, budgetCulture, budgetEducation, budgetAttractivite};
        int dimension = budgets.length;

        List<Objet> objets = new ArrayList<>();

        for (Projet p : projets) {
            if (p == null) {
                throw new IllegalArgumentException("Chaque projet de la liste doit être non null");
            }

            int utilite = p.getBenefice();

            int[] couts = new int[dimension];
            int indiceSecteur = getIndiceSecteur(p.getSecteur());
            couts[indiceSecteur] = p.getCout(fr.GooseQuack.equipe.Cout.ECONOMIQUE);

            objets.add(new Objet(utilite, couts));
        }

        return new SacADos(dimension, budgets, objets);
    }

    /**
     * @param secteur
     * @return
     */
    private static int getIndiceSecteur(Secteur secteur) {
        if (secteur == null) {
            throw new IllegalArgumentException("Le secteur doit être non null");
        }

        switch (secteur) {
            case SPORT:
                return 0;
            case SANTE:
                return 1;
            case CULTURE:
                return 2;
            case EDUCATION:
                return 3;
            case ATTRACTIVITE_ECONOMIQUE:
                return 4;
            default:
                throw new IllegalArgumentException("Le secteur est inconnu : " + secteur); 
        }

    }

    /**
     * @param pathFichier
     * @return
     * @throws IOException
     */
    public static SacADos loadFichier(String pathFichier) throws IOException {
        if (pathFichier == null || pathFichier.trim().isEmpty()) {
            throw new IllegalArgumentException("Le path du fichier doit être non null");
        }

        // Lecture du fichier
        try (BufferedReader br = new BufferedReader(new FileReader(pathFichier))) {

            // La première ligne c'est : [n nombre d'objets] [k nombe de budgets] [v valeur optimale (0 si unknown)]
            String[] nkv = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(nkv[0]);
            int k = Integer.parseInt(nkv[1]);
            int v = Integer.parseInt(nkv[2]);
            
            if (n <= 0 || k <= 0) {
                throw new IllegalArgumentException("n et k doivent être strictement positifs");
            }

            if (v < 0) {
                throw new IllegalArgumentException("v doit être positif");
            }

            // Deuxième ligne : [] fois n utilités
            String[] utilitesStrings = br.readLine().trim().split("\\s+");

            if (utilitesStrings.length != n) {
                throw new IllegalArgumentException("Le nombre d'utilités doit être de " + n);
            }

            int[] utilites = new int[n];
            for (int l = 0; l < n; l++) {
                utilites[l] = Integer.parseInt(utilitesStrings[l]);
            }

            // Le reste sauf dernière ligne : coûts par dimension
            int[][] matriceCouts = new int[k][n];

            for (int l = 0; l < k; l++) {
                String ligne = br.readLine();
                
                if (ligne == null) {
                    throw new IllegalArgumentException("Taille de la matrice des coûts incorrect");
                }

                String[] coutsStrings = ligne.trim().split("\\s+");
                
                if (coutsStrings.length != n) {
                    throw new IllegalArgumentException("Taille de la matrice des coûts incorrect");
                }

                for (int j = 0; j < n; j++) {
                    matriceCouts[l][j] = Integer.parseInt(coutsStrings[j]);
                }
            }

            // Dernière ligne c'est : [] fois k budgets
            String lastLigne = br.readLine();
            if (lastLigne == null) {
                throw new IllegalArgumentException("Ligne des budgets manquante");
            }

            String[] budgetsStrings = lastLigne.trim().split("\\s+");

            if (budgetsStrings.length != k) {
                throw new IllegalArgumentException("Nombre de budgets incorrect");
            }

            int[] budgets = new int[k];

            for (int l = 0; l < k; l++) {
                budgets[l] = Integer.parseInt(budgetsStrings[l]);

                if (budgets[l] < 0) {
                    throw new IllegalArgumentException("Budget négatif à la dimension " + l);
                }
            }

            // Construction des objects
            List<Objet> objets = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                int[] coutsObjet = new int[k];
                
                for (int l = 0; l < k; l++) {
                    coutsObjet[l] = matriceCouts[l][i];
                }
                objets.add(new Objet(utilites[i], coutsObjet));
            }
            return new SacADos(k, budgets, objets);

        }

    }


    




}
