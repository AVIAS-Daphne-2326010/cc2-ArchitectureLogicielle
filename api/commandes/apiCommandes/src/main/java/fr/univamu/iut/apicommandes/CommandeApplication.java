package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe principale de configuration de l'application REST pour la gestion des commandes.
 * Configure le point d'entrée de l'API et gère le cycle de vie du repository.
 *
 * <p>Cette classe étend {@link Application} pour définir le chemin de base de l'API
 * et utilise CDI pour la gestion des dépendances.</p>
 *
 * @see Application
 * @see ApplicationScoped
 */
@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

    /**
     * Crée et configure une instance du repository PostgreSQL.
     *
     * <p>Cette méthode est annotée avec {@link Produces} pour permettre à CDI
     * d'injecter le repository dans les autres composants de l'application.</p>
     *
     * @return Une instance implémentant {@link CommandeRepositoryInterface}
     * @throws IllegalStateException Si la création du repository échoue
     *
     * @see Produces
     * @see CommandeRepositoryPostgres
     */
    @Produces
    public CommandeRepositoryInterface createRepository() {
        try {
            CommandeRepositoryPostgres repo = new CommandeRepositoryPostgres(
                    "jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative",
                    "pub_admin_coop",
                    "admin_coop");
            System.out.println("Création réussie du repository PostgreSQL");
            return repo;
        } catch (Exception e) {
            System.err.println("Échec création repository: " + e.getMessage());
            throw new IllegalStateException("Échec création repository", e);
        }
    }

    /**
     * Ferme proprement le repository lorsqu'il n'est plus nécessaire.
     *
     * <p>Cette méthode est appelée automatiquement par CDI grâce à l'annotation
     * {@link Disposes} lorsque le contexte est détruit.</p>
     *
     * @param repo Le repository à fermer
     *
     * @see Disposes
     * @see AutoCloseable
     */
    public void closeRepository(@Disposes CommandeRepositoryInterface repo) {
        System.out.println("Fermeture du repository");
        try {
            if (repo != null) {
                repo.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur fermeture repository: " + e.getMessage());
        }
    }
}