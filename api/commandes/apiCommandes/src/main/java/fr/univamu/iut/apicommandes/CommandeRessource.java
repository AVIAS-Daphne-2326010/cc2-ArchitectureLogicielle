package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commandes")
@ApplicationScoped
public class CommandeRessource {
    private CommandeService service;

    public CommandeRessource() {}

    public CommandeRessource(CommandeRepositoryInterface commandeRepo) {
        this.service = new CommandeService(commandeRepo);
    }

    public CommandeRessource(CommandeService service) {
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
    public String getCommande(@PathParam("id") int id) {
        String result = service.getCommmandeJSON(id);

        if (result==null) throw new NotFoundException();
        return result;
    }

    @GET
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commande commande) {
        if (!service.updateCommande(id, commande)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }


}
