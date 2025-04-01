package fr.univamu.iut.pu;

import java.sql.Date;
import java.util.*;

public interface UserRepositoryInterface {
    public void close();

    public User getUser(String id);

    public ArrayList<User> getAllUsers() ;

    public boolean updateUser(String login, String first_name, String last_name, Date date, String password);

    public boolean addUser(User user);
}