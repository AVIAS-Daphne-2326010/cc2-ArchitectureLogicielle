package fr.univamu.iut.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@ApplicationScoped
public class UserResource {

    private UserService userService;

    public UserResource() {}

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userService.getAllUsersJSON();
    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("login") String login) {
        String result = userService.getUserJSON(login);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }
}
