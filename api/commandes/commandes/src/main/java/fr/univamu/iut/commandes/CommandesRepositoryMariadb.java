package fr.univamu.iut.commandes;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CommandesRepositoryMariadb implements CommandesRepositoryInterface, Closeable {
    protected Connection dbConnection;

    public CommandesRepositoryMariadb(String infConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infConnection, user, pwd);
    }

    @Override
    public void close(){
        try{
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Commandes getCommandes(int id_commande) {
        Commandes selectedCommandes = null;
        String query = "SELECT * FROM Commandes WHERE id_commande=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, String.valueOf(id_commande));
            ResultSet result = ps.executeQuery();

            if(result.next()){
                String user_name = result.getString("user_name");
                String relai = result.getString("relai");
                LocalDate date = result.getDate("date").toLocalDate();

                selectedCommandes = new Commandes(id_commande, user_name, relai, date);
                selectedCommandes.setRelai(relai);
                selectedCommandes.setDate(date);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return selectedCommandes;
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> listCommandes;
        String query = "SELECT * FROM Commandes";
        try(PreparedStatement ps = dbConnection.prepareStatement(query)){
            ResultSet result = ps.executeQuery();
            listCommandes = new ArrayList<>();
            while (result.next()){
                int id_commande = result.getInt("id_commande");
                String user_name = result.getString("user_name");
                String relai = result.getString("relai");
                LocalDate date = result.getDate("date").toLocalDate();

                Commandes currentCommandes = new Commandes(id_commande, user_name,relai, date);
                currentCommandes.setRelai(relai);
                currentCommandes.setDate(date);

                listCommandes.add(currentCommandes);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id_commande, String user_name, String relai, LocalDate date){
        String query = "UPDATE Commandes SET user_name=?, relai=?, date=? WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString(1, user_name);
            ps.setString(2, relai);
            ps.setString(3, String.valueOf(date));

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public void ajouterCommande(Commandes commande) {

    }

    @Override
    public void supprimerCommande(int id_commande) {

    }
}
