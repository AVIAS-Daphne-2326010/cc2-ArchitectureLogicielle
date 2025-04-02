<?php

namespace service;

/**
 * Classe CommandesChecking
 *
 * Cette classe est responsable de la gestion et de la validation des commandes récupérées via l'API.
 * Elle permet de stocker et de manipuler les données relatives aux commandes d'un utilisateur.
 */
class CommandesChecking
{
    /**
     * @var array|null Contient la liste des commandes sous forme de tableau associatif.
     */
    protected $commandesTxt;

    /**
     * Récupère les commandes sous forme de tableau associatif.
     *
     * @return array|null Un tableau des commandes ou null si aucune commande n'est présente.
     */
    public function getCommandesTxt()
    {
        return $this->commandesTxt;
    }

    /**
     * Récupère toutes les commandes d'un utilisateur via l'API et les formate pour être utilisées.
     *
     * @param object $api L'instance de l'API utilisée pour récupérer les commandes.
     * @param string $userName Le nom d'utilisateur pour lequel récupérer les commandes.
     *
     * @return void Cette méthode ne retourne rien. Elle modifie la propriété $commandesTxt pour stocker les commandes récupérées.
     */
    public function getAllCommandes($api, $userName)
    {
        // Récupération des commandes via l'API
        $commandes = $api->getAllCommandes($userName);

        // Si aucune commande n'est récupérée, on retourne une liste vide
        if (empty($commandes)) {
            return [];
        }

        // Initialisation du tableau pour stocker les commandes formatées
        $this->commandesTxt = array();

        // Formatage des commandes récupérées de l'API
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
