<?php
namespace service;

/**
 * Interface UserAccessInterface
 *
 * Cette interface définit la méthode nécessaire pour accéder aux informations d'un utilisateur
 * en fonction de son identifiant (login) et de son mot de passe. Elle est utilisée pour interagir
 * avec des systèmes de gestion des utilisateurs (par exemple, une API ou une base de données).
 */
interface UserAccessInterface
{
    /**
     * Récupère les informations d'un utilisateur à partir de son login et mot de passe.
     *
     * Cette méthode permet de récupérer un utilisateur spécifique à partir de ses informations d'authentification.
     * Si les informations sont correctes, l'utilisateur correspondant est retourné.
     *
     * @param string $login L'identifiant de l'utilisateur.
     * @param string $password Le mot de passe de l'utilisateur.
     *
     * @return \domain\User|null L'utilisateur correspondant ou null si aucun utilisateur n'est trouvé.
     */
    public function getUser($login, $password);
}
