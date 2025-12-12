package fr.GooseQuack.solveur;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.GooseQuack.sacados.Objet;
import fr.GooseQuack.sacados.SacADos;
import fr.GooseQuack.solveur.glouton.ComparatorfSomme;
import fr.GooseQuack.solveur.glouton.GloutonAjoutSolver;


/**
 * Classe pour l'algorithme Hill Climbing.
 * 
 * @author 
 * 
 * @version 1.0
 */

public class HillClimbingSolver {
    private int f_S(List<Objet> S) { // avec S obtenue par gluton 
        int somme_utilite = 0;
        for (Objet o : S) {
            somme_utilite += o.getUtilite();
        }
        return somme_utilite; // renvoie l'utilité totale de S 
    }

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

    public List<Objet> solve (SacADos sac, List<Objet> sol_initiale){ // sol_initiale : sol par gluton 
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
    public static void main (String[] args ){

        List<Objet> liste_objets= new ArrayList<>();
        int[] budget= {20,15,12};
        Objet o1 = new Objet(10, new int [] {5,3,2});
        Objet o2 = new Objet(12, new int [] {6,4,3});
        Objet o3 = new Objet(20, new int [] {10,7,4});
        Objet o4 = new Objet(15, new int [] {8,2,1});
        liste_objets.add(o1);
        liste_objets.add(o2);
        liste_objets.add(o3);
        liste_objets.add(o4);
        SacADos mysac = new SacADos(3, budget,liste_objets);

        GloutonAjoutSolver glouton= new GloutonAjoutSolver();
        Comparator<Objet> comp= new ComparatorfSomme();
        List<Objet> sol_0= glouton.MethodeParAjout(mysac, comp);
        System.out.println("la solution initiale est :"+ sol_0);

        HillClimbingSolver hc= new HillClimbingSolver();
        List<Objet> sol_finale= hc.solve(mysac, sol_0);
        System.out.println("la solution finale est :"+ sol_finale);    
    };
    
}// class

    









