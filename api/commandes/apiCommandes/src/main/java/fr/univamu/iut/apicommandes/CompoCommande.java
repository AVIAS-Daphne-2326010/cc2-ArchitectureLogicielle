package fr.univamu.iut.apicommandes;

/**
 * Représente un composant d'une commande, associant une commande à un type de panier et une quantité.
 */
public class CompoCommande {
    protected int id_commande;
    protected int id_type_panier;
    protected int quantite;

    /**
     * Constructeur par défaut.
     */
    public CompoCommande() {
    }

    /**
     * Constructeur avec paramètres.
     * @param id_commande Identifiant de la commande.
     * @param id_type_panier Identifiant du type de panier.
     * @param quantite Quantité du type de panier.
     */
    public CompoCommande(int id_commande, int id_type_panier, int quantite) {
        this.id_commande = id_commande;
        this.id_type_panier = id_type_panier;
        this.quantite = quantite;
    }

    /**
     * Obtient l'identifiant de la commande.
     * @return L'identifiant de la commande.
     */
    public int getId_commande() {
        return id_commande;
    }

    /**
     * Définit l'identifiant de la commande.
     * @param id_commande L'identifiant de la commande.
     */
    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    /**
     * Obtient l'identifiant du type de panier.
     * @return L'identifiant du type de panier.
     */
    public int getId_type_panier() {
        return id_type_panier;
    }

    /**
     * Définit l'identifiant du type de panier.
     * @param id_type_panier L'identifiant du type de panier.
     */
    public void setId_type_panier(int id_type_panier) {
        this.id_type_panier = id_type_panier;
    }

    /**
     * Obtient la quantité du type de panier dans la commande.
     * @return La quantité du type de panier.
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définit la quantité du type de panier dans la commande.
     * @param quantite La quantité du type de panier.
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Retourne une représentation textuelle de l'objet.
     * @return Une chaîne représentant l'objet CompoCommande.
     */
    @Override
    public String toString() {
        return "CompoCommande{" +
                "id_commande=" + id_commande +
                ", id_type_panier=" + id_type_panier +
                ", quantite=" + quantite +
                '}';
    }
}
