package fr.univamu.iut.pu;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * La classe {@code ProduitRepositoryPostgres} implémente l'interface {@code ProduitRepositoryInterface}
 * et gère l'accès aux données des produits dans une base de données PostgreSQL.
 * Elle fournit des méthodes pour récupérer, ajouter, supprimer et mettre à jour des produits.
 * Cette classe implémente également l'interface {@code Closeable} pour gérer la fermeture de la connexion à la base de données.
 */
public class ProduitRepositoryPostgres implements ProduitRepositoryInterface, Closeable {

    /**
     * La connexion à la base de données.
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe {@code ProduitRepositoryPostgres}.
     * Initialise la connexion à la base de données PostgreSQL en utilisant les informations de connexion fournies.
     *
     * @param infoConnection L'URL de la base de données.
     * @param user Le nom d'utilisateur pour la connexion à la base de données.
     * @param password Le mot de passe pour la connexion à la base de données.
     * @throws SQLException Si une erreur SQL se produit lors de la connexion.
     * @throws ClassNotFoundException Si le driver PostgreSQL n'est pas trouvé.
     */
    public ProduitRepositoryPostgres(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    /**
     * Ferme la connexion à la base de données.
     * Cette méthode doit être appelée après la fin des opérations sur la base de données.
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère un produit spécifique à partir de son identifiant.
     *
     * @param idProduit L'identifiant du produit à récupérer.
     * @return Un objet {@code Produit} représentant le produit trouvé, ou {@code null} si aucun produit n'est trouvé.
     */
    @Override
    public Produit getProduit(int idProduit) {
        Produit selectedProduit = null;
        String query = "SELECT * FROM produit WHERE id_produit=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idProduit);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String nomProduit = result.getString("nom_produit");
                int quantite = result.getInt("quantite");
                String unite = result.getString("unite");

                selectedProduit = new Produit(idProduit, nomProduit, quantite, unite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduit;
    }

    /**
     * Récupère tous les produits présents dans la base de données.
     *
     * @return Une liste contenant tous les produits sous forme d'objets {@code Produit}.
     */
    @Override
    public ArrayList<Produit> getAllProduits() {
        ArrayList<Produit> listProduits = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int idProduit = result.getInt("id_produit");
                String nomProduit = result.getString("nom_produit");
                int quantite = result.getInt("quantite");
                String unite = result.getString("unite");

                Produit currentProduit = new Produit(idProduit, nomProduit, quantite, unite);
                listProduits.add(currentProduit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduits;
    }

    /**
     * Ajoute un nouveau produit dans la base de données.
     *
     * @param produit L'objet {@code Produit} à ajouter.
     * @return {@code true} si l'ajout a réussi, {@code false} sinon.
     */
    @Override
    public boolean addProduit(Produit produit) {
        String query = "INSERT INTO produit (nom_produit, quantite, unite) VALUES (?, ?, ?)";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, produit.getNomProduit());
            ps.setInt(2, produit.getQuantite());
            ps.setString(3, produit.getUnite());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbRowModified > 0;
    }

    /**
     * Supprime un produit de la base de données en utilisant son identifiant.
     *
     * @param idProduit L'identifiant du produit à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    @Override
    public boolean deleteProduit(int idProduit) {
        String query = "DELETE FROM produit WHERE id_produit=?";
        int nbRowDeleted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idProduit);

            nbRowDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbRowDeleted > 0;
    }

    /**
     * Met à jour les informations d'un produit existant dans la base de données.
     *
     * @param idProduit L'identifiant du produit à mettre à jour.
     * @param nomProduit Le nouveau nom du produit.
     * @param quantite La nouvelle quantité du produit.
     * @param unite La nouvelle unité de mesure du produit.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    @Override
    public boolean updateProduit(int idProduit, String nomProduit, int quantite, String unite) {
        String query = "UPDATE produit SET nom_produit=?, quantite=?, unite=? WHERE id_produit=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nomProduit);
            ps.setInt(2, quantite);
            ps.setString(3, unite);
            ps.setInt(4, idProduit);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbRowModified > 0;
    }
}
