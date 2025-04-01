package fr.univamu.iut.panier;

/**
 * Représente la composition d'un panier, contenant un produit et sa quantité.
 */
public class CompoPanier {

    private int idTypePanier;
    private int idProduit;
    private int quantite;

    /**
     * Constructeur par défaut.
     */
    public CompoPanier() {}

    /**
     * Constructeur avec paramètres.
     *
     * @param idTypePanier l'identifiant du type de panier
     * @param idProduit l'identifiant du produit
     * @param quantite la quantité du produit dans le panier
     */
    public CompoPanier(int idTypePanier, int idProduit, int quantite) {
        this.idTypePanier = idTypePanier;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    /**
     * Obtient l'identifiant du type de panier.
     *
     * @return l'identifiant du type de panier
     */
    public int getIdTypePanier() {
        return idTypePanier;
    }

    /**
     * Définit l'identifiant du type de panier.
     *
     * @param idTypePanier le nouvel identifiant du type de panier
     */
    public void setIdTypePanier(int idTypePanier) {
        this.idTypePanier = idTypePanier;
    }

    /**
     * Obtient l'identifiant du produit.
     *
     * @return l'identifiant du produit
     */
    public int getIdProduit() {
        return idProduit;
    }

    /**
     * Définit l'identifiant du produit.
     *
     * @param idProduit le nouvel identifiant du produit
     */
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Obtient la quantité du produit dans le panier.
     *
     * @return la quantité du produit
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définit la quantité du produit dans le panier.
     *
     * @param quantite la nouvelle quantité du produit
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
