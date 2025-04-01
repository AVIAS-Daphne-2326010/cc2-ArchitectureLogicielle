package fr.univamu.iut.user;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;


public class UserRepositoryPostgres implements UserRepositoryInterface, Closeable {

    protected Connection dbConnection ;

    public UserRepositoryPostgres(String infoConnection, String user, String password ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, password ) ;
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
    public User getUser(String login) {

        User selectedUser = null;

        String query = "SELECT * FROM User WHERE login=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, login);

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String login = result.getInt("login");
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                Date date = result.getDate("date");
                String password = result.getString("password");

                selectedUser = new User(login, first_name, last_name, date, password);
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
                String login = result.getInt("login");
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                Date date = result.getDate("date");
                String password = result.getString("password");


                User currentUser = new User(login, first_name, last_name, date, password);
                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(String login, String first_name, String last_name, Date date, String password) {
        String query = "UPDATE User SET first_name=?, last_name=?, password=?, date=?, WHERE login=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, password);
            ps.setInt(5, login);
            ps.setDate(6, date);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}