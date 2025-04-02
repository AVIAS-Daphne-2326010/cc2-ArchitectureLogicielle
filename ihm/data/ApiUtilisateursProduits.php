<?php

namespace data;

use domain\User;
use domain\Produit;
include_once "domain/User.php";
include_once "domain/Produit.php";

use service\UserAccessInterface;
include_once "service/UserAccessInterface.php";

/**
 * Classe ApiUtilisateursProduits permettant d'accéder aux utilisateurs via une API.
 * Implémente l'interface UserAccessInterface.
 */
class ApiUtilisateursProduits implements UserAccessInterface
{
    /**
     * Récupère un utilisateur en fonction de son login et de son mot de passe via l'API.
     *
     * @param string $login Le login de l'utilisateur.
     * @param string $password Le mot de passe de l'utilisateur.
     *
     * @return User|null L'objet User correspondant aux informations de l'utilisateur, ou null si l'utilisateur n'est pas trouvé.
     */
    public function getUser($login, $password)
    {
        $user = null;

        $apiUrl = "http://localhost:8080/PU-1.0-SNAPSHOT/api/users";
        $jsonData = file_get_contents($apiUrl);

        if ($jsonData === false) {
            return null;
        }

        $users = json_decode($jsonData, true);

        foreach ($users as $userData) {
            if ($userData['login'] === $login && $userData['password'] === $password) {
                return new User(
                    $userData['login'],
                    $userData['password'],
                    $userData['lastName'],
                    $userData['firstName'],
                    $userData['date']
                );
            }
        }

        return null;
    }
}
