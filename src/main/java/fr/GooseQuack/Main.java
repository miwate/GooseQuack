package fr.GooseQuack;

import fr.GooseQuack.equipe.Cout;
import fr.GooseQuack.equipe.Elu;
import fr.GooseQuack.equipe.EquipeMunicipale;
import fr.GooseQuack.equipe.Evaluateur;
import fr.GooseQuack.equipe.Expert;
import fr.GooseQuack.equipe.Secteur;


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
    }   

}
