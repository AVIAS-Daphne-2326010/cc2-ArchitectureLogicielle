package fr.univamu.iut.panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface CompoPanierRepositoryInterface pour la gestion des compositions de paniers
 * en utilisant une base de données MariaDB.
 */
public class CompoPanierRepositoryMariadb implements CompoPanierRepositoryInterface {

    private Connection dbConnection;

    /**
     * Constructeur de la classe qui établit une connexion à la base de données.
     *
     * @param infoConnection l'URL de connexion à la base de données
     * @param user le nom d'utilisateur pour la base de données
     * @param pwd le mot de passe pour la base de données
     * @throws SQLException si un problème survient lors de la connexion
     * @throws ClassNotFoundException si le driver de la base de données n'est pas trouvé
     */
    public CompoPanierRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    /**
     * Ajoute un produit dans un panier.
     *
     * @param compo la composition du panier contenant le produit et la quantité
     */
    @Override
    public void ajouterProduit(CompoPanier compo) {
        String query = "INSERT INTO CompoPanier (id_type_panier, id_produit, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, compo.getIdTypePanier());
            ps.setInt(2, compo.getIdProduit());
            ps.setInt(3, compo.getQuantite());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un produit d'un panier.
     *
     * @param idPanier l'identifiant du panier
     * @param idProduit l'identifiant du produit à supprimer
     */
    @Override
    public void supprimerProduit(int idPanier, int idProduit) {
        String query = "DELETE FROM CompoPanier WHERE id_type_panier = ? AND id_produit = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idPanier);
            ps.setInt(2, idProduit);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère la liste des produits contenus dans un panier donné.
     *
     * @param idPanier l'identifiant du panier
     * @return une liste d'objets {@link CompoPanier} contenant les produits et leurs quantités
     */
    @Override
    public List<CompoPanier> getAllProduitsPanier(int idPanier) {
        List<CompoPanier> compoPaniers = new ArrayList<>();
        String query = "SELECT * FROM CompoPanier WHERE id_type_panier = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idPanier);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompoPanier compo = new CompoPanier(
                        rs.getInt("id_type_panier"),
                        rs.getInt("id_produit"),
                        rs.getInt("quantite")
                );
                compoPaniers.add(compo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compoPaniers;
    }

    /**
     * Met à jour la quantité d'un produit dans un panier.
     *
     * @param idPanier l'identifiant du panier
     * @param idProduit l'identifiant du produit
     * @param nouvelleQuantite la nouvelle quantité du produit dans le panier
     */
    @Override
    public void updateProduit(int idPanier, int idProduit, int nouvelleQuantite) {
        String query = "UPDATE CompoPanier SET quantite = ? WHERE id_type_panier = ? AND id_produit = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, nouvelleQuantite);
            ps.setInt(2, idPanier);
            ps.setInt(3, idProduit);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
