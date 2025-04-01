package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commandes")
public class CommandeRessource {
    private CommandeService service;

    @Inject
    public CommandeRessource(CommandeRepositoryInterface commandeRepo) {
        this.service = new CommandeService(commandeRepo);
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
        String result = service.getCommandeJSON(id);

        if (result==null) throw new NotFoundException();
        return result;
    }

    @POST
    @Consumes("application/json")
    public Response createCommande(Commande commande) {
        if (!service.createCommande(commande)) {
            throw new NotAcceptableException();
        }
        return Response.status(201).entity("created").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCommande(@PathParam("id") int id) {
        if (!service.deleteCommande(id)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commande commande) {
        if (!service.updateCommande(id, commande)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }

    @GET
    @Path("{id}/paniers")
    @Produces("application/json")
    public String getCompoCommandes(@PathParam("id") int id) {
        String result = service.getCompoCommandesJSON(id);
        if (result == null) throw new NotFoundException();
        return result;
    }

    @POST
    @Path("{id}/paniers")
    @Consumes("application/x-www-form-urlencoded")
    public Response addCompoCommande(
            @PathParam("id") int id_commande,
            @FormParam("id_type_panier") int id_type_panier,
            @FormParam("quantite") int quantite) {

        if (!service.addCompoCommande(id_commande, id_type_panier, quantite)) {
            throw new NotAcceptableException();
        }
        return Response.ok("added").build();
    }

    @PUT
    @Path("{id}/paniers/{idPanier}")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateCompoCommande(
            @PathParam("id") int id_commande,
            @PathParam("idPanier") int id_type_panier,
            @FormParam("quantite") int quantite) {

        if (!service.updateCompoCommande(id_commande, id_type_panier, quantite)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("{id}/paniers/{idPanier}")
    public Response removeCompoCommande(
            @PathParam("id") int id_commande,
            @PathParam("idPanier") int id_type_panier) {

        if (!service.removeCompoCommande(id_commande, id_type_panier)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

}
