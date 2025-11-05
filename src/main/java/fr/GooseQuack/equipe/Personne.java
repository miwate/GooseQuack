package fr.GooseQuack.equipe;

import java.util.Objects;

public abstract class Personne {
    
    private final String nom;
    private final String prenom;

    // Pas final puisque l'âge change chaque année
    private int age;

    protected Personne(String _nom, String _prenom, int _age) {
        Objects.requireNonNull(_nom, "Le nom ne peut pas être null");
        this.nom = _nom;

        Objects.requireNonNull(_prenom, "Le nom ne peut pas être null");
        this.prenom = _prenom;

        Objects.requireNonNull(_age, "Le nom ne peut pas être null");
        this.age = _age;
    }
}
