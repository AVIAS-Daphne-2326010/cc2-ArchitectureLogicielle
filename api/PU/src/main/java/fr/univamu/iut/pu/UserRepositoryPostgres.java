package fr.univamu.iut.pu;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Implémentation de {@link UserRepositoryInterface} pour la gestion des utilisateurs dans une base de données PostgreSQL.
 * Cette classe permet de récupérer, ajouter, mettre à jour et supprimer des utilisateurs dans une base de données PostgreSQL.
 */
public class UserRepositoryPostgres implements UserRepositoryInterface, Closeable {

    /**
     * Connexion à la base de données PostgreSQL.
     */
    protected Connection dbConnection;

    /**
     * Constructeur de {@code UserRepositoryPostgres}.
     * Initialise la connexion à la base de données avec les informations fournies.
     *
     * @param infoConnection L'URL de connexion à la base de données PostgreSQL.
     * @param user Le nom d'utilisateur pour la connexion.
     * @param password Le mot de passe pour la connexion.
     * @throws SQLException Si la connexion à la base de données échoue.
     * @throws ClassNotFoundException Si la classe JDBC pour PostgreSQL n'est pas trouvée.
     */
    public UserRepositoryPostgres(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère un utilisateur à partir de son login.
     *
     * @param login Le login de l'utilisateur à récupérer.
     * @return L'objet {@code User} représentant l'utilisateur avec ce login, ou {@code null} si l'utilisateur n'est pas trouvé.
     */
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

    /**
     * Récupère tous les utilisateurs présents dans la base de données.
     *
     * @return Une liste d'objets {@code User} représentant tous les utilisateurs.
     */
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

    /**
     * Met à jour les informations d'un utilisateur dans la base de données.
     *
     * @param login Le login de l'utilisateur à mettre à jour.
     * @param firstName Le nouveau prénom de l'utilisateur.
     * @param lastName Le nouveau nom de famille de l'utilisateur.
     * @param date La nouvelle date de naissance de l'utilisateur.
     * @param password Le nouveau mot de passe de l'utilisateur.
     * @return {@code true} si l'utilisateur a été mis à jour avec succès, {@code false} sinon.
     */
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

    /**
     * Ajoute un nouvel utilisateur dans la base de données.
     *
     * @param user L'objet {@code User} représentant l'utilisateur à ajouter.
     * @return {@code true} si l'utilisateur a été ajouté avec succès, {@code false} sinon.
     */
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
