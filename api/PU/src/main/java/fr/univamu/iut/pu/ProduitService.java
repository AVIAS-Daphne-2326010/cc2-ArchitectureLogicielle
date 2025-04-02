package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * La classe {@code ProduitService} fournit des services métiers pour gérer les produits.
 * Elle récupère les produits depuis le dépôt {@code ProduitRepositoryInterface} et les transforme
 * en format JSON pour être utilisés dans une API REST.
 * Les méthodes incluent la récupération de tous les produits ou d'un produit spécifique par son identifiant.
 */
@ApplicationScoped
public class ProduitService {

    /**
     * Référence vers le dépôt des produits.
     */
    private ProduitRepositoryInterface produitRepo;

    /**
     * Constructeur par défaut de la classe {@code ProduitService}.
     * Utilisé pour l'injection de dépendance.
     */
    public ProduitService() {}

    /**
     * Constructeur de la classe {@code ProduitService} avec injection du dépôt des produits.
     *
     * @param produitRepo Le dépôt des produits utilisé pour accéder aux données des produits.
     */
    @Inject
    public ProduitService(ProduitRepositoryInterface produitRepo) {
        this.produitRepo = produitRepo;
    }

    /**
     * Récupère tous les produits sous forme de chaîne JSON.
     *
     * @return Une chaîne JSON représentant la liste de tous les produits.
     */
    public String getAllProduitsJSON() {
        ArrayList<Produit> allProduits = produitRepo.getAllProduits();  // Récupère tous les produits du dépôt

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allProduits);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un produit spécifique sous forme de chaîne JSON en utilisant son identifiant.
     *
     * @param idProduit L'identifiant du produit à récupérer.
     * @return Une chaîne JSON représentant le produit demandé, ou {@code null} si le produit n'existe pas.
     */
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
