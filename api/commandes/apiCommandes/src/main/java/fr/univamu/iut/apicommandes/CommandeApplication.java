package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

    @Produces
    @ApplicationScoped
    public CommandeRepositoryInterface createDbConnection() {
        try {
            return new CommandeRepositoryPostgres(
                    "jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative",
                    "pub_admin_coop",
                    "admin_coop");
        } catch (Exception e) {
            throw new RuntimeException("ÉCHEC CONNEXION POSTGRESQL: " + e.getMessage(), e);
        }
    }

    public void closeDbConnection(@Disposes CommandeRepositoryInterface commandeRepo){
        commandeRepo.close();
    }
}