<?php
namespace service;

/**
 * Classe PaniersChecking
 *
 * Cette classe est responsable de la gestion des paniers en vérifiant leur disponibilité et en récupérant
 * les informations détaillées des paniers à partir d'une API.
 * Elle récupère les paniers via une API, puis les structure sous une forme plus simple pour être utilisée
 * dans d'autres parties de l'application.
 */
class PaniersChecking
{
    /**
     * @var array Contient les paniers récupérés et formatés
     */
    protected $paniersTxt;

    /**
     * Récupère les paniers formatés.
     *
     * Cette méthode retourne le tableau des paniers après avoir été récupéré et formaté par `getAllPaniers()`.
     *
     * @return array Retourne un tableau de paniers formatés.
     */
    public function getPaniersTxt()
    {
        return $this->paniersTxt;
    }

    /**
     * Récupère tous les paniers et les formate.
     *
     * Cette méthode interroge l'API pour récupérer tous les paniers disponibles. Ensuite, elle les transforme
     * sous un format plus simple en extrayant uniquement les informations nécessaires.
     *
     * @param $api L'objet API qui gère la récupération des paniers.
     *
     * @return void
     */
    public function getAllPaniers($api)
    {
        // Récupération des paniers depuis l'API
        $paniers = $api->getAllPaniers();

        // Initialisation du tableau formaté
        $this->paniersTxt = [];

        // Formatage des paniers récupérés
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
