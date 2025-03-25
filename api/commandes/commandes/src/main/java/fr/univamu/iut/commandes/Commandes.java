package fr.univamu.iut.commandes;

import java.time.LocalDate;

public class Commandes {
    protected int id_commande;
    protected String user_name;
    protected String relai;
    protected LocalDate date;

    public Commandes(){}

    public Commandes(int id_commande, String user_name, String relai, LocalDate date) {
        this.id_commande = id_commande;
        this.user_name = user_name;
        this.relai = relai;
        this.date = date;
    }

    public int getIdCommande() {
        return id_commande;
    }

    public String getUserName() {
        return user_name;
    }

    public String getRelai() {
        return relai;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setIdCommande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setRelai(String relai) {
        this.relai = relai;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commandes{" + "id_commande=" + id_commande
                + ", user_name=" + user_name
                + ", relai=" + relai
                + ", date=" + date + '}';
    }
}
