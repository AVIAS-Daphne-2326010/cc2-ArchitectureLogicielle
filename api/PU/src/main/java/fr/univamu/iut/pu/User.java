package fr.univamu.iut.pu;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date date;

    public User(String login, String firstName, String lastName, java.sql.Date date, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}