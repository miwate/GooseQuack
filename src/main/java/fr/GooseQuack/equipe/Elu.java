package fr.GooseQuack.equipe;

import java.util.Random;

/**
 * Classe d'un élu, sous-classe de Personne.
 * 
 * @author William (miwate)
 * @author Drys (lidr05)
 * @version 1.0
 */
public class Elu extends Personne {
    // Nombre aleatoire
    private final Random random;

    // Parametres arbitraires
    private static final int MIN_BENEFICE = 10;
    private static final int MAX_BENEFICE = 1000;
    
    /**
     * Construit un élu.
     *
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur ou égal à 18)
     * @throws NullPointerException si le nom ou le prénom est null
     * @throws IllegalArgumentException si l'âge est inférieur à 18
     */
    public Elu(String nom, String prenom, int age) {
        super(nom, prenom, age);
        this.random = new Random();
    }

    /**
     * Évalue et attribue un bénéfice aléatoire à un projet
     *
     * @param projet le projet à évaluer (ne doit pas être null)
     * @throws NullPointerException si le projet est null
     */
    public void evaluerBenefice(Projet projet) {
        if (projet == null) {
            throw new NullPointerException("Le projet ne peut pas être null");
        }

        // Stochastique (aleatoire)
        int benefice = random.nextInt(MAX_BENEFICE - MIN_BENEFICE + 1) + MIN_BENEFICE;
        projet.setBenefice(benefice);
    }
}
