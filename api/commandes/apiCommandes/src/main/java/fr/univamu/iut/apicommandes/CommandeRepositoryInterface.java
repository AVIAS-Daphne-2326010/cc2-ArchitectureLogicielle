package fr.univamu.iut.apicommandes;

import java.util.ArrayList;
import java.sql.Date;

public interface CommandeRepositoryInterface {
    public void close();
    public Commande getCommande(int id);
    public ArrayList<Commande> getAllCommandes();
    public boolean updateCommande(int id, String user_name, String relai, Date date);

    public ArrayList<CompoCommande> getCompoCommandes(int id_commande);
    public boolean addCompoCommande(int id_commande, int id_type_panier, int quantite);
    public boolean updateCompoCommande(int id_commande, int id_type_panier, int quantite);
    public boolean removeCompoCommande(int id_commande, int id_type_panier);
}
