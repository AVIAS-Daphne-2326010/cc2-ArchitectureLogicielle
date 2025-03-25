package fr.univamu.iut.commandes;

import java.time.LocalDate;
import java.util.*;

public interface CommandesRepositoryInterface {
    public void close();
    public Commandes getCommandes(int id_commande);
    public ArrayList<Commandes> getAllCommandes();
    public void ajouterCommande(Commandes commande);
    public void supprimerCommande(int id_commande);
    public boolean updateCommande(int id_commande, String user_name, String relai, LocalDate date);

}
