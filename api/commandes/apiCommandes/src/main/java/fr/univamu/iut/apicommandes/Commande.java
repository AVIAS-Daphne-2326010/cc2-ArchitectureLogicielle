package fr.univamu.iut.apicommandes;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;

public class Commande {
     protected int id;
     protected String user_name;
     protected String relai;
     protected Date date;
     protected ArrayList<CompoCommande> paniers;

     public Commande() {
         this.paniers = new ArrayList<>();
     }

     public Commande(int id, String user_name, String relai, Date date) {
         this.id = id;
         this.user_name = user_name;
         this.relai = relai;
         this.date = date;
         this.paniers = new ArrayList<>();
     }

     // Getters et Setters
     public ArrayList<CompoCommande> getPaniers() {
         return paniers;
     }

     public void setPaniers(ArrayList<CompoCommande> paniers) {
         this.paniers = paniers;
     }

     public void addPanier(CompoCommande panier) {
         this.paniers.add(panier);
     }

     public int getId() {
         return id;
     }

     public String getUser_name() {
         return user_name;
     }

     public String getRelai() {
         return relai;
     }

     public Date getDate() {
         return date;
     }

     public void setId(int id) {
         this.id = id;
     }

     public void setUser_name(String user_name) {
         this.user_name = user_name;
     }

     public void setRelai(String relai) {
         this.relai = relai;
     }

     public void setDate(Date date) {
         this.date = date;
     }

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
