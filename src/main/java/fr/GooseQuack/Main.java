package fr.GooseQuack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import fr.GooseQuack.solveur.glouton.GloutonRetraitSolver;


public class Main {

    public static void main(String[] args) {
        System.out.println("Running main...");

        // Instances d'exemple :
        Elu elu = new Elu("Gilbert", "Hugo", 34);
        Evaluateur evaluateurEco = new Evaluateur("Gilbart","Huga", 33, Cout.ECONOMIQUE);
        Evaluateur evaluateurSocial = new Evaluateur("Gilbort","Hugues", 32, Cout.SOCIAL);
        Evaluateur evaluateurEnvironnement = new Evaluateur("Gilbirt","Hugwo", 31, Cout.ENVIRONNEMENTAL);
        Expert expertSportSante = new Expert("Gilbertss", "Hugoss", 30, Secteur.SANTE, Secteur.SANTE);
        Expert expertCultureEco= new Expert("Gilbertce", "Hugoce", 31, Secteur.CULTURE, Secteur.ATTRACTIVITE_ECONOMIQUE);

        // Vers l'equipe :
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

        List<Objet> res1 = solver.methodeParRetrait(sac, compSomme, compSomme);
        System.out.println("ComparatorfSomme : " + res1.size() + " objets");
        int util1 = 0;
        for (Objet o : res1) {
            util1 += o.getUtilite();
        }
        System.out.println("Utilité totale : " + util1);

        List<Objet> res2 = solver.methodeParRetrait(sac, compMax, compMax);
        System.out.println("ComparatorfMax : " + res2.size() + " objets");
        int util2 = 0;
        for (Objet o : res2) {
            util2 += o.getUtilite();
        }
        System.out.println("Utilité totale : " + util2);

    }

}
