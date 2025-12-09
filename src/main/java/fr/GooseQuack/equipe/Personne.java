package fr.GooseQuack.equipe;

import java.util.Objects;

/**
 * Classe abstraite d'une personne avec son nom, son prénom, et son âge.
 * 
 * @author William (miwate)
 * @author Drys (lidr05)
 * @version 1.0
 */

public abstract class Personne {

    private final String nom;
    private final String prenom;

    private int age;


    /**
     * Construit une personne
     *
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur ou égal à 18)
     * @throws NullPointerException si le nom ou le prénom est null
     * @throws IllegalArgumentException si l'âge est inférieur à 18
     */
    protected Personne(String nom, String prenom, int age) {
        Objects.requireNonNull(nom, "Le nom ne peut pas être null");
        this.nom = nom;

        Objects.requireNonNull(prenom, "Le prénom ne peut pas être null");
        this.prenom = prenom;

        if (age < 18) {
            throw new IllegalArgumentException("L'âge doit être >= 18");
        }
        this.age = age;
    }

    /**
     * Donne le nom de famille
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Donne le prénom
     *
     * @return le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Donne l'âge
     *
     * @return l'âge
     */
    public int getAge() {
        return age;
    }

    /**
     * Met à jour l'âge de la personne
     *
     * @param age l'âge (doit être supérieur ou égal à 18)
     * @throws IllegalArgumentException si l'âge est inférieur à 18
     */
    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("L'âge doit être >= 18");
        }
        this.age = age;
    }

    /**
     * Vérifie l'égalité entre deux personnes
     *
     * @param o l'objet à comparer
     * @return true si les personnes ont le même nom, prénom et âge, false sinon
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personne personne = (Personne) o;
        return age == personne.age && java.util.Objects.equals(nom, personne.nom) && java.util.Objects.equals(prenom, personne.prenom);
    }

    /**
     * Donne le hash d'une personne
     *
     * @return le code de hash à l'aide du nom, du prénom et de l'âge
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(nom, prenom, age);
    }
}
