package fr.univamu.iut.panier;

import java.util.List;

/**
 * Interface d'accès aux données des paniers
 */
public interface PanierRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();

    /**
     * Méthode permettant de créer un panier
     * @param panier l'objet panier à créer
     * @return le panier créé
     */
    public Panier create(Panier panier);

    /**
     * Méthode retournant un panier par son identifiant
     * @param id identifiant du panier recherché
     * @return le panier recherché
     */
    public Panier findById(int id);

    /**
     * Méthode retournant la liste de tous les paniers
     * @return la liste des paniers
     */
    public List<Panier> findAll();

    /**
     * Méthode permettant de mettre à jour un panier
     * @param panier le panier à mettre à jour
     * @return le panier mis à jour
     */
    public Panier update(Panier panier);

    /**
     * Méthode permettant de supprimer un panier par son identifiant
     * @param id identifiant du panier à supprimer
     */
    public void delete(int id);
}
