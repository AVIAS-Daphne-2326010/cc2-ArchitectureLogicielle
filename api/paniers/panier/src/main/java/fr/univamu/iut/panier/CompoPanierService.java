package fr.univamu.iut.panier;

import java.util.List;

public class CompoPanierService {

    private CompoPanierRepositoryInterface compoRepo;

    public CompoPanierService(CompoPanierRepositoryInterface compoRepo) {
        this.compoRepo = compoRepo;
    }

    public void ajouterProduit(int idPanier, int idProduit, int quantite) {
        CompoPanier compo = new CompoPanier(idPanier, idProduit, quantite);
        compoRepo.ajouterProduit(compo);
    }

    public void supprimerProduit(int idPanier, int idProduit) {
        compoRepo.supprimerProduit(idPanier, idProduit);
    }

    public String getProduitsDuPanierJSON(int idPanier) {
        List<CompoPanier> compoPaniers = compoRepo.findByPanier(idPanier);
        return toJson(compoPaniers);
    }

    private String toJson(List<CompoPanier> compoPaniers) {
        try (jakarta.json.bind.Jsonb jsonb = jakarta.json.bind.JsonbBuilder.create()) {
            return jsonb.toJson(compoPaniers);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
