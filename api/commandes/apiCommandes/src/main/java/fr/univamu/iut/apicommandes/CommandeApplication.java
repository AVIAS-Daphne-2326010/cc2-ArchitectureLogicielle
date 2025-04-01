package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

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