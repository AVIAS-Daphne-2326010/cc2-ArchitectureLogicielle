package fr.univamu.iut.user;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UserRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User getUser(int id) {

        User selectedUser = null;

        String query = "SELECT * FROM User WHERE id=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id); // utiliser setInt ici

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String mail = result.getString("mail");
                String name = result.getString("name");
                String pwd = result.getString("pwd");

                selectedUser = new User(id, mail, name, pwd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers = new ArrayList<>();

        String query = "SELECT * FROM User";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ResultSet result = ps.executeQuery();

            while ( result.next() )
            {
                int id = result.getInt("id");
                String mail = result.getString("mail");
                String name = result.getString("name");
                String pwd = result.getString("pwd");

                User currentUser = new User(id, mail, name, pwd);
                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(int id, String mail, String name, String pwd) {
        String query = "UPDATE User SET mail=?, name=?, pwd=? WHERE id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, mail);
            ps.setString(2, name);
            ps.setString(3, pwd);
            ps.setInt(4, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}