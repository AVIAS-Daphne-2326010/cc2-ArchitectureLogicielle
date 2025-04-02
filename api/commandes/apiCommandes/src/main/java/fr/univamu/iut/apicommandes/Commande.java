package fr.univamu.iut.apicommandes;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Classe représentant une commande dans le système.
 * Une commande est caractérisée par un identifiant, un nom d'utilisateur,
 * un point relais et une date, ainsi qu'une liste de paniers la composant.
 */
public class Commande {
    protected int id;
    protected String user_name;
    protected String relai;
    protected String date;
    protected ArrayList<CompoCommande> paniers;

    /**
     * Constructeur par défaut initialisant une liste vide de paniers.
     */
    public Commande() {
        this.paniers = new ArrayList<>();
    }

    /**
     * Constructeur avec paramètres.
     * @param id Identifiant unique de la commande
     * @param user_name Nom de l'utilisateur ayant passé la commande
     * @param relai Point relais pour la livraison
     * @param date Date de récupération de la commande
     */
    public Commande(int id, String user_name, String relai, String date) {
        this.id = id;
        this.user_name = user_name;
        this.relai = relai;
        this.date = date;
        this.paniers = new ArrayList<>();
    }

    /**
     * Retourne la liste des paniers composant la commande.
     * @return ArrayList des CompoCommande
     */
    public ArrayList<CompoCommande> getPaniers() {
        return paniers;
    }

    /**
     * Définit la liste des paniers de la commande.
     * @param paniers Nouvelle liste de paniers
     */
    public void setPaniers(ArrayList<CompoCommande> paniers) {
        this.paniers = paniers;
    }

    /**
     * Ajoute un panier à la commande.
     * @param panier Panier à ajouter
     */
    public void addPanier(CompoCommande panier) {
        this.paniers.add(panier);
    }

    /**
     * Retourne l'identifiant de la commande.
     * @return Identifiant de la commande
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom de l'utilisateur.
     * @return Nom de l'utilisateur
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Retourne le point relais de livraison.
     * @return Nom du point relais
     */
    public String getRelai() {
        return relai;
    }

    /**
     * Retourne la date de récupération de la commande.
     * @return Date de récupération de la commande
     */
    public String getDate() {
        return date;
    }

    /**
     * Définit l'identifiant de la commande.
     * @param id Nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définit le nom de l'utilisateur.
     * @param user_name Nouveau nom d'utilisateur
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Définit le point relais de livraison.
     * @param relai Nouveau point relais
     */
    public void setRelai(String relai) {
        this.relai = relai;
    }

    /**
     * Définit la date de récupération de la commande.
     * @param date Nouvelle date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retourne une représentation textuelle de la commande.
     * @return String décrivant la commande et ses paniers
     */
    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", relai='" + relai + '\'' +
                ", date=" + date +
                ", paniers=" + paniers +
                '}';
    }
}