package fr.univamu.iut.panier;

public class CompoPanier {

    private int idTypePanier;
    private int idProduit;
    private int quantite;

    public CompoPanier() {}

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
}
