package fr.univamu.iut.apicommandes;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class CommandeRepositoryPostgres implements CommandeRepositoryInterface, Closeable {
    private final Connection dbConnection;

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
    public Commande getCommande(int id) {
        Commande selectedCommande = null;
        String query = "SELECT * FROM Commande WHERE id = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if(result.next()) {
                String user_name = result.getString("user_name");
                String relai = result.getString("relai");
                Date date = result.getDate("date");

                selectedCommande = new Commande(id, user_name, relai, date);
                selectedCommande.setRelai(relai);
                selectedCommande.setDate(date);
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
                int id = result.getInt("id");
                String user_name = result.getString("user_name");
                String relai = result.getString("relai");
                Date date = result.getDate("date");

                Commande currentCommande = new Commande(id, user_name, relai, date);
                currentCommande.setRelai(relai);
                currentCommande.setDate(date);

                listCommandes.add(currentCommande);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande (int id, String user_name, String relai, Date date) {
        String query = "UPDATE Commande SET user_name = ?, relai = ?, date = ? WHERE id = ?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString (1, user_name);
            ps.setString (2, relai);
            ps.setDate (3, date);
            ps.setInt (4, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

}
