package fr.GooseQuack;

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


    




}
