package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST pour la gestion des commandes et de leur composition.
 * Expose des endpoints CRUD pour les commandes et leurs paniers associés.
 *
 * <p>Toutes les routes sont préfixées par {@code /commandes}.</p>
 *
 * @see CommandeService
 * @see CommandeRepositoryInterface
 */
@Path("/commandes")
public class CommandeRessource {
    private CommandeService service;

    /**
     * Constructeur avec injection de dépendance.
     * @param commandeRepo Le repository à injecter
     */
    @Inject
    public CommandeRessource(CommandeRepositoryInterface commandeRepo) {
        this.service = new CommandeService(commandeRepo);
    }

    /**
     * Récupère toutes les commandes au format JSON.
     * @return String JSON contenant la liste des commandes
     *
     * @HTTP 200 Succès
     */
    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return service.getAllCommandesJSON();
    }

    /**
     * Récupère une commande spécifique par son ID.
     * @param id Identifiant de la commande
     * @return String JSON contenant la commande
     *
     * @HTTP 200 Succès
     * @HTTP 404 Si la commande n'existe pas
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {
        String result = service.getCommandeJSON(id);
        if (result==null) throw new NotFoundException();
        return result;
    }

    /**
     * Crée une nouvelle commande.
     * @param commande La commande à créer (au format JSON)
     * @return Réponse HTTP
     *
     * @HTTP 201 Créé avec succès
     * @HTTP 406 Si la création échoue
     */
    @POST
    @Consumes("application/json")
    public Response createCommande(Commande commande) {
        if (!service.createCommande(commande)) {
            throw new NotAcceptableException();
        }
        return Response.status(201).entity("created").build();
    }

    /**
     * Supprime une commande existante.
     * @param id Identifiant de la commande à supprimer
     * @return Réponse HTTP
     *
     * @HTTP 200 Supprimé avec succès
     * @HTTP 404 Si la commande n'existe pas
     */
    @DELETE
    @Path("{id}")
    public Response deleteCommande(@PathParam("id") int id) {
        if (!service.deleteCommande(id)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    /**
     * Met à jour une commande existante.
     * @param id Identifiant de la commande
     * @param commande Nouvelles données de la commande (au format JSON)
     * @return Réponse HTTP
     *
     * @HTTP 200 Mis à jour avec succès
     * @HTTP 404 Si la commande n'existe pas
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commande commande) {
        if (!service.updateCommande(id, commande)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    /**
     * Récupère les paniers d'une commande.
     * @param id Identifiant de la commande
     * @return String JSON contenant la liste des paniers
     *
     * @HTTP 200 Succès
     * @HTTP 404 Si la commande n'existe pas
     */
    @GET
    @Path("{id}/paniers")
    @Produces("application/json")
    public String getCompoCommandes(@PathParam("id") int id) {
        String result = service.getCompoCommandesJSON(id);
        if (result == null) throw new NotFoundException();
        return result;
    }

    /**
     * Ajoute un panier à une commande.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Quantité à ajouter
     * @return Réponse HTTP
     *
     * @HTTP 200 Ajouté avec succès
     * @HTTP 406 Si l'ajout échoue
     */
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

    /**
     * Met à jour la quantité d'un panier dans une commande.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Nouvelle quantité
     * @return Réponse HTTP
     *
     * @HTTP 200 Mis à jour avec succès
     * @HTTP 404 Si la commande ou le panier n'existe pas
     */
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

    /**
     * Supprime un panier d'une commande.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @return Réponse HTTP
     *
     * @HTTP 200 Supprimé avec succès
     * @HTTP 404 Si la commande ou le panier n'existe pas
     */
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