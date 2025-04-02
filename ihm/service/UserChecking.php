<?php

namespace service;

/**
 * Classe UserChecking
 *
 * Cette classe est utilisée pour gérer l'authentification des utilisateurs en vérifiant
 * leur identifiant (login) et leur mot de passe à travers un système externe (par exemple, une API).
 */
class UserChecking
{
    /**
     * Authentifie un utilisateur en vérifiant son login et mot de passe.
     *
     * Cette méthode utilise l'API fournie pour récupérer les informations de l'utilisateur et
     * vérifier si les identifiants sont valides.
     *
     * @param string $login L'identifiant de l'utilisateur.
     * @param string $password Le mot de passe de l'utilisateur.
     * @param \service\UserAccessInterface $api L'API utilisée pour récupérer les informations de l'utilisateur.
     *
     * @return bool True si l'utilisateur existe et les identifiants sont corrects, sinon False.
     */
    public function authenticate($login, $password, $api)
    {
        return ($api->getUser($login, $password) != null);
    }
}
