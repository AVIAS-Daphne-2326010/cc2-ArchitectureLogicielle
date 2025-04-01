package fr.univamu.iut.pu;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryPostgres implements UserRepositoryInterface, Closeable {

    protected Connection dbConnection;

    // Constructor
    public UserRepositoryPostgres(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    // Close connection
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User getUser(String login) {
        User selectedUser = null;
        String query = "SELECT * FROM users WHERE login=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, login);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String password = result.getString("password");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                Date date = result.getDate("date");

                selectedUser = new User(login, firstName, lastName, date, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selectedUser;
    }

    // Get all Users
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String login = result.getString("login");
                String password = result.getString("password");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                Date date = result.getDate("date");

                User currentUser = new User(login, firstName, lastName, date, password);
                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listUsers;
    }

    // Update a User's details
    @Override
    public boolean updateUser(String login, String firstName, String lastName, Date date, String password) {
        String query = "UPDATE users SET first_name=?, last_name=?, date=?, password=? WHERE login=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setDate(3, date);
            ps.setString(4, password);
            ps.setString(5, login);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbRowModified != 0;
    }

    // Add a new User
    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO users (login, first_name, last_name, date, password) VALUES (?, ?, ?, ?, ?)";
        int nbRowInserted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setDate(4, new java.sql.Date(user.getDate().getTime()));
            ps.setString(5, user.getPassword());

            nbRowInserted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbRowInserted != 0;
    }
}
