package fr.univamu.iut.panier;

import java.util.List;

/**
 * Interface définissant les opérations pour la gestion des compositions de paniers.
 */
public interface CompoPanierRepositoryInterface {

    /**
     * Ferme la connexion à la base de données.
     */
    void close();

    /**
     * Ajoute un produit dans un panier.
     *
     * @param compo la composition du panier à ajouter
     */
    void ajouterProduit(CompoPanier compo);

    /**
     * Supprime un produit d'un panier.
     *
     * @param idPanier l'identifiant du panier
     * @param idProduit l'identifiant du produit à supprimer
     */
    void supprimerProduit(int idPanier, int idProduit);

    /**
     * Récupère la liste des produits contenus dans un panier.
     *
     * @param idPanier l'identifiant du panier
     * @return une liste des produits présents dans le panier
     */
    List<CompoPanier> getAllProduitsPanier(int idPanier);


    /**
     * Met à jour la quantité d'un produit dans un panier.
     *
     * @param idPanier l'identifiant du panier
     * @param idProduit l'identifiant du produit
     * @param nouvelleQuantite la nouvelle quantité du produit
     */
    void updateProduit(int idPanier, int idProduit, int nouvelleQuantite);
}
