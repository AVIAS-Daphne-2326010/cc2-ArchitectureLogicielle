<?php
namespace service;

/**
 * Interface CommandesAccessInterface
 *
 * Cette interface définit la méthode pour accéder à toutes les commandes d'un utilisateur.
 */
interface CommandesAccessInterface
{
    /**
     * Récupère toutes les commandes d'un utilisateur spécifié par son nom d'utilisateur.
     *
     * @param string $userName Le nom d'utilisateur pour lequel récupérer les commandes.
     *
     * @return array|null Un tableau contenant les commandes de l'utilisateur ou null si aucune commande n'est trouvée.
     */
    public function getAllCommandes($userName);
}
