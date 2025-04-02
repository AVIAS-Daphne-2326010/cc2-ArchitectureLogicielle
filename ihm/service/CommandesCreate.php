<?php

namespace service;

/**
 * Classe CommandesCreate
 *
 * Cette classe est responsable de la création de commandes via l'API.
 * Elle permet d'envoyer des données à l'API pour créer une commande pour un utilisateur donné.
 */
class CommandesCreate
{
    /**
     * Crée une commande en envoyant les données à l'API.
     *
     * Cette méthode appelle l'API pour créer une commande en utilisant le nom d'utilisateur et les identifiants des produits fournis.
     * Elle retourne un booléen qui indique si la création de la commande a réussi ou non.
     *
     * @param object $api L'instance de l'API utilisée pour créer la commande.
     * @param string $userName Le nom d'utilisateur pour lequel créer la commande.
     * @param array $produitsId Un tableau contenant les identifiants des produits à inclure dans la commande.
     *
     * @return bool Retourne `true` si la commande a été créée avec succès, sinon `false`.
     */
    public function createCommande($api, $userName, $produitsId)
    {
        // Appel à la méthode de l'API pour créer la commande
        return ($api->createCommande($userName, $produitsId) != false);
    }
}
