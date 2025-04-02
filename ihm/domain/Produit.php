<?php

namespace domain;

class Produit
{
    protected $idProduit;
    protected $nomProduit;
    protected $quantite;
    protected $unite;

    public function __construct($idProduit, $nomProduit, $quantite, $unite)
    {
        $this->idProduit = $idProduit;
        $this->nomProduit = $nomProduit;
        $this->quantite = $quantite;
        $this->unite = $unite;
    }

    public function getIdProduit()
    {
        return $this->idProduit;
    }

    public function getNomProduit()
    {
        return $this->nomProduit;
    }

    public function getQuantite()
    {
        return $this->quantite;
    }

    public function getUnite()
    {
        return $this->unite;
    }
}
