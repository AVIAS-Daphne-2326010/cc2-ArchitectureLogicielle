<?php

namespace domain;

/**
 * Classe Panier représentant un panier d'achat.
 */
class Panier
{
    /**
     * @var string L'ID du type de panier.
     */
    protected $idTypePanier;

    /**
     * @var string La date de la dernière mise à jour du panier.
     */
    protected $miseAJour;

    /**
     * @var int Le nombre de paniers disponibles.
     */
    protected $nPanierDispo;

    /**
     * @var float Le prix total du panier.
     */
    protected $prix;

    /**
     * @var Produit[] Liste des produits dans le panier.
     */
    protected $produits;

    /**
     * Constructeur de la classe Panier.
     *
     * @param string $idTypePanier L'ID du type de panier.
     * @param string $miseAJour La date de mise à jour du panier.
     * @param int $nPanierDispo Le nombre de paniers disponibles.
     * @param float $prix Le prix du panier.
     * @param Produit[] $produits Liste des produits dans le panier (facultatif, défaut : tableau vide).
     */
    public function __construct($idTypePanier, $miseAJour, $nPanierDispo, $prix, $produits = [])
    {
        $this->idTypePanier = $idTypePanier;
        $this->miseAJour = $miseAJour;
        $this->nPanierDispo = $nPanierDispo;
        $this->prix = $prix;
        $this->produits = $produits;
    }

    /**
     * Récupère l'ID du type de panier.
     *
     * @return string L'ID du type de panier.
     */
    public function getIdTypePanier()
    {
        return $this->idTypePanier;
    }

    /**
     * Récupère la date de mise à jour du panier.
     *
     * @return string La date de mise à jour.
     */
    public function getMiseAJour()
    {
        return $this->miseAJour;
    }

    /**
     * Récupère le nombre de paniers disponibles.
     *
     * @return int Le nombre de paniers disponibles.
     */
    public function getNPanierDispo()
    {
        return $this->nPanierDispo;
    }

    /**
     * Récupère le prix du panier.
     *
     * @return float Le prix du panier.
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Récupère la liste des produits dans le panier.
     *
     * @return Produit[] La liste des produits dans le panier.
     */
    public function getProduits()
    {
        return $this->produits;
    }
}