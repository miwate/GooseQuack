package fr.GooseQuack.equipe;

import java.util.Objects;

/**
 * Classe abstraite d'une personne avec son nom, son prénom, et son âge.
 * Les personnes doivent être majeures (>= 18 ans) pour exercer un métier.
 * 
 * @author William (miwate)
 * @version 1.0
 */

public abstract class Personne {
    
    private final String nom;
    private final String prenom;

    private int age;


    /**
     * Construit une Personne
     * 
     * @param _nom le nom de famille (ne doit pas être null)
     * @param _prenom le prénom (ne doit pas être null)
     * @param _age l'âge (doit être supérieur à 18)
     * @throws NullPointerException si le nom ou le prénom est null
     * @throws IllegalArgumentException si l'âge n'est pas supérieur à 18
     */
    protected Personne(String _nom, String _prenom, int _age) {
        Objects.requireNonNull(_nom, "Le nom ne peut pas être null");
        this.nom = _nom;

        Objects.requireNonNull(_prenom, "Le prénom ne peut pas être null");
        this.prenom = _prenom;

        if (_age < 18) {
            throw new IllegalArgumentException("L'âge doit être >= 18");
        }
        this.age = _age;
    }

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
     * @param _age l'âge (doit être supérieur à 18)
     * @throws IllegalArgumentException si l'âge n'est pas supérieur à 18
     */
    public void setAge(int _age) {
        if (_age < 18) {
            throw new IllegalArgumentException("L'âge doit être >= 18");
        }
        this.age = _age;
    }
}
