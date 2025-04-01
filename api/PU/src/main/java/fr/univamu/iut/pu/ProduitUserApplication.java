package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * La classe {@code ProduitUserApplication} configure les connexions aux bases de données pour les produits et les utilisateurs.
 * Elle étend {@code Application} pour exposer des ressources REST sous le chemin "/api" et fournit des méthodes
 * pour produire des instances des dépôts de produits et d'utilisateurs. Elle gère également la fermeture des connexions
 * aux bases de données lorsqu'elles ne sont plus nécessaires.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class ProduitUserApplication extends Application {

    /**
     * Produit une instance de {@code ProduitRepositoryPostgres}, qui est utilisée pour accéder aux données des produits.
     * Cette méthode crée une connexion à la base de données PostgreSQL pour les produits.
     *
     * @return Une instance de {@code ProduitRepositoryInterface} pour interagir avec les données des produits.
     */
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

    /**
     * Ferme la connexion à la base de données pour les produits lorsque l'instance de {@code ProduitRepositoryInterface}
     * n'est plus nécessaire.
     *
     * @param produitRepo Le dépôt des produits à fermer.
     */
    private void closeDbConnection(@Disposes ProduitRepositoryInterface produitRepo) {
        produitRepo.close();
    }

    /**
     * Produit une instance de {@code UserRepositoryPostgres}, qui est utilisée pour accéder aux données des utilisateurs.
     * Cette méthode crée une connexion à la base de données PostgreSQL pour les utilisateurs.
     *
     * @return Une instance de {@code UserRepositoryInterface} pour interagir avec les données des utilisateurs.
     */
    @Produces
    public UserRepositoryInterface openDbConnectionUsers() {
        UserRepositoryPostgres db = null;
        try {
            db = new UserRepositoryPostgres("jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données pour les utilisateurs : " + e.getMessage());
        }

        return db;
    }

    /**
     * Ferme la connexion à la base de données pour les utilisateurs lorsque l'instance de {@code UserRepositoryInterface}
     * n'est plus nécessaire.
     *
     * @param produitRepo Le dépôt des utilisateurs à fermer.
     */
    private void closeDbConnectionUsers(@Disposes UserRepositoryInterface produitRepo) {
        produitRepo.close();
    }
}
