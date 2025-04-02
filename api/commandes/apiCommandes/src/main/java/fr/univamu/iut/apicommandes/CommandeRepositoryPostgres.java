package fr.univamu.iut.apicommandes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
 * Implémentation concrète du repository pour la gestion des commandes utilisant PostgreSQL.
 * Cette classe fournit les opérations CRUD pour les commandes et leur composition en paniers.
 *
 * <p>Elle implémente {@link CommandeRepositoryInterface} et gère la connexion à la base de données PostgreSQL.</p>
 *
 * @see CommandeRepositoryInterface
 */
public class CommandeRepositoryPostgres implements CommandeRepositoryInterface {
    private Connection dbConnection;
    private static final String PANIER_API_URL = "http://localhost:8080/panier-1.0-SNAPSHOT/api/panier/";

    /**
     * Constructeur établissant la connexion à la base de données PostgreSQL.
     *
     * @param url URL de connexion à la base de données
     * @param user Nom d'utilisateur pour l'authentification
     * @param password Mot de passe pour l'authentification
     * @throws SQLException Si une erreur survient lors de la connexion
     * @throws ClassNotFoundException Si le driver PostgreSQL n'est pas trouvé
     */
    public CommandeRepositoryPostgres(String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.dbConnection = DriverManager.getConnection(url, user, password);
    }

