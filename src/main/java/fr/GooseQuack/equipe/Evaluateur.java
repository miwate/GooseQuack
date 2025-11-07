package fr.GooseQuack.equipe;

/**
 * Classe d'un évaluateur, sous-classe de Personne.
 * 
 * @author William (miwate)
 * @version 1.0
 */
public class Evaluateur extends Personne {
    
    private final Cout specialisation;

    /**
     * Construit un évaluateur avec sa spécialisation.
     * 
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur à 18)
     * @param specialisation la spécialisation (ne doit pas être null)
     * @throws NullPointerException si le nom, le prénom ou la spécialisation est null
     * @throws IllegalArgumentException si l'âge n'est pas supérieur à 18
     */
    public Evaluateur(String nom, String prenom, int age, Cout specialisation) {
        super(nom, prenom, age);

        if (specialisation == null) {
            throw new NullPointerException("La spécialisation ne peut pas être null");
        }
        this.specialisation = specialisation;
    }

    public Cout getSpecialisation() {
        return specialisation;
    }

    // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    // renseigner le coût économique d'un projet : à faire

}
