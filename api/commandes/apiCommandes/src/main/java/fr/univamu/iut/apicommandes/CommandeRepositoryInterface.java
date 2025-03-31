package fr.univamu.iut.apicommandes;

import java.util.ArrayList;
import java.sql.Date;

public interface CommandeRepositoryInterface {
    public void close();
    public Commande getCommande(int id);
    public ArrayList<Commande> getAllCommandes();
    public boolean updateCommande(int id, String user_name, String relai, Date date);


}
