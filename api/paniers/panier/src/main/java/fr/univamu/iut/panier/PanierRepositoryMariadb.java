package fr.univamu.iut.panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'accéder aux paniers stockés dans une base de données MariaDB
 */
public class PanierRepositoryMariadb implements PanierRepositoryInterface {

    private Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de connexion à la base de données
     * @param user identifiant de connexion
     * @param pwd mot de passe
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanierRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public Panier create(Panier panier) {
        String query = "INSERT INTO Panier (id_type_panier, prix, n_panier_dispo, mise_a_jour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, panier.getId_type_panier());
            ps.setDouble(2, panier.getPrix());
            ps.setInt(3, panier.getN_panier_dispo());
            ps.setString(4, panier.getMise_a_jour());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                panier.setId_type_panier(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public Panier findById(int id) {
        Panier panier = null;
        String query = "SELECT * FROM Panier WHERE id_type_panier = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                panier = new Panier(rs.getInt("id_type_panier"), rs.getDouble("prix"), rs.getInt("n_panier_dispo"), rs.getString("mise_a_jour"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public List<Panier> findAll() {
        List<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM Panier";
        try (Statement stmt = dbConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Panier panier = new Panier(rs.getInt("id_type_panier"), rs.getDouble("prix"), rs.getInt("n_panier_dispo"), rs.getString("mise_a_jour"));
                paniers.add(panier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paniers;
    }

    @Override
    public Panier update(Panier panier) {
        String query = "UPDATE Panier SET prix = ?, n_panier_dispo = ?, mise_a_jour = ? WHERE id_type_panier = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDouble(1, panier.getPrix());
            ps.setInt(2, panier.getN_panier_dispo());
            ps.setString(3, panier.getMise_a_jour());
            ps.setInt(4, panier.getId_type_panier());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Panier WHERE id_type_panier = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() {}
}
