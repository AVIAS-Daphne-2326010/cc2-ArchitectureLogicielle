package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * La classe {@code ProduitResource} est un contrôleur REST qui expose des services HTTP pour la gestion des produits.
 * Elle permet d'interagir avec le service {@code ProduitService} pour récupérer les informations des produits
 * au format JSON. Les opérations incluent la récupération de tous les produits et la récupération d'un produit
 * spécifique par son identifiant.
 */
@Path("/produits")
@ApplicationScoped
public class ProduitResource {

    /**
     * Service de gestion des produits.
     */
    private ProduitService produitService;

    /**
     * Constructeur par défaut de la classe {@code ProduitResource}.
     * Il est utilisé pour l'injection de dépendance.
     */
    public ProduitResource() {
    }

    /**
     * Constructeur de la classe {@code ProduitResource} avec injection du service {@code ProduitService}.
     *
     * @param produitService Le service utilisé pour récupérer et manipuler les produits.
     */
    @Inject
    public ProduitResource(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Récupère tous les produits sous forme de chaîne JSON.
     * Cette méthode répond à une requête HTTP GET sur le chemin "/produits".
     *
     * @return Une chaîne JSON contenant tous les produits.
     */
    @GET
    @Produces("application/json")
    public String getAllProduits() {
        return produitService.getAllProduitsJSON();
    }

    /**
     * Récupère un produit spécifique en fonction de son identifiant, au format JSON.
     * Cette méthode répond à une requête HTTP GET sur le chemin "/produits/{id_produit}".
     *
     * @param idProduit L'identifiant du produit à récupérer.
     * @return Une chaîne JSON représentant le produit demandé.
     * @throws NotFoundException Si le produit avec l'identifiant spécifié n'est pas trouvé.
     */
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
