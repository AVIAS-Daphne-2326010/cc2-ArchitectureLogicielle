package fr.univamu.iut.panier;

import java.util.List;

public interface CompoPanierRepositoryInterface {
    void close();

    void ajouterProduit(CompoPanier compo);

    void supprimerProduit(int idPanier, int idProduit);

    List<CompoPanier> findByPanier(int idPanier);
}
