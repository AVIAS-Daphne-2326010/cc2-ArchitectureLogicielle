package fr.univamu.iut.pu;

import java.util.Date;

/**
 * La classe {@code Produit} représente un produit avec ses informations de base,
 * telles que l'identifiant, le nom, la quantité et l'unité.
 * Elle permet de gérer ces informations à travers des méthodes d'accès et de modification.
 */
public class Produit {

    /**
     * Identifiant unique du produit.
     */
    private int idProduit;

    /**
     * Nom du produit.
     */
    private String nomProduit;

    /**
     * Quantité disponible du produit.
     */
    private int quantite;

    /**
     * Unité de mesure du produit (ex: "kg", "litre").
     */
    private String unite;

    /**
     * Constructeur de la classe {@code Produit}.
     *
     * @param idProduit Identifiant du produit.
     * @param nomProduit Nom du produit.
     * @param quantite Quantité du produit disponible.
     * @param unite Unité de mesure du produit.
     */
    public Produit(int idProduit, String nomProduit, int quantite, String unite) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.unite = unite;
    }

    /**
     * Retourne l'identifiant du produit.
     *
     * @return L'identifiant du produit.
     */
    public int getIdProduit() {
        return idProduit;
    }

    /**
     * Définit l'identifiant du produit.
     *
     * @param idProduit L'identifiant à assigner au produit.
     */
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Retourne le nom du produit.
     *
     * @return Le nom du produit.
     */
    public String getNomProduit() {
        return nomProduit;
    }

    /**
     * Définit le nom du produit.
     *
     * @param nomProduit Le nom à assigner au produit.
     */
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    /**
     * Retourne la quantité du produit.
     *
     * @return La quantité du produit.
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définit la quantité du produit.
     *
     * @param quantite La quantité à assigner au produit.
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Retourne l'unité de mesure du produit.
     *
     * @return L'unité du produit.
     */
    public String getUnite() {
        return unite;
    }

    /**
     * Définit l'unité de mesure du produit.
     *
     * @param unite L'unité à assigner au produit.
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet {@code Produit}.
     *
     * @return Une chaîne de caractères représentant les informations du produit.
     */
    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", quantite=" + quantite +
                ", unite='" + unite + '\'' +
                '}';
    }
}
