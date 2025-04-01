package fr.univamu.iut.apicommandes;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Interface définissant le contrat pour le repository de gestion des commandes.
 * Fournit les opérations CRUD (Create, Read, Update, Delete) pour les commandes
 * et leur composition (paniers associés).
 *
 * <p>Cette interface doit être implémentée par des classes concrètes fournissant
 * l'accès aux données, comme {@link CommandeRepositoryPostgres}.</p>
 */
public interface CommandeRepositoryInterface {

    /**
     * Ferme les ressources utilisées par le repository.
     * Doit être appelée lorsque le repository n'est plus nécessaire.
     */
    void close();

    /**
     * Récupère une commande par son identifiant.
     * @param id Identifiant de la commande à récupérer
     * @return La commande correspondante, ou null si non trouvée
     */
    Commande getCommande(int id);

    /**
     * Récupère toutes les commandes existantes.
     * @return Liste de toutes les commandes
     */
    ArrayList<Commande> getAllCommandes();

    /**
     * Met à jour les informations d'une commande existante.
     * @param id Identifiant de la commande à mettre à jour
     * @param user_name Nouveau nom d'utilisateur
     * @param relai Nouveau point relais
     * @param date Nouvelle date
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updateCommande(int id, String user_name, String relai, Date date);

    /**
     * Crée une nouvelle commande dans le système.
     * @param commande Commande à créer
     * @return true si la création a réussi, false sinon
     */
    boolean createCommande(Commande commande);

    /**
     * Supprime une commande existante.
     * @param id Identifiant de la commande à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean deleteCommande(int id);

    /**
     * Récupère la composition (paniers) d'une commande.
     * @param id_commande Identifiant de la commande
     * @return Liste des paniers associés à la commande
     */
    ArrayList<CompoCommande> getCompoCommandes(int id_commande);

    /**
     * Ajoute un panier à une commande existante.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Quantité à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    boolean addCompoCommande(int id_commande, int id_type_panier, int quantite);

    /**
     * Met à jour la quantité d'un panier dans une commande.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @param quantite Nouvelle quantité
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updateCompoCommande(int id_commande, int id_type_panier, int quantite);

    /**
     * Supprime un panier d'une commande.
     * @param id_commande Identifiant de la commande
     * @param id_type_panier Identifiant du type de panier
     * @return true si la suppression a réussi, false sinon
     */
    boolean removeCompoCommande(int id_commande, int id_type_panier);
}