package fr.univamu.iut.user;

import java.util.*;

public interface UserRepositoryInterface {
    public void close();

    public User getUser(String id);

    public ArrayList<User> getAllUsers() ;

    public boolean updateUser(String id, string password, String first_name, String last_name, Date date);
}