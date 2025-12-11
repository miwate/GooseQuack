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


    private final Evaluateur[] evaluateurs;
    private final List<Expert> experts;
    private final List<Projet> projets;

    /**
     * Construit une équipe municipale vide avec des tableaux pour les évaluateurs, experts et projets
     */
    public EquipeMunicipale() {
        this.evaluateurs = new Evaluateur[Cout.nbTypes()]; // 1 evaluateur par type 
        this.experts = new ArrayList<Expert>(); // Nombre indetermine d'experts
        this.projets = new ArrayList<Projet>(); // Nombre indetermine d'experts
    }

    /**
     * Donne l'élu de l'équipe
     *
     * @return une copie de l'élu
     * @throws NullPointerException si pas d'élu
     */
    public Elu getElu() {
        java.util.Objects.requireNonNull(elu, "L'élu ne peut pas être null");
        return new Elu(elu.getNom(), elu.getPrenom(), elu.getAge()); // Renvoie une copie pour proteger l'elu
    }

    /**
     * Donne l'évaluateur pour un type de coût donné
     *
     * @param cout le type de coût recherché (ne doit pas être null)
     * @return l'évaluateur associé à ce type de coût
     * @throws NullPointerException si le coût est null
     * @throws IllegalStateException si aucun évaluateur n'est trouvé pour cette spécialisation
     */
    public Evaluateur getEvaluateur(Cout cout) {
        Objects.requireNonNull(cout, "Le cout ne peut pas être null");
        Evaluateur evaluateur = evaluateurs[cout.ordinal()];

        if (evaluateur == null) {
            throw new IllegalStateException("Aucun évaluateur trouvé pour la spécialisation: " + cout);
        }
        return evaluateur;
    }

    /**
     * Donne tous les évaluateurs de l'équipe
     *
     * @return une copie du tableau des évaluateurs
     */
    public Evaluateur[] getEvaluateurs() {
        return java.util.Arrays.copyOf(this.evaluateurs, this.evaluateurs.length); // Eviter de retourner la liste pour la securite
    }

    /**
     * Donne la liste des experts de l'équipe
     *
     * @return une copie de la liste des experts
     */
    public List<Expert> getExperts() {
        return new java.util.ArrayList<>(this.experts); // Eviter de retourner la liste pour la securite
    }

    /**
     * Donne la liste des projets de l'équipe
     *
     * @return une copie de la liste des projets
     */
    public List<Projet> getProjets() {
        return new java.util.ArrayList<>(this.projets); // Eviter de retourner la liste pour la securite
    }

    /**
     * Met à jour l'élu de l'équipe
     *
     * @param elu le nouvel élu (ne doit pas être null)
     * @throws NullPointerException si l'élu est null
     */
    public void setElu(Elu elu) {
        Objects.requireNonNull(elu, "L'élu ne peut pas être null");
        this.elu = elu;
    }

    /**
     * Ajoute un évaluateur à l'équipe
     *
     * @param evaluateur l'évaluateur à ajouter (ne doit pas être null)
     * @throws NullPointerException si l'évaluateur est null
     * @throws IllegalStateException si un évaluateur pour sa spécialisation existe déjà
     */
    public void addEvaluateur(Evaluateur evaluateur) {
        Objects.requireNonNull(evaluateur, "L'évaluateur ne peut pas être null");

        int index = evaluateur.getSpecialisation().ordinal();
        if (evaluateurs[index] != null) {
            throw new IllegalStateException("Un évaluateur pour la spécialisation " + evaluateur.getSpecialisation().getNom() + " existe déjà");
        }

        evaluateurs[index] = evaluateur;
    }

    /**
     * Ajoute un expert à l'équipe
     *
     * @param expert l'expert à ajouter (ne doit pas être null)
     * @throws NullPointerException si l'expert est null
     * @throws IllegalArgumentException si l'expert existe déjà dans l'équipe
     */
    public void addExpert(Expert expert) {
        Objects.requireNonNull(expert, "L'expert ne peut pas être null");

        if (this.experts.contains(expert)) { // Refuser les doublons
            throw new IllegalArgumentException("L'expert" + expert.getNom() + "existe déjà.");
        }

        this.experts.add(expert);
    }

    /**
     * Ajoute un projet à l'équipe
     *
     * @param projet le projet à ajouter (ne doit pas être null)
     * @throws NullPointerException si le projet est null
     */
    public void addProjet(Projet projet){
        java.util.Objects.requireNonNull(projet, "Le projet ne peut pas être null");
        this.projets.add(projet);
    }

    // Methodes - Actions a proprement parler
    // Cette ligne a été écrite sur le trône.
    /**
     * Exécute un cycle de simulation complet où chaque expert propose un projet,
     * les évaluateurs estiment les coûts et l'élu évalue le bénéfice
     */
    public void cycleSimulation(){
        if (this.getExperts().isEmpty()) {
            System.out.println("Aucun expert donc aucun projet présent");
            return;
        }
        
        // Parcours de toutes les propositions des experts
        for (Expert expert : this.getExperts()) {
            String titre = "Proposition de " +  expert.getPrenom() + " " + expert.getNom() + " (" + expert.getCompetence().getNom() + ")";
            String description = "Proposition générée pour le secteur " + expert.getCompetence().getNom();
            Projet projetActuel = expert.propositionSecteur(
                titre, description, expert.getCompetence());
        
            // Estimation du cout pour chaque specialisation (Economique, Social et Environnemental)
            for (Evaluateur evaluateur : this.getEvaluateurs()) {
                if (evaluateur != null) {
                    evaluateur.evaluerCout(projetActuel);
                }
            }
            // Evaluation du bénéfice pour le projet
            this.getElu().evaluerBenefice(projetActuel);

            // Ajout dans la liste de projets
            this.addProjet(projetActuel);
            System.out.println("Projet ajouté : " + projetActuel.getTitre());
            System.out.println("\t ---> Description : " + projetActuel.getDescription());
        }
    }   

}
