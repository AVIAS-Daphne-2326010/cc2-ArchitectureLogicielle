package fr.univamu.iut.panier;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.List;

/**
 * Service pour la gestion des compositions de paniers.
 */
public class CompoPanierService {

    private CompoPanierRepositoryInterface compoRepo;

    /**
     * Constructeur du service utilisant un repository de composition de panier.
     *
     * @param compoRepo l'interface du repository pour accéder aux données des compositions de panier.
     */
    public CompoPanierService(CompoPanierRepositoryInterface compoRepo) {
        this.compoRepo = compoRepo;
    }

    /**
     * Ajoute un produit à un panier avec une quantité spécifiée.
     *
     * @param idPanier  l'identifiant du panier
     * @param idProduit l'identifiant du produit à ajouter
     * @param quantite  la quantité du produit à ajouter
     */
    public void ajouterProduit(int idPanier, int idProduit, int quantite) {
        CompoPanier compo = new CompoPanier(idPanier, idProduit, quantite);
        compoRepo.ajouterProduit(compo);
    }

    /**
     * Supprime un produit d'un panier.
     *
     * @param idPanier  l'identifiant du panier
     * @param idProduit l'identifiant du produit à supprimer
     */
    public void supprimerProduit(int idPanier, int idProduit) {
        compoRepo.supprimerProduit(idPanier, idProduit);
    }

    /**
     * Met à jour la quantité d'un produit dans un panier.
     *
     * @param idPanier        l'identifiant du panier
     * @param idProduit       l'identifiant du produit à mettre à jour
     * @param nouvelleQuantite la nouvelle quantité du produit dans le panier
     */
    public void updateProduit(int idPanier, int idProduit, int nouvelleQuantite) {
        compoRepo.updateProduit(idPanier, idProduit, nouvelleQuantite);
    }


    /**
     * Récupère la liste des produits d'un panier et les retourne sous forme de JSON.
     *
     * @param idPanier l'identifiant du panier
     * @return une chaîne JSON représentant les produits du panier
     */
    public String getProduitsDuPanierJSON(int idPanier) {
        List<CompoPanier> compoPaniers = compoRepo.getAllProduitsPanier(idPanier);
        String result = null;

        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(compoPaniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
