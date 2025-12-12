package fr.GooseQuack.solveur;
import java.util.ArrayList;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;


/**
 * Classe HillClimbing
 * 
 * @author Christian (christianlikq-del)
 */

public class HillClimbingSolver {

    /**
     * Calcule la somme d'utilité d'une solution S
     * @param S : la solution obtenue par glouton
     * @return la somme d'utilité totale de S
     */
    private int f_S(List<Objet> S) { // avec S obtenue par glouton 
        int somme_utilite = 0;
        for (Objet o : S) {
            somme_utilite += o.getUtilite();
        }
        return somme_utilite; // renvoie l'utilité totale de S
    }

    /**
     * Détermine si le budget est-il respecté
     * @param sac : le sac à dos
     * @param S : une solution
     * @return true si le budget est respecté, false sinon
     */
    private boolean RespectBudgets(SacADos sac, List<Objet> S){
        int dim= sac.getDimension(); 
        int[] liste_budgets= sac.getBudgets();
        int[] cout_total= new int[dim];// on crée une liste de taille 3, indice 0 = cout eco, indi2 = ct social etc
        for (Objet o : S ){
            int[] couts= o.getCouts();
            for (int i = 0;i<dim;i++){
                cout_total[i]+=couts[i];
            } // une liste dont les valeurs = cout total dim eco; cout total dim social etc 
        }
        for (int i = 0;i<dim; i++){
            if(cout_total[i]> liste_budgets[i]){
                return false; // des que va cout total d'une dim depasse va budget alors impossibke
            }
        }
        return true;

    }

    /**
     * Construit une liste de voisins d'une Solution S
     * @param sac : le sac à dos
     * @param S : solution
     * @return la liste des voisins de S
     */
    private List<List<Objet>> liste_voisins(SacADos sac, List<Objet> S){
        List<List<Objet>> liste_voisins = new ArrayList<>(); // liste ou contientra l'ensemble des voisins 
        List<Objet> liste_objets_jouables= sac.getObjets(); // liste des objets ajoutable, supprimmable et echangeable 
        for (Objet o : S){
            List<Objet> S_1 = new ArrayList<>(S);
            S_1.remove(o);
            if(RespectBudgets(sac,S_1)){
                liste_voisins.add(S_1);
            } // S_1 : voisin par remove 
        }
        for (Objet o : liste_objets_jouables){
            if (!S.contains(o)){ // utilise equals redef 
                List<Objet> S_2= new ArrayList<>(S);
                S_2.add(o);
                if (RespectBudgets(sac,S_2)){
                    liste_voisins.add(S_2); // voisin par ajout 
                } // du if 2
                for (Objet i : S){
                     List<Objet> S_3= new ArrayList<>(S);
                    S_3.remove(i);
                    S_3.add(o);
                    if (RespectBudgets(sac,S_3)){
                        liste_voisins.add(S_3); // voisin par echange 
                    }// du if 3
                }// du for 2
            }// du if 1
        }// boucle for 1 
        return liste_voisins;
    }//private liste voisins

    /**
     * Applique l'algorithme prenant une solution S, explore les voisins de S puis retourne une solution optimale
     * @param sac: sac à dos
     * @param sol_initiale : une solution initiale par glouton
     * @return la solution optimale trouvée par Hill Climbing
     */
    public List<Objet> solve (SacADos sac, List<Objet> sol_initiale){ // sol_initiale : sol par glouton 
        List<Objet>  solution = new ArrayList<>(sol_initiale);
        int fdeS = f_S(solution);

        while (true){
            List<List<Objet>> voisins_possibles= liste_voisins(sac,solution);
            if (voisins_possibles.size()==0){ // ie pas de voisins possibles 
                return solution; 
            }
            List <Objet> bestvoisin=voisins_possibles.get(0);// on prends 1e voisin 
            int fdeS_max= f_S(bestvoisin);
            for (int i = 1;i < voisins_possibles.size();i++){
                List <Objet> V = voisins_possibles.get(i);
                int fdeV = f_S(V);
                if (fdeV > fdeS_max){
                    fdeS_max=fdeV;
                    bestvoisin=V;
                }
            }

            if (fdeS_max <= fdeS){
                return solution;
            }
            solution=bestvoisin;
            fdeS=fdeS_max;
        }
    }

}// class









