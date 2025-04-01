package fr.univamu.iut.pu;

/**
 * Le service {@code UserAuthenticationService} est responsable de l'authentification des utilisateurs.
 * Il permet de valider un utilisateur en vérifiant si le couple login et mot de passe correspond à un utilisateur existant.
 */
public class UserAuthenticationService {

    /**
     * Le dépôt des utilisateurs utilisé pour récupérer les informations des utilisateurs.
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur de la classe {@code UserAuthenticationService}.
     *
     * @param userRepo Le dépôt des utilisateurs utilisé pour accéder aux données des utilisateurs.
     */
    public UserAuthenticationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Vérifie si un utilisateur avec le login et le mot de passe fournis est valide.
     *
     * @param login Le login de l'utilisateur à vérifier.
     * @param password Le mot de passe de l'utilisateur à vérifier.
     * @return {@code true} si l'utilisateur est valide (c'est-à-dire si le login et le mot de passe sont corrects),
     *         {@code false} sinon.
     */
    public boolean isValidUser(String login, String password) {

        User currentUser = userRepo.getUser(login);

        if (currentUser == null)
            return false;

        return currentUser.getPassword().equals(password);
    }
}
