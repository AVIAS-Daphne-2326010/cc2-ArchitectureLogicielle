<?php

namespace service;
class CommandesChecking
{
    protected $commandesTxt;

    public function getCommandesTxt()
    {
        return $this->commandesTxt;
    }

    public function getAllCommandes($api, $userName)
    {
        $commandes = $api->getAllCommandes($userName);

        if (empty($commandes)) {
            return [];
        }

        $this->commandesTxt = array();

        foreach ($commandes as $commande) {
            $this->commandesTxt[] = [
                'id' => $commande->getId(),
                'relai' => $commande->getRelai(),
                'userName' => $commande->getUserName(),
                'date' => $commande->getDate(),
                'paniers' => $commande->getPaniers()
            ];
        }
    }
}