    /**
     * {@inheritDoc}
     * Ferme proprement la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.err.println("ERREUR FERMETURE CONNEXION: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * Récupère une commande complète avec ses paniers associés.
     *
     * @param id_commande Identifiant de la commande à récupérer
     * @return La commande trouvée ou null si elle n'existe pas
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public Commande getCommande(int id_commande) {
        Commande selectedCommande = null;
        String query = "SELECT * FROM Commande WHERE id_commande = ?";
        String queryCompo = "SELECT * FROM CompoCommande WHERE id_commande = ?";

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                selectedCommande = new Commande(
                        id_commande,
                        rs.getString("login"),
                        rs.getString("relai"),
                        rs.getString("date")
                );

                try (PreparedStatement stmtCompo = dbConnection.prepareStatement(queryCompo)) {
                    stmtCompo.setInt(1, id_commande);
                    try (ResultSet rsCompo = stmtCompo.executeQuery()) {
                        ArrayList<CompoCommande> compoCommandes = new ArrayList<>();
                        while (rsCompo.next()) {
                            int idTypePanier = rsCompo.getInt("id_type_panier");
                            int quantite = rsCompo.getInt("quantite");

                            String apiUrl = "http://localhost:8080/panier-1.0-SNAPSHOT/api/panier/" + idTypePanier;
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(apiUrl))
                                    .GET()
                                    .build();

                            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                            if (response.statusCode() == 200) {
                                JsonNode panierJson = objectMapper.readTree(response.body());

                                CompoCommande compo = new CompoCommande();
                                compo.setId_commande(id_commande);
                                compo.setId_type_panier(idTypePanier);
                                compo.setQuantite(quantite);
                                compo.setMiseAjour(panierJson.get("mise_a_jour").asText());
                                compo.setNombrePaniersDispo(panierJson.get("n_panier_dispo").asInt());
                                compo.setPrix(panierJson.get("prix").floatValue());

                                List<Produit> produits = new ArrayList<>();
                                JsonNode produitsArray = panierJson.get("produits");
                                for (JsonNode produitJson : produitsArray) {
                                    Produit produit = new Produit();
                                    produit.setIdProduit(produitJson.get("idProduit").asInt());
                                    produit.setNomProduit(produitJson.get("nomProduit").asText());
                                    produit.setQuantite(produitJson.get("quantite").asInt());
                                    produit.setUnite(produitJson.get("unite").asText());
                                    produits.add(produit);
                                }
                                compo.setProduits(produits);

                                compoCommandes.add(compo);
                            } else {
                                throw new RuntimeException("Erreur lors du fetch du panier avec id " + idTypePanier);
                            }
                        }
                        selectedCommande.setPaniers(compoCommandes);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    /**
     * {@inheritDoc}
     * Récupère toutes les commandes avec leurs paniers associés.
     *
     * @return Liste de toutes les commandes
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public ArrayList<Commande> getAllCommandes() {
        ArrayList<Commande> listCommandes = new ArrayList<>();
        String query = "SELECT * FROM Commande";
        String queryCompo = "SELECT * FROM CompoCommande WHERE id_commande = ?";

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        try (Statement stmt = dbConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idCommande = rs.getInt("id_commande");
                Commande commande = new Commande(
                        idCommande,
                        rs.getString("login"),
                        rs.getString("relai"),
                        rs.getString("date")
                );

                try (PreparedStatement stmtCompo = dbConnection.prepareStatement(queryCompo)) {
                    stmtCompo.setInt(1, idCommande);
                    try (ResultSet rsCompo = stmtCompo.executeQuery()) {
                        ArrayList<CompoCommande> compoCommandes = new ArrayList<>();
                        while (rsCompo.next()) {
                            int idTypePanier = rsCompo.getInt("id_type_panier");
                            int quantite = rsCompo.getInt("quantite");

                            String apiUrl = "http://localhost:8080/panier-1.0-SNAPSHOT/api/panier/" + idTypePanier;
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(apiUrl))
                                    .GET()
                                    .build();

                            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                            if (response.statusCode() == 200) {
                                JsonNode panierJson = objectMapper.readTree(response.body());

                                CompoCommande compo = new CompoCommande();
                                compo.setId_commande(idCommande);
                                compo.setId_type_panier(idTypePanier);
                                compo.setQuantite(quantite);
                                compo.setMiseAjour(panierJson.get("mise_a_jour").asText());
                                compo.setNombrePaniersDispo(panierJson.get("n_panier_dispo").asInt());
                                compo.setPrix(panierJson.get("prix").floatValue());

                                List<Produit> produits = new ArrayList<>();
                                JsonNode produitsArray = panierJson.get("produits");
                                for (JsonNode produitJson : produitsArray) {
                                    Produit produit = new Produit();
                                    produit.setIdProduit(produitJson.get("idProduit").asInt());
                                    produit.setNomProduit(produitJson.get("nomProduit").asText());
                                    produit.setQuantite(produitJson.get("quantite").asInt());
                                    produit.setUnite(produitJson.get("unite").asText());
                                    produits.add(produit);
                                }
                                compo.setProduits(produits);

                                compoCommandes.add(compo);
                            } else {
                                throw new RuntimeException("Erreur lors du fetch du panier avec id " + idTypePanier);
                            }
                        }
                        commande.setPaniers(compoCommandes);
                    }
                }
                listCommandes.add(commande);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    /**
     * {@inheritDoc}
     * Crée une nouvelle commande dans la base de données.
     *
     * @param commande La commande à créer
     * @return true si la création a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean createCommande(Commande commande) {
        String query = "INSERT INTO Commande (login, relai, date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, commande.getUser_name());
            ps.setString(2, commande.getRelai());
            ps.setString(3, commande.getDate());

            int nbRowInserted = ps.executeUpdate();
            return nbRowInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la création de la commande", e);
        }
    }

    /**
     * {@inheritDoc}
     * Supprime une commande de la base de données.
     *
     * @param id Identifiant de la commande à supprimer
     * @return true si la suppression a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean deleteCommande(int id) {
        String query = "DELETE FROM Commande WHERE id_commande = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            int nbRowDeleted = ps.executeUpdate();
            return nbRowDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la suppression de la commande", e);
        }
    }

    /**
     * {@inheritDoc}
     * Met à jour les informations d'une commande existante.
     *
     * @param id_commande Identifiant de la commande à mettre à jour
     * @param login Nouveau nom d'utilisateur
     * @param relai Nouveau point relais
     * @param date Nouvelle date
     * @return true si la mise à jour a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean updateCommande (int id_commande, String login, String relai, String date) {
        String query = "UPDATE Commande SET login = ?, relai = ?, date = ? WHERE id_commande = ?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString (1, login);
            ps.setString (2, relai);
            ps.setString (3, date);
            ps.setInt (4, id_commande);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    /**
     * {@inheritDoc}
     * Récupère la composition en paniers d'une commande.
     *
     * @param id_commande Identifiant de la commande
     * @return Liste des paniers associés à la commande
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public ArrayList<CompoCommande> getCompoCommandes(int id_commande) {
        ArrayList<CompoCommande> listCompoCommandes = new ArrayList<>();
        String query = "SELECT * FROM CompoCommande WHERE id_commande = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id_type_panier = result.getInt("id_type_panier");
                int quantite = result.getInt("quantite");

                listCompoCommandes.add(new CompoCommande(id_commande, id_type_panier, quantite));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCompoCommandes;
    }

    /**
     * {@inheritDoc}
     * Ajoute un panier à une commande existante.
     *
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Quantité à ajouter
     * @return true si l'ajout a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean addCompoCommande(int id_commande, int id_type_panier, int quantite) {
        String query = "INSERT INTO CompoCommande (id_commande, id_type_panier, quantite) VALUES (?, ?, ?)";
        int nbRowInserted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ps.setInt(2, id_type_panier);
            ps.setInt(3, quantite);

            nbRowInserted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowInserted != 0);
    }

    /**
     * {@inheritDoc}
     * Modifie la quantité d'un panier dans une commande.
     *
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Nouvelle quantité
     * @return true si la modification a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean updateCompoCommande(int id_commande, int id_type_panier, int quantite) {
        String query = "UPDATE CompoCommande SET quantite = ? WHERE id_commande = ? AND id_type_panier = ?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, quantite);
            ps.setInt(2, id_commande);
            ps.setInt(3, id_type_panier);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    /**
     * {@inheritDoc}
     * Supprime un panier d'une commande.
     *
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @return true si la suppression a réussi, false sinon
     * @throws RuntimeException Si une erreur SQL survient
     */
    @Override
    public boolean removeCompoCommande(int id_commande, int id_type_panier) {
        String query = "DELETE FROM CompoCommande WHERE id_commande = ? AND id_type_panier = ?";
        int nbRowDeleted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ps.setInt(2, id_type_panier);

            nbRowDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowDeleted != 0);
    }

    @Override
    public ArrayList<Commande> getCommandesByUser(String user_name) {
        ArrayList<Commande> userCommandes = new ArrayList<>();
        String query = "SELECT * FROM Commande WHERE login = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, user_name);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("id_commande");
                String relai = result.getString("relai");
                String date = result.getString("date");

                Commande commande = new Commande(id, user_name, relai, date);
                commande.setPaniers(getCompoCommandes(id));
                userCommandes.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userCommandes;
    }

    /**
     * Méthode utilitaire pour récupérer les détails d'un panier depuis l'API
     * @param idTypePanier l'identifiant du type de panier
     * @return les détails du panier sous forme de String JSON
     * @throws Exception si l'appel à l'API échoue
     */
    private String getPanierDetailsFromApi(int idTypePanier) throws Exception {
        URL url = new URL(PANIER_API_URL + idTypePanier);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception("Erreur API Panier - Code: " + responseCode);
        }
    }

}
