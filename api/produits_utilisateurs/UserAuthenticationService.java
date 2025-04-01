package fr.univamu.iut.user;

public class UserAuthenticationService {

    protected UserRepositoryInterface userRepo ;

    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public boolean isValidUser(String login, String password) {

        User currentUser = userRepo.getUser(login);

        if( currentUser == null )
            return false;

        if( ! currentUser.getPwd().equals(pwd) )
            return false;

        return true;
    }
}