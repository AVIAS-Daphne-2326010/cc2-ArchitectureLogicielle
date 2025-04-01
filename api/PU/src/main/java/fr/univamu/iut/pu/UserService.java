package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service de gestion des utilisateurs.
 * Cette classe contient la logique métier pour interagir avec le dépôt d'utilisateurs et convertir les données en format JSON.
 */
@ApplicationScoped
public class UserService {

    /**
     * Référentiel des utilisateurs, utilisé pour accéder aux données des utilisateurs.
     */
    private UserRepositoryInterface userRepo;

    /**
     * Constructeur par défaut.
     */
    public UserService() {}

    /**
     * Constructeur avec injection du référentiel des utilisateurs.
     *
     * @param userRepo Le référentiel des utilisateurs utilisé pour accéder aux données des utilisateurs.
     */
    @Inject
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Récupère tous les utilisateurs sous forme de chaîne JSON.
     *
     * @return Une chaîne JSON représentant tous les utilisateurs.
     */
    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers(); // Récupère tous les utilisateurs du référentiel

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers); // Convertit la liste des utilisateurs en JSON
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un utilisateur spécifique par son login, sous forme de chaîne JSON.
     *
     * @param login Le login de l'utilisateur à récupérer.
     * @return Une chaîne JSON représentant l'utilisateur demandé, ou null si l'utilisateur n'est pas trouvé.
     */
    public String getUserJSON(String login) {
        User myUser = userRepo.getUser(login); // Récupère l'utilisateur du référentiel

        if (myUser != null) {
            String result = null;
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser); // Convertit l'utilisateur en JSON
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return result;
        } else {
            return null; // Si l'utilisateur n'est pas trouvé, renvoie null
        }
    }
}
