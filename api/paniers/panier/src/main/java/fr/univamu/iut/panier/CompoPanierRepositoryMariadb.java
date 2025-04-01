package fr.univamu.iut.panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompoPanierRepositoryMariadb implements CompoPanierRepositoryInterface {

    private Connection dbConnection;

    public CompoPanierRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

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

    @Override
    public List<CompoPanier> findByPanier(int idPanier) {
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

    @Override
    public void close(){};
}
