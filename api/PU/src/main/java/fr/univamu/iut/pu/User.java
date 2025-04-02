package fr.univamu.iut.pu;

import java.util.Date;

/**
 * La classe {@code User} représente un utilisateur avec des informations telles que
 * le login, le mot de passe, le prénom, le nom de famille et la date de naissance.
 * Elle inclut des méthodes pour accéder et modifier ces informations.
 */
public class User {

    /**
     * Le login de l'utilisateur.
     */
    private String login;

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String password;

    /**
     * Le prénom de l'utilisateur.
     */
    private String firstName;

    /**
     * Le nom de famille de l'utilisateur.
     */
    private String lastName;

    /**
     * La date de naissance de l'utilisateur.
     */
    private String date;

    /**
     * Constructeur de la classe {@code User} pour initialiser un utilisateur avec les informations fournies.
     *
     * @param login Le login de l'utilisateur.
     * @param firstName Le prénom de l'utilisateur.
     * @param lastName Le nom de famille de l'utilisateur.
     * @param date La date de naissance de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
    public User(String login, String firstName, String lastName, String date, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.password = password;
    }

    /**
     * Récupère le login de l'utilisateur.
     *
     * @return Le login de l'utilisateur.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Modifie le login de l'utilisateur.
     *
     * @param login Le nouveau login de l'utilisateur.
     */
    public void setlogin(String login) {
        this.login = login;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     *
     * @param password Le nouveau mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Modifie le prénom de l'utilisateur.
     *
     * @param firstName Le nouveau prénom de l'utilisateur.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Récupère le nom de famille de l'utilisateur.
     *
     * @return Le nom de famille de l'utilisateur.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Modifie le nom de famille de l'utilisateur.
     *
     * @param lastName Le nouveau nom de famille de l'utilisateur.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Récupère la date de naissance de l'utilisateur.
     *
     * @return La date de naissance de l'utilisateur.
     */
    public String getDate() {
        return date;
    }

    /**
     * Modifie la date de naissance de l'utilisateur.
     *
     * @param date La nouvelle date de naissance de l'utilisateur.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'utilisateur.
     *
     * @return Une chaîne représentant l'utilisateur, avec son login, mot de passe, prénom, nom de famille et date de naissance.
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
