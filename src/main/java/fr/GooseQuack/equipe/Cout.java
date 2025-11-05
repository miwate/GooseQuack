package fr.GooseQuack.equipe;

public enum Cout {
    ECONOMIQUE("Économique"),
    SOCIAL("Social"),
    ENVIRONNEMENTAL("Environnemental");

    private final String nom;

    Cout(String _nom) {
        this.nom = _nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    // On en a besoin pour créer le tableau couts dans Projet
    public static int nbTypes() {
        return values().length;
    }
}
