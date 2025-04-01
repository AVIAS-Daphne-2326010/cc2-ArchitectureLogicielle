package fr.univamu.iut.apicommandes;

import java.sql.*;
import java.util.ArrayList;

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

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);

            ResultSet result = ps.executeQuery();

            if(result.next()) {
                String login = result.getString("login");
                String relai = result.getString("relai");
                Date date = result.getDate("date");

                selectedCommande = new Commande(id_commande, login, relai, date);

                ArrayList<CompoCommande> paniers = getCompoCommandes(id_commande);
                selectedCommande.setPaniers(paniers);
            }

        } catch (SQLException e){
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
    public ArrayList<Commande> getAllCommandes(){
        ArrayList<Commande> listCommandes;

        String query = "SELECT * FROM Commande";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ResultSet result = ps.executeQuery();
            listCommandes = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id_commande");
                String login = result.getString("login");
                String relai = result.getString("relai");
                Date date = result.getDate("date");

                Commande currentCommande = new Commande(id, login, relai, date);

                ArrayList<CompoCommande> paniers = getCompoCommandes(id);
                currentCommande.setPaniers(paniers);

                listCommandes.add(currentCommande);
            }

        } catch (SQLException e){
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
            ps.setDate(3, new java.sql.Date(commande.getDate().getTime()));

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
        // Option 1 : Suppression en cascade (si les FK le permettent)
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
    public boolean updateCommande (int id_commande, String login, String relai, Date date) {
        String query = "UPDATE Commande SET login = ?, relai = ?, date = ? WHERE id_commande = ?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString (1, login);
            ps.setString (2, relai);
            ps.setDate (3, date);
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

}
