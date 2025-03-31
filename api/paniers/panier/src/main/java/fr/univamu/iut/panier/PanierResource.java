package fr.univamu.iut.panier;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux paniers
 */
@Path("/panier")
public class PanierResource {

    private PanierService service;
    private CompoPanierService compoService;

    @Inject
    public PanierResource(PanierRepositoryInterface panierRepo, CompoPanierRepositoryInterface compoRepo) {
        this.service = new PanierService(panierRepo);
        this.compoService = new CompoPanierService(compoRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return service.getAllPaniersJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPanier(@PathParam("id") int id) {
        String result = service.getPanierJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        if (!service.updatePanier(id, panier)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePanier(@PathParam("id") int id) {
        service.deletePanier(id);
        return Response.ok("deleted").build();
    }


    @POST
    @Path("{id}/produit/{idProduit}")
    @Consumes("application/json")
    public Response ajouterProduitAuPanier(@PathParam("id") int idPanier, @PathParam("idProduit") int idProduit, @QueryParam("quantite") int quantite) {
        compoService.ajouterProduit(idPanier, idProduit, quantite);
        return Response.status(Response.Status.CREATED).entity("Produit ajouté au panier").build();
    }

    @DELETE
    @Path("{id}/produit/{idProduit}")
    public Response supprimerProduitDuPanier(@PathParam("id") int idPanier, @PathParam("idProduit") int idProduit) {
        compoService.supprimerProduit(idPanier, idProduit);
        return Response.status(Response.Status.OK).entity("Produit supprimé du panier").build();
    }

    @GET
    @Path("{id}/produits")
    @Produces("application/json")
    public String getProduitsDuPanier(@PathParam("id") int idPanier) {
        return compoService.getProduitsDuPanierJSON(idPanier);
    }
}
