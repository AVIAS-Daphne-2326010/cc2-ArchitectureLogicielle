<?php
namespace data;

use domain\Panier;
include_once "domain/Panier.php";

use domain\Produit;
use service\PaniersAccessInterface;
include_once "service/PaniersAccessInterface.php";

/**
 * Classe ApiPaniers permettant d'accéder aux paniers via une API.
 * Implémente l'interface PaniersAccessInterface.
 */
class ApiPaniers implements PaniersAccessInterface
{
    /**
     * Récupère tous les paniers via l'API.
     *
     * @return Panier[]|null Un tableau d'objets Panier, ou null en cas d'échec.
     */
    public function getAllPaniers()
    {
        $apiUrl = "http://localhost:8080/panier-1.0-SNAPSHOT/api/panier";
        $jsonData = file_get_contents($apiUrl);

        if ($jsonData === false) {
            return null;
        }

        $paniersData = json_decode($jsonData, true);
        $paniers = [];

        foreach ($paniersData as $panierData) {
            $produits = [];
            foreach ($panierData['produits'] as $produitData) {
                $produit = new Produit(
                    $produitData['idProduit'],
                    $produitData['nomProduit'],
                    $produitData['quantite'],
                    $produitData['unite']
                );
                if ($produit) {
                    $produits[] = $produit;
                }
            }

            $paniers[] = new Panier(
                $panierData['id_type_panier'],
                $panierData['mise_a_jour'],
                $panierData['n_panier_dispo'],
                $panierData['prix'],
                $produits
            );
        }

        return $paniers;
    }
}
