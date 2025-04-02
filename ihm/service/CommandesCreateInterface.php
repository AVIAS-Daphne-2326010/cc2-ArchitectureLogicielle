<?php

namespace service;

/**
 * Interface CommandesCreateInterface
 *
 * Cette interface définit les méthodes nécessaires pour créer une commande.
 * Elle est utilisée par les classes qui implémentent la logique de création de commande dans un système.
 */
interface CommandesCreateInterface
{
    /**
     * Crée une commande pour un utilisateur donné avec les produits spécifiés.
     *
     * Cette méthode est responsable de la création d'une commande en utilisant les données fournies.
     *
     * @param string $userName Le nom d'utilisateur pour lequel la commande doit être créée.
     * @param array $produitsId Un tableau contenant les identifiants des produits à inclure dans la commande.
     *
     * @return bool Retourne `true` si la commande a été créée avec succès, sinon `false`.
     */
    public function createCommande($userName, $produitsId);
}
