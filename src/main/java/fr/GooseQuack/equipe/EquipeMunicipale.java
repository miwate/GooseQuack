package fr.GooseQuack.equipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe de l'équipe municipale
 * 
 * @author William (miwate)
 * @author Drys (lidr05)
 * @version 1.0
 */
public class EquipeMunicipale {
    

    private Elu elu;

    /**
     * Tableau des 3 évaluateurs (taille fixe)
     */
    private final Evaluateur[] evaluateurs;

    /** 
     * ArrayList des experts (taille dynamique avec List<Objet>)
     */
    private final List<Expert> experts;

    /** 
     * ArrayList des projets (taille dynamique)
     */
    private final List<Projet> projets;

    /**
     * 
     */
    public EquipeMunicipale() {
        this.evaluateurs = new Evaluateur[Cout.nbTypes()]; // 1 evaluateur par type 
        this.experts = new ArrayList<Expert>(); // Nombre indetermine d'experts
        this.projets = new ArrayList<Projet>(); // Nombre indetermine d'experts
    }

    // Getters
    public Elu getElu() {
        return new Elu(elu.getNom(), elu.getPrenom(), elu.getAge()); // Renvoie une copie pour proteger l'elu
    }
    public Evaluateur getEvaluateur(Cout cout) { // L'evaluateur associe a l'index du cout (l'evaluateur du type du cout)
        Objects.requireNonNull(cout, "Le cout ne peut pas être null");
        Evaluateur evaluateur = evaluateurs[cout.ordinal()];

        if (evaluateur == null) {
            throw new IllegalStateException("Aucun évaluateur trouvé pour la spécialisation: " + cout);
        }
        return evaluateur;
    }
    public Evaluateur[] getEvaluateurs() {
        return java.util.Arrays.copyOf(this.evaluateurs, this.evaluateurs.length); // Eviter de retourner la liste pour la securite
    }
    public List<Expert> getExperts() {
        return new java.util.ArrayList<>(this.experts); // Eviter de retourner la liste pour la securite
    }
    public List<Projet> getProjets() {
        return new java.util.ArrayList<>(this.projets); // Eviter de retourner la liste pour la securite
    }

    // Setters
    public void setElu(Elu elu) {
        Objects.requireNonNull(elu, "L'élu ne peut pas être null");
        this.elu = elu;
    }

    // Methodes sur les attributs
    public void addEvaluateur(Evaluateur evaluateur) {
        Objects.requireNonNull(evaluateur, "L'évaluateur ne peut pas être null");

        int index = evaluateur.getSpecialisation().ordinal();
        if (evaluateurs[index] != null) {
            throw new IllegalStateException("Un évaluateur pour la spécialisation " + evaluateur.getSpecialisation().getNom() + " existe déjà");
        }

        evaluateurs[index] = evaluateur;
    }

    public void addExpert(Expert expert) {
        Objects.requireNonNull(expert, "L'expert ne peut pas être null");

        if (this.experts.contains(expert)) { // Refuser les doublons
            throw new IllegalArgumentException("L'expert" + expert.getNom() + "existe déjà.");
        }

        this.experts.add(expert);
    }

    // Methodes - Actions a proprement parler
    public void cycleSimulation(EquipeMunicipale equipe){
        for (Expert expert : equipe.experts) {
            expert.propositionSecteur(expert.getTitreProp(), expert.getDescProp(), expert.getCompetence());
        }
    }

}
