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
     * Construit une Personne
     * 
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur à 18)
     * @throws NullPointerException si le nom ou le prénom est null
     * @throws IllegalArgumentException si l'âge n'est pas supérieur à 18
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

    // Getters
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getAge() {
        return age;
    }

    /**
     * Met à jour l'âge d'une personne (supérieur à 18 ans)
     * 
     * @param age l'âge (doit être supérieur à 18)
     * @throws IllegalArgumentException si l'âge n'est pas supérieur à 18
     */
    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("L'âge doit être >= 18");
        }
        this.age = age;
    }

}
