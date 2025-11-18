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
     * ArrayList des experts (taille dynamite)
     */
    private final List<Expert> experts;

    /** 
     * ArrayList des projets (taille dynamite)
     */
    private final List<Projet> projets;


    /**
     * 
     */
    public EquipeMunicipale() {
        this.evaluateurs = new Evaluateur[Cout.nbTypes()];
        this.experts = new ArrayList<Expert>();
        this.projets = new ArrayList<Projet>();
    }

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


    public void setElu(Elu elu) {
        Objects.requireNonNull(elu, "L'élu ne peut pas être null");
        this.elu = elu;
    }

    public void addEvaluateur(Evaluateur evaluateur) {
        Objects.requireNonNull(evaluateur, "L'évaluateur ne peut pas être null");

        int index = evaluateur.getSpecialisation().ordinal();

        
        if (evaluateurs[index] != null) {
            throw new IllegalStateException("Un évaluateur pour la spécialisation " + evaluateur.getSpecialisation().getNom() + " existe déjà");
        }

        evaluateurs[index] = evaluateur;
    }

}
