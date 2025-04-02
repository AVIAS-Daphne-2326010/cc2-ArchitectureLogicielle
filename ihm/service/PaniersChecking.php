<?php

namespace service;
class PaniersChecking
{
    protected $paniersTxt;

    public function getPaniersTxt()
    {
        return $this->paniersTxt;
    }

    public function getAllPaniers($api)
    {
        $paniers = $api->getAllPaniers();

        $this->paniersTxt = array();
        foreach ($paniers as $panier) {
            $this->paniersTxt[] = [
                'id' => $panier->getIdTypePanier(),
                'mise_a_jour' => $panier->getMiseAJour(),
                'n_panier_dispo' => $panier->getNPanierDispo(),
                'prix' => $panier->getPrix(),
                'produits' => $panier->getProduits()
            ];
        }
    }

}