package fr.GooseQuack;

import java.util.ArrayList;
import java.util.List;

import fr.GooseQuack.equipe.Projet;
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
    
    public static SacADos versTypeCout(List<Projet> projets, int budgetEconomique, int budgetSocial, int budgetEnvironnemental) {
        
        if (projets.isEmpty() || projets == null) {
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

}
