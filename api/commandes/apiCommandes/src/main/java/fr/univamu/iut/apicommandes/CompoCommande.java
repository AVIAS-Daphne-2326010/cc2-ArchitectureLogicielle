package fr.univamu.iut.apicommandes;

import java.util.List;

/**
 * Représente un composant d'une commande, associant une commande à un type de panier et une quantité.
 */
public class CompoCommande {
    protected int id_commande;
    protected int quantite;
    protected int id_type_panier;
    protected String miseAjour;
    protected int nombrePaniersDispo;
    protected float prix;
    protected List<Produit> produits;

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
     * Obtient la date de mise à jour.
     * @return La date de mise à jour.
     */
    public String getMiseAjour() {
        return miseAjour;
    }

    /**
     * Définit la date de mise à jour.
     * @param miseAjour La date de mise à jour.
     */
    public void setMiseAjour(String miseAjour) {
        this.miseAjour = miseAjour;
    }

    /**
     * Obtient le nombre de paniers disponibles.
     * @return Le nombre de paniers disponibles.
     */
    public int getNombrePaniersDispo() {
        return nombrePaniersDispo;
    }

    /**
     * Définit le nombre de paniers disponibles.
     * @param nombrePaniersDispo Le nombre de paniers disponibles.
     */
    public void setNombrePaniersDispo(int nombrePaniersDispo) {
        this.nombrePaniersDispo = nombrePaniersDispo;
    }

    /**
     * Obtient le prix du panier.
     * @return Le prix du panier.
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Définit le prix du panier.
     * @param prix Le prix du panier.
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    /**
     * Obtient la liste des produits dans le panier.
     * @return La liste des produits.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Définit la liste des produits dans le panier.
     * @param produits La liste des produits.
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Retourne une représentation textuelle de l'objet.
     * @return Une chaîne représentant l'objet CompoCommande.
     */
    @Override
    public String toString() {
        return "CompoCommande{" +
                "id_commande=" + id_commande +
                ", quantite=" + quantite +
                ", id_type_panier=" + id_type_panier +
                ", miseAjour='" + miseAjour + '\'' +
                ", nombrePaniersDispo=" + nombrePaniersDispo +
                ", prix=" + prix +
                ", produits=" + produits +
                '}';
    }
}