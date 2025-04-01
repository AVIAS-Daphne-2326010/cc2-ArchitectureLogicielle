package fr.univamu.iut.pu;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class ProduitRepositoryPostgres implements ProduitRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public ProduitRepositoryPostgres(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Produit getProduit(int idProduit) {
        Produit selectedProduit = null;
        String query = "SELECT * FROM produit WHERE id_produit=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idProduit);

            ResultSet result = ps.executeQuery();
            System.out.println(result);
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
