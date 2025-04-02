package fr.univamu.iut.panier;

/**
 * Représente la composition d'un panier, contenant un produit et sa quantité.
 */
public class CompoPanier {

    private int idTypePanier;
    private int idProduit;
    private int quantite;
    private String nomProduit;
    private String unite;

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

    public int getIdTypePanier() {
        return idTypePanier;
    }

    public void setIdTypePanier(int idTypePanier) {
        this.idTypePanier = idTypePanier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
