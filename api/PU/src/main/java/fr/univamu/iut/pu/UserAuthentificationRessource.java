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
 * Ressource associée à l'authentification des utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/authenticate")
@ApplicationScoped
public class UserAuthenticationResource {

    private UserAuthenticationService auth;

    public UserAuthenticationResource(){}

    public @Inject UserAuthenticationResource( UserRepositoryInterface userRepo ){
        this.auth = new UserAuthenticationService( userRepo ) ;
    }


    @GET
    @Produces("text/plain")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        boolean res = false;

        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        String encodedCredentials = authHeader.split(" ")[1];
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), "UTF-8");

        String[] tokens = decodedCredentials.split(":");

        String login = tokens[0];
        String pwd = tokens[1];

        res = auth.isValidUser(login, pwd);

        return Response.ok(String.valueOf(res)).build();
    }
}