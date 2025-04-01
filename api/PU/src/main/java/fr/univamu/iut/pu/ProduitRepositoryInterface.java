package fr.univamu.iut.pu;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public interface ProduitRepositoryInterface {
    void close();

    Produit getProduit(int idProduit);

    ArrayList<Produit> getAllProduits();

    boolean deleteProduit(int idProduit);

    boolean updateProduit(int idProduit, String nomProduit, int quantite, String unite);

    boolean addProduit(Produit produit);
}
