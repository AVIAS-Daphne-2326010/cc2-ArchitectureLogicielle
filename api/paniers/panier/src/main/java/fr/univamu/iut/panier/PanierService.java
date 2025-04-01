package fr.univamu.iut.panier;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

/**
 * Service utilisé pour récupérer et manipuler les informations des paniers.
 * Il fournit des méthodes pour récupérer, modifier et supprimer des paniers.
 */
public class PanierService {

    private PanierRepositoryInterface panierRepo;

    /**
     * Constructeur du service PanierService.
     *
     * @param panierRepo l'interface du repository permettant d'accéder aux données des paniers
     */
    public PanierService(PanierRepositoryInterface panierRepo) {
        this.panierRepo = panierRepo;
    }

    /**
     * Récupère tous les paniers et les retourne sous forme de JSON.
     *
     * @return une chaîne JSON représentant la liste des paniers
     */
    public String getAllPaniersJSON() {
        List<Panier> paniers = panierRepo.getAllPaniers();
        String result = null;

        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(paniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un panier spécifique par son identifiant et le retourne sous forme de JSON.
     *
     * @param id l'identifiant du panier
     * @return une chaîne JSON représentant le panier, ou null si le panier n'existe pas
     */
    public String getPanierJSON(int id) {
        Panier panier = panierRepo.getPanier(id);
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

    /**
     * Met à jour les informations d'un panier existant.
     *
     * @param id     l'identifiant du panier à mettre à jour
     * @param panier le panier contenant les nouvelles informations
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updatePanier(int id, Panier panier) {
        panier.setId_type_panier(id);
        return panierRepo.update(panier) != null;
    }

    /**
     * Supprime un panier en fonction de son identifiant.
     *
     * @param id l'identifiant du panier à supprimer
     */
    public void deletePanier(int id) {
        panierRepo.delete(id);
    }
}
