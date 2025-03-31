package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commandes")
@ApplicationScoped
public class CommandesResource {
    @Inject
    private CommandesService service;

    public CommandesResource() {}

    @Inject
    public CommandesResource(CommandesRepositoryInterface commandesRepo) {
        this.service = new CommandesService(commandesRepo);
    }

    public CommandesResource(CommandesService service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return service.getAllCommandesJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommandes(@PathParam("id") int id){
        String result = service.getCommandesJSON(id);
        if(result == null)
            return"{}";
        return result;
    }
}
