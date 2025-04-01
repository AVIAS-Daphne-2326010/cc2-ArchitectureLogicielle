package fr.univamu.iut.user;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date date;

    public User(String login, String password, String firstName, String lastName, Date date) {
        this.login = login;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getLogin() {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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