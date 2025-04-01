package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/produits")
@ApplicationScoped
public class ProduitResource {

    private ProduitService produitService;

    public ProduitResource() {
    }

    @Inject
    public ProduitResource(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GET
    @Produces("application/json")
    public String getAllProduits() {

        return produitService.getAllProduitsJSON();
    }

    @GET
    @Path("/{id_produit}")
    @Produces("application/json")
    public String getProduitById(@PathParam("id_produit") int idProduit) {
        String result = produitService.getProduitJSON(idProduit);

        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }
}
