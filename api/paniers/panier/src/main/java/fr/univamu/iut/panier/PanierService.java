package fr.univamu.iut.panier;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

/**
 * Service utilisé pour récupérer et manipuler les informations des paniers
 */
public class PanierService {

    private PanierRepositoryInterface panierRepo;

    public PanierService(PanierRepositoryInterface panierRepo) {
        this.panierRepo = panierRepo;
    }

    public String getAllPaniersJSON() {
        List<Panier> paniers = panierRepo.findAll();
        String result = null;

        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(paniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getPanierJSON(int id) {
        Panier panier = panierRepo.findById(id);
        String result = null;

        if (panier != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(panier);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean updatePanier(int id, Panier panier) {
        panier.setId_type_panier(id);
        return panierRepo.update(panier) != null;
    }

    public void deletePanier(int id) {
        panierRepo.delete(id);
    }
}
