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
        return elu;
    }
    public Evaluateur getEvaluateur(Cout cout) {
        Objects.requireNonNull(cout, "Le cout ne peut pas être null");
        return evaluateurs[cout.ordinal()];
    }
    public Evaluateur[] getEvaluateurs() {
        return evaluateurs;
    }

    // Setters
    public void setElu(Elu elu) {
        Objects.requireNonNull(elu, "L'élu ne peut pas être null");
        this.elu = elu;
    }

    // Methodes
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
            throw new IllegalArgumentException("Cet expert existe déjà.");
        }

        this.experts.add(expert);
    }
}
