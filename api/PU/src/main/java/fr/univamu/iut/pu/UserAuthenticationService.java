package fr.univamu.iut.pu;

public class UserAuthenticationService {

    protected UserRepositoryInterface userRepo ;

    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public boolean isValidUser(String login, String password) {

        User currentUser = userRepo.getUser(login);

        if( currentUser == null )
            return false;

        return currentUser.getPassword().equals(password);
    }
}