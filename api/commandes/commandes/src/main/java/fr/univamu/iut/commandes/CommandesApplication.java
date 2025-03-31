package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandesApplication extends Application {

    @Produces
    @ApplicationScoped
    public CommandesRepositoryInterface openDbConnection() {
        try{
            return new CommandesRepositoryMariadb(
                    "jdbc:mariadb://mysql-pub_admin_coop.alwaysdata.net/pub_admin_coop_library_db",
                    "pub_cooperative",
                    "admin_coop");
        } catch (Exception e){
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void closeDbConnection(@Disposes CommandesRepositoryInterface commandesRepo ) {
        commandesRepo.close();
    }

}
