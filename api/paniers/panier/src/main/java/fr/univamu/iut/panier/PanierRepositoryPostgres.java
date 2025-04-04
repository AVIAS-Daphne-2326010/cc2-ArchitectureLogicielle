package fr.univamu.iut.panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe permettant d'accéder aux paniers stockés dans une base de données MariaDB
 */
public class PanierRepositoryPostgres implements PanierRepositoryInterface {

    private Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de connexion à la base de données
     * @param user identifiant de connexion
     * @param pwd mot de passe
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanierRepositoryPostgres(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
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
    public Panier getPanier(int id) {
        Panier panier = null;
        String query = "SELECT * FROM Panier WHERE id_type_panier = ?";
        String queryCompo = "SELECT * FROM CompoPanier WHERE id_type_panier = ?";

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                panier = new Panier(id, rs.getDouble("prix"), rs.getInt("n_panier_dispo"), rs.getString("mise_a_jour"), new ArrayList<>());

                try (PreparedStatement stmtCompo = dbConnection.prepareStatement(queryCompo)) {
                    stmtCompo.setInt(1, id);
                    try (ResultSet rsCompo = stmtCompo.executeQuery()) {
                        while (rsCompo.next()) {
                            int idProduit = rsCompo.getInt("id_produit");
                            int quantite = rsCompo.getInt("quantite");

                            String apiUrl = "http://localhost:8080/PU-1.0-SNAPSHOT/api/produits/" + idProduit;
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(apiUrl))
                                    .GET()
                                    .build();

                            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                            if (response.statusCode() == 200) {
                                JsonNode productJson = objectMapper.readTree(response.body());

                                String nomProduit = productJson.get("nomProduit").asText();
                                String unite = productJson.get("unite").asText();

                                CompoPanier produit = new CompoPanier();
                                produit.setIdProduit(idProduit);
                                produit.setNomProduit(nomProduit);
                                produit.setUnite(unite);
                                produit.setQuantite(quantite);
                                produit.setIdTypePanier(id);

                                panier.getProduits().add(produit);
                            } else {
                                throw new RuntimeException("Erreur lors du fetch du produit avec id " + idProduit);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return panier;
    }


    @Override
    public List<Panier> getAllPaniers() {
        List<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM Panier";
        String queryCompo = "SELECT * FROM CompoPanier WHERE id_type_panier = ?";

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        try (Statement stmt = dbConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idTypePanier = rs.getInt("id_type_panier");
                Panier panier = new Panier(idTypePanier, rs.getDouble("prix"), rs.getInt("n_panier_dispo"), rs.getString("mise_a_jour"), new ArrayList<>());

                try (PreparedStatement stmtCompo = dbConnection.prepareStatement(queryCompo)) {
                    stmtCompo.setInt(1, idTypePanier);
                    try (ResultSet rsCompo = stmtCompo.executeQuery()) {
                        while (rsCompo.next()) {
                            int idProduit = rsCompo.getInt("id_produit");
                            int quantite = rsCompo.getInt("quantite");

                            String apiUrl = "http://localhost:8080/PU-1.0-SNAPSHOT/api/produits/" + idProduit;
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(apiUrl))
                                    .GET()
                                    .build();

                            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                            if (response.statusCode() == 200) {
                                JsonNode productJson = objectMapper.readTree(response.body());

                                String nomProduit = productJson.get("nomProduit").asText();
                                String unite = productJson.get("unite").asText();

                                CompoPanier produit = new CompoPanier();
                                produit.setIdProduit(idProduit);
                                produit.setNomProduit(nomProduit);
                                produit.setUnite(unite);
                                produit.setQuantite(quantite);
                                produit.setIdTypePanier(idTypePanier);

                                panier.getProduits().add(produit);
                            } else {
                                throw new RuntimeException("Erreur lors du fetch du produit avec id " + idProduit);
                            }
                        }
                    }
                }
                paniers.add(panier);
            }
        } catch (Exception e) {
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
