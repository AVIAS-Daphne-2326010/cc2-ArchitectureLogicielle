package fr.univamu.iut.apicommandes;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class CommandeRepositoryPostgres implements CommandeRepositoryInterface {
    private Connection dbConnection;

    public CommandeRepositoryPostgres(String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.dbConnection = DriverManager.getConnection(url, user, password);
    }

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
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

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

                listCommandes.add(currentCommande);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

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
