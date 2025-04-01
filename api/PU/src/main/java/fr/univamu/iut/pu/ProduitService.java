package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@ApplicationScoped
public class ProduitService {

    private ProduitRepositoryInterface produitRepo;

    public ProduitService() {}

    @Inject
    public ProduitService(ProduitRepositoryInterface produitRepo) {
        this.produitRepo = produitRepo;
    }

    public String getAllProduitsJSON() {
        ArrayList<Produit> allProduits = produitRepo.getAllProduits();  // Fetch all produits from the repository

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allProduits);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getProduitJSON(int idProduit) {
        Produit myProduit = produitRepo.getProduit(idProduit);

        if (myProduit != null) {

            String result = null;
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduit);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return result;
        } else {
            return null;
        }
    }
}
