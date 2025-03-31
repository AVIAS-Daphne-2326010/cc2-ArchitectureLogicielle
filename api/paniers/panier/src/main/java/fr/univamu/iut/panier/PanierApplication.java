package fr.univamu.iut.panier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class PanierApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données pour les paniers
     * @return un objet implémentant l'interface PanierRepositoryInterface pour accéder aux données des paniers
     */
    @Produces
    private PanierRepositoryInterface openDbConnection() {
        PanierRepositoryMariadb db = null;
        try {
            db = new PanierRepositoryMariadb("jdbc:mariadb://mysql-pub_admin_coop.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param panierRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes PanierRepositoryInterface panierRepo) {
        panierRepo.close();
    }

    /**
     * Méthode produisant le repository pour la gestion des compositions de paniers
     * @return un objet implémentant l'interface CompoPanierRepositoryInterface pour accéder aux données des compositions de paniers
     */
    @Produces
    private CompoPanierRepositoryInterface openCompoDbConnection() {
        CompoPanierRepositoryMariadb compoDb = null;
        try {
            compoDb = new CompoPanierRepositoryMariadb("jdbc:mariadb://mysql-pub_admin_coop.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return compoDb;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données pour les compositions de paniers
     * @param compoRepo la connexion à la base de données instanciée dans la méthode @openCompoDbConnection
     */
    private void closeCompoDbConnection(@Disposes CompoPanierRepositoryInterface compoRepo) {
        compoRepo.close();
    }
}
