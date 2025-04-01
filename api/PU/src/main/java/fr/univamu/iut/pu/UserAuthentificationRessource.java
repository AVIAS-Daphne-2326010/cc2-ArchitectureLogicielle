package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Ressource REST associée à l'authentification des utilisateurs.
 * Ce point d'accès permet aux utilisateurs de s'authentifier via le mécanisme Basic Authentication.
 *
 * Il traite les demandes d'authentification en vérifiant les identifiants transmis dans l'en-tête HTTP "Authorization".
 */
@Path("/authenticate")
@ApplicationScoped
public class UserAuthentificationRessource {

    /**
     * Service d'authentification des utilisateurs utilisé pour valider les informations d'identification.
     */
    private UserAuthenticationService auth;

    /**
     * Constructeur par défaut de la classe {@code UserAuthentificationRessource}.
     */
    public UserAuthentificationRessource(){}

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param userRepo Le dépôt des utilisateurs utilisé pour récupérer les informations des utilisateurs.
     */
    public @Inject UserAuthentificationRessource(UserRepositoryInterface userRepo){
        this.auth = new UserAuthenticationService(userRepo);
    }

    /**
     * Point d'accès GET qui permet d'authentifier un utilisateur en utilisant les identifiants envoyés dans l'en-tête
     * "Authorization" sous le format Basic Authentication.
     *
     * @param requestContext Le contexte de la requête HTTP, utilisé pour extraire l'en-tête "Authorization".
     * @return Une réponse HTTP contenant "true" si les identifiants sont valides, "false" sinon.
     *         Retourne un statut HTTP 401 (Unauthorized) si l'en-tête "Authorization" est absent ou mal formé.
     * @throws UnsupportedEncodingException Si l'encodage des données échoue.
     */
    @GET
    @Produces("text/plain")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        boolean res = false;

        // Récupérer l'en-tête d'autorisation
        String authHeader = requestContext.getHeaderString("Authorization");

        // Vérifier si l'en-tête est valide et commence par "Basic"
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("WWW-Authenticate", "Basic")
                    .build();
        }

        // Extraire et décoder les informations d'identification de l'en-tête
        String encodedCredentials = authHeader.split(" ")[1];
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), "UTF-8");

        // Séparer le login et le mot de passe
        String[] tokens = decodedCredentials.split(":");

        String login = tokens[0];
        String pwd = tokens[1];

        // Vérifier si les informations d'identification sont valides
        res = auth.isValidUser(login, pwd);

        // Retourner une réponse avec le résultat de l'authentification
        return Response.ok(String.valueOf(res)).build();
    }
}
