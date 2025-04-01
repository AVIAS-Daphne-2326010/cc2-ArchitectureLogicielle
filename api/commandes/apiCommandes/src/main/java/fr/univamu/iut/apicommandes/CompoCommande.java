package fr.univamu.iut.apicommandes;

import java.sql.Date;

public class CompoCommande {
    protected int id_commande;
    protected int id_type_panier;
    protected int quantite;

    public CompoCommande() {
    }

    public CompoCommande(int id_commande, int id_type_panier, int quantite) {
        this.id_commande = id_commande;
        this.id_type_panier = id_type_panier;
        this.quantite = quantite;
    }

    // Getters et Setters
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_type_panier() {
        return id_type_panier;
    }

    public void setId_type_panier(int id_type_panier) {
        this.id_type_panier = id_type_panier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "CompoCommande{" +
                "id_commande=" + id_commande +
                ", id_type_panier=" + id_type_panier +
                ", quantite=" + quantite +
                '}';
    }
}