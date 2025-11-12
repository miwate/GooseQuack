package fr.GooseQuack.equipe;
import java.util.EnumSet;

/**
 * Classe d'un expert, sous-classe de Personne.
 *
 * @author William (miwate)
 * @version 1.0
 */
public class Expert extends Personne {

    private EnumSet<Secteur> competences;

    /**
     * Construit un expert.
     *
     * @param nom le nom de famille (ne doit pas être null)
     * @param prenom le prénom (ne doit pas être null)
     * @param age l'âge (doit être supérieur à 18)
     * @param competences les secteurs de compétence de l'expert (au moins un)
     */
    public Expert(String nom, String prenom, int age, Secteur... competences) {
        super(nom, prenom, age);

        if (competences.length == 0) {
            throw new IllegalArgumentException("Un expert doit avoir avoir au moins une compétence");
        }

        this.competences = EnumSet.noneOf(Secteur.class);

        for (Secteur secteur : competences) {
            this.competences.add(secteur);
        }
    }

    /**
     * Retourne les compétences de l'expert.
     *
     * @return un EnumSet des secteurs de compétence
     */
    public EnumSet<Secteur> getCompetences() {
        return EnumSet.copyOf(competences);
    }

}
