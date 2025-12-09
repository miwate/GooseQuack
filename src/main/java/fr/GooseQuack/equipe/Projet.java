package fr.GooseQuack.equipe;

import java.util.Objects;


/**
 * Classe d'un projet avec son titre, sa description, son secteur, son bénéfice et ses coûts.
 *
 * @author William (miwate)
 * @author Drys (lidr05)
 * @author Christiab (christianlikq-del)
 * @version 1.0
 */
public class Projet {

    private final String titre;
    private final String description;
    private final Secteur secteur;

    private int benefice;
    private final int[] couts;

    /**
     * Construit un projet
     *
     * @param titre le titre du projet
     * @param description la description du projet
     * @param secteur le secteur du projet
     * @throws NullPointerException si le titre, la description ou le secteur est null
     */
    public Projet(String titre, String description, Secteur secteur) {

        Objects.requireNonNull(titre, "Le titre ne peut pas être null");
        this.titre = titre;

        Objects.requireNonNull(description, "La description ne peut pas être null");
        this.description = description;

        Objects.requireNonNull(secteur, "Le secteur ne peut pas être null");
        this.secteur = secteur;

        this.benefice = 0;
        
        this.couts = new int[Cout.nbTypes()];
    }

    /**
     * Donne le titre du projet
     *
     * @return le titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Donne la description du projet
     *
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Donne le secteur du projet
     *
     * @return le secteur
     */
    public Secteur getSecteur() {
        return secteur;
    }

    /**
     * Donne le bénéfice du projet
     *
     * @return le bénéfice
     */
    public int getBenefice() {
        return benefice;
    }

    /**
     * Donne le coût pour un type donné
     *
     * @param type le type du coût (économique, social ou environnemental)
     * @return le coût pour ce type
     * @throws NullPointerException si le type est null
     */
    public int getCout(Cout type) {
        Objects.requireNonNull(type, "Le type ne peut pas être null");
        return couts[type.ordinal()];
    }

    /**
     * Donne les coûts du projet
     *
     * @return une copie du tableau des coûts
     */
    public int[] getCouts(){
        return java.util.Arrays.copyOf(this.couts, this.couts.length);
    }

    /**
     * Met à jour le bénéfice du projet
     *
     * @param benefice le nouveau bénéfice (doit être positif)
     * @throws IllegalArgumentException si le bénéfice n'est pas positif
     */
    public void setBenefice(int benefice) {
        if (benefice < 0) {
            throw new IllegalArgumentException("Le bénéfice doit être positif");
        }
        this.benefice = benefice;
    }

    /**
     * Met à jour le coût pour un type donné
     *
     * @param specialisation le type de coût
     * @param valeur la nouvelle valeur du coût (doit être positive ou nulle)
     * @throws NullPointerException si le type est null
     * @throws IllegalArgumentException si la valeur est négative
     */
    public void setCout(Cout specialisation, int valeur) {
        Objects.requireNonNull(specialisation, "Le type de coût (spécialisation) ne peut pas être null");

        if (valeur < 0) {
            throw new IllegalArgumentException("La valeur du coût doit être positive (ou nulle)");
        }

        int index = specialisation.ordinal();
        this.couts[index] = valeur;
    }

}
