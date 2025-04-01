package fr.univamu.iut.pu;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * Interface {@code ProduitRepositoryInterface} qui définit les opérations de gestion des produits.
 * Elle permet d'interagir avec un dépôt de produits, incluant des méthodes pour récupérer, ajouter,
 * supprimer et mettre à jour des produits dans ce dépôt.
 *
 * Ces méthodes sont utilisées pour manipuler des objets {@code Produit} dans une base de données ou
 * tout autre mécanisme de stockage.
 */
public interface ProduitRepositoryInterface {

    /**
     * Ferme la connexion avec le dépôt de produits (ex : base de données).
     * Cette méthode devrait être appelée une fois les opérations de gestion des produits terminées.
     */
    void close();

    /**
     * Récupère un produit spécifique à partir de son identifiant.
     *
     * @param idProduit L'identifiant du produit à récupérer.
     * @return Le produit correspondant à l'identifiant, ou {@code null} si aucun produit n'est trouvé.
     */
    Produit getProduit(int idProduit);

    /**
     * Récupère tous les produits présents dans le dépôt.
     *
     * @return Une liste contenant tous les produits.
     */
    ArrayList<Produit> getAllProduits();

    /**
     * Supprime un produit du dépôt à partir de son identifiant.
     *
     * @param idProduit L'identifiant du produit à supprimer.
     * @return {@code true} si le produit a été supprimé avec succès, {@code false} sinon.
     */
    boolean deleteProduit(int idProduit);

    /**
     * Met à jour les informations d'un produit existant dans le dépôt.
     *
     * @param idProduit L'identifiant du produit à mettre à jour.
     * @param nomProduit Le nouveau nom du produit.
     * @param quantite La nouvelle quantité du produit.
     * @param unite La nouvelle unité de mesure du produit.
     * @return {@code true} si la mise à jour a été effectuée avec succès, {@code false} sinon.
     */
    boolean updateProduit(int idProduit, String nomProduit, int quantite, String unite);

    /**
     * Ajoute un nouveau produit dans le dépôt.
     *
     * @param produit Le produit à ajouter.
     * @return {@code true} si l'ajout a été effectué avec succès, {@code false} sinon.
     */
    boolean addProduit(Produit produit);
}
