<?php

namespace domain;

/**
 * Classe Produit représentant un produit dans un panier ou une commande.
 */
class Produit
{
    /**
     * @var int L'ID du produit.
     */
    protected $idProduit;

    /**
     * @var string Le nom du produit.
     */
    protected $nomProduit;

    /**
     * @var int La quantité du produit.
     */
    protected $quantite;

    /**
     * @var string L'unité de mesure du produit (par exemple, 'kg', 'L', 'unité').
     */
    protected $unite;

    /**
     * Constructeur de la classe Produit.
     *
     * @param int $idProduit L'ID du produit.
     * @param string $nomProduit Le nom du produit.
     * @param int $quantite La quantité du produit.
     * @param string $unite L'unité de mesure du produit.
     */
    public function __construct($idProduit, $nomProduit, $quantite, $unite)
    {
        $this->idProduit = $idProduit;
        $this->nomProduit = $nomProduit;
        $this->quantite = $quantite;
        $this->unite = $unite;
    }

    /**
     * Récupère l'ID du produit.
     *
     * @return int L'ID du produit.
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Récupère le nom du produit.
     *
     * @return string Le nom du produit.
     */
    public function getNomProduit()
    {
        return $this->nomProduit;
    }

    /**
     * Récupère la quantité du produit.
     *
     * @return int La quantité du produit.
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * Récupère l'unité de mesure du produit.
     *
     * @return string L'unité de mesure du produit.
     */
    public function getUnite()
    {
        return $this->unite;
    }
}
