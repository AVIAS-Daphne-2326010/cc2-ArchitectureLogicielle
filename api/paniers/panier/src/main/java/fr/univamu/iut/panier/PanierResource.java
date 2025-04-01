package fr.univamu.iut.panier;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux paniers.
 * Cette classe gère les opérations HTTP relatives aux paniers et à leur contenu.
 * Elle fournit des méthodes pour accéder, modifier, supprimer des paniers et gérer leurs produits.
 */
@Path("/panier")
public class PanierResource {

    private PanierService service;
    private CompoPanierService compoService;

    /**
     * Constructeur de la ressource PanierResource.
     * Injecte les services nécessaires pour la gestion des paniers et de leurs compositions.
     *
     * @param panierRepo   le repository pour accéder aux données des paniers
     * @param compoRepo    le repository pour accéder aux données des compositions de paniers
     */
    @Inject
    public PanierResource(PanierRepositoryInterface panierRepo, CompoPanierRepositoryInterface compoRepo) {
        this.service = new PanierService(panierRepo);
        this.compoService = new CompoPanierService(compoRepo);
    }

    /**
     * Récupère tous les paniers sous forme de JSON.
     *
     * @return une chaîne JSON représentant tous les paniers
     */
    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return service.getAllPaniersJSON();
    }

    /**
     * Récupère un panier par son identifiant sous forme de JSON.
     *
     * @param id l'identifiant du panier
     * @return une chaîne JSON représentant le panier
     * @throws NotFoundException si le panier n'existe pas
     */
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

    /**
     * Met à jour un panier en utilisant les informations fournies dans le corps de la requête.
     *
     * @param id      l'identifiant du panier à mettre à jour
     * @param panier  le panier avec les nouvelles informations
     * @return une réponse indiquant le succès de la mise à jour
     * @throws NotFoundException si le panier n'existe pas
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        if (!service.updatePanier(id, panier)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    /**
     * Supprime un panier par son identifiant.
     *
     * @param id l'identifiant du panier à supprimer
     * @return une réponse indiquant le succès de la suppression
     */
    @DELETE
    @Path("{id}")
    public Response deletePanier(@PathParam("id") int id) {
        service.deletePanier(id);
        return Response.ok("deleted").build();
    }

    /**
     * Ajoute un produit à un panier spécifié par leur identifiant respectif.
     *
     * @param idPanier   l'identifiant du panier
     * @param idProduit  l'identifiant du produit à ajouter
     * @param quantite   la quantité du produit à ajouter au panier
     * @return une réponse indiquant le succès de l'ajout
     */
    @POST
    @Path("{id}/produit/{idProduit}")
    @Consumes("application/json")
    public Response ajouterProduitAuPanier(@PathParam("id") int idPanier, @PathParam("idProduit") int idProduit, @QueryParam("quantite") int quantite) {
        compoService.ajouterProduit(idPanier, idProduit, quantite);
        return Response.status(Response.Status.CREATED).entity("Produit ajouté au panier").build();
    }

    /**
     * Supprime un produit d'un panier spécifié par leur identifiant respectif.
     *
     * @param idPanier   l'identifiant du panier
     * @param idProduit  l'identifiant du produit à supprimer
     * @return une réponse indiquant le succès de la suppression
     */
    @DELETE
    @Path("{id}/produit/{idProduit}")
    public Response supprimerProduitDuPanier(@PathParam("id") int idPanier, @PathParam("idProduit") int idProduit) {
        compoService.supprimerProduit(idPanier, idProduit);
        return Response.status(Response.Status.OK).entity("Produit supprimé du panier").build();
    }

    /**
     * Récupère tous les produits d'un panier sous forme de JSON.
     *
     * @param idPanier l'identifiant du panier
     * @return une chaîne JSON représentant les produits du panier
     */
    @GET
    @Path("{id}/produits")
    @Produces("application/json")
    public String getProduitsDuPanier(@PathParam("id") int idPanier) {
        return compoService.getProduitsDuPanierJSON(idPanier);
    }
}
