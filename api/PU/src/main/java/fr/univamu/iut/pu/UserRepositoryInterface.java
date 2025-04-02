package fr.univamu.iut.pu;

import java.sql.Date;
import java.util.*;

/**
 * Interface représentant le dépôt des utilisateurs.
 * Cette interface définit les opérations de gestion des utilisateurs (ajout, récupération, mise à jour, etc.)
 * qui peuvent être effectuées sur une base de données ou tout autre système de stockage.
 */
public interface UserRepositoryInterface {

    /**
     * Ferme la connexion avec la base de données ou le système de stockage.
     * Cette méthode doit être appelée pour libérer les ressources après utilisation.
     */
    public void close();

    /**
     * Récupère un utilisateur à partir de son identifiant (login).
     *
     * @param id L'identifiant (login) de l'utilisateur à récupérer.
     * @return Un objet {@code User} représentant l'utilisateur avec le login spécifié,
     *         ou {@code null} si aucun utilisateur correspondant n'a été trouvé.
     */
    public User getUser(String id);

    /**
     * Récupère tous les utilisateurs du système de stockage.
     *
     * @return Une liste contenant tous les utilisateurs. La liste est vide si aucun utilisateur n'est présent.
     */
    public ArrayList<User> getAllUsers();

    /**
     * Met à jour les informations d'un utilisateur dans le système de stockage.
     *
     * @param login Le login de l'utilisateur à mettre à jour.
     * @param first_name Le nouveau prénom de l'utilisateur.
     * @param last_name Le nouveau nom de famille de l'utilisateur.
     * @param date La nouvelle date de naissance de l'utilisateur.
     * @param password Le nouveau mot de passe de l'utilisateur.
     * @return {@code true} si la mise à jour a été effectuée avec succès, {@code false} sinon.
     */
    public boolean updateUser(String login, String first_name, String last_name, String date, String password);

    /**
     * Ajoute un nouvel utilisateur au système de stockage.
     *
     * @param user L'objet {@code User} représentant l'utilisateur à ajouter.
     * @return {@code true} si l'utilisateur a été ajouté avec succès, {@code false} sinon.
     */
    public boolean addUser(User user);
}
