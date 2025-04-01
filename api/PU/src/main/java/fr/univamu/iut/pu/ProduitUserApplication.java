package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class ProduitUserApplication extends Application {

    @Produces
    public ProduitRepositoryInterface openDbConnection() {
        ProduitRepositoryPostgres db = null;
        try {
            db = new ProduitRepositoryPostgres("jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données pour les produits : " + e.getMessage());
        }

        return db;
    }

    private void closeDbConnection(@Disposes ProduitRepositoryInterface produitRepo) {
        produitRepo.close();
    }

    @Produces
    public UserRepositoryInterface openDbConnectionUsers() {
        UserRepositoryPostgres db = null;
        try {
            db = new UserRepositoryPostgres("jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données pour les produits : " + e.getMessage());
        }

        return db;
    }

    private void closeDbConnectionUsers(@Disposes UserRepositoryInterface produitRepo) {
        produitRepo.close();
    }
}
