package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée à la gestion des utilisateurs dans l'API REST.
 * Cette classe expose des points d'accès pour récupérer tous les utilisateurs ou un utilisateur spécifique.
 */
@Path("/users")
@ApplicationScoped
public class UserResource {

    /**
     * Service de gestion des utilisateurs injecté pour interagir avec la couche métier.
     */
    private UserService userService;

    /**
     * Constructeur par défaut.
     */
    public UserResource() {
    }

    /**
     * Constructeur avec injection du service de gestion des utilisateurs.
     *
     * @param userService Le service de gestion des utilisateurs.
     */
    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * Point d'accès GET pour récupérer tous les utilisateurs.
     *
     * @return Une chaîne JSON représentant tous les utilisateurs.
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return userService.getAllUsersJSON();
    }

    /**
     * Point d'accès GET pour récupérer un utilisateur spécifique par son login.
     *
     * @param login Le login de l'utilisateur à récupérer.
     * @return Une chaîne JSON représentant l'utilisateur demandé.
     * @throws NotFoundException Si l'utilisateur avec le login spécifié n'est pas trouvé.
     */
    @GET
    @Path("/{login}")
    @Produces("application/json")
    public String getUserById(@PathParam("login") String login) {
        String result = userService.getUserJSON(login);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }
}
