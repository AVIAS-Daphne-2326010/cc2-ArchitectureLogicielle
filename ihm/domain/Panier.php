<?php

namespace domain;

class Panier
{
    protected $idTypePanier;
    protected $miseAJour;
    protected $nPanierDispo;
    protected $prix;
    protected $produits;

    public function __construct($idTypePanier, $miseAJour, $nPanierDispo, $prix, $produits = [])
    {
        $this->idTypePanier = $idTypePanier;
        $this->miseAJour = $miseAJour;
        $this->nPanierDispo = $nPanierDispo;
        $this->prix = $prix;
        $this->produits = $produits;
    }

    public function getIdTypePanier()
    {
        return $this->idTypePanier;
    }

    public function getMiseAJour()
    {
        return $this->miseAJour;
    }

    public function getNPanierDispo()
    {
        return $this->nPanierDispo;
    }

    public function getPrix()
    {
        return $this->prix;
    }

    public function getProduits()
    {
        return $this->produits;
    }
}