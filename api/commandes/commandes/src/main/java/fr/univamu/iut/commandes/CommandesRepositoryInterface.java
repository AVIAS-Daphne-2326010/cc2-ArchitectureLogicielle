package fr.univamu.iut.commandes;

import java.time.LocalDate;
import java.util.*;

public interface CommandesRepositoryInterface {
    public void close();
    public Commandes getCommandes(int id_commande);
    public ArrayList<Commandes> getAllCommandes();
}
