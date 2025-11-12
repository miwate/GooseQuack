package fr.GooseQuack.equipe;

/**
 * Classe d'un élu, sous-classe de Personne.
 * 
 * @author William (miwate)
 * @version 1.0
 */
public class Elu extends Personne {
    
    /**
     * Construit un élu.
     * 
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur à 18)
     */
    public Elu(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public int evaluerBenefice(Projet projet) {
        if (projet == null) {
            throw new NullPointerException("Le projet ne peut pas être null");
        }

        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        // je mets 1000 parce que je sais pas quoi mettre d'autre pour l'instant
        int benefice = 1000;

        return benefice;
    }
}
