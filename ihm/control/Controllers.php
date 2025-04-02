<?php
namespace control;

class Controllers
{
    /**
     * Authentifie un utilisateur en vérifiant ses informations de connexion.
     *
     * @param object $userCheck L'objet responsable de l'authentification de l'utilisateur.
     * @param object $dataUsers Tableau contenant les données des utilisateurs.
     *
     * @return string|null Retourne une erreur en cas d'échec d'authentification, sinon rien.
     */
    public function authenticateAction($userCheck, $dataUsers)
    {
        // Si l'utilisateur n'a pas de session ouverte
        if( !isset($_SESSION['login']) ) {

            // Si la page d'origine est le formulaire de connexion ou de création de compte
            if( isset($_POST['login']) && isset($_POST['password']) )
            {
                // Vérification de l'authentification si la précédente page était le formulaire de connexion
                if( !$userCheck->authenticate($_POST['login'], $_POST['password'], $dataUsers) )
                {
                    // retourne une erreur si le compte n'est pas enregistré
                    $error = 'bad login or pwd';
                    return $error;
                }
                // Enregistrement des informations de session après une authentification réussie
                else {
                    $_SESSION['login'] = $_POST['login'];
                }
            }
            else{
                // retourne une erreur si la personne ne passe pas par le formulaire de création ou de connexion
                $error = 'not connected';
                return $error;
            }
        }
    }

    /**
     * Récupère tous les paniers d'un utilisateur.
     *
     * @param object $api L'API qui interagit avec la base de données ou le service externe.
     * @param object $paniersCheck L'objet responsable de la gestion des paniers.
     *
     * @return void
     */
    public function paniersAction($api, $paniersCheck)
    {
        $paniersCheck->getAllPaniers($api);
    }

    /**
     * Gère les commandes d'un utilisateur. Si un identifiant de produit est fourni, une nouvelle commande est créée.
     *
     * @param object $api L'API qui interagit avec le service externe.
     * @param object $commandesCheck L'objet responsable de la gestion des commandes.
     * @param string $userName Le nom de l'utilisateur pour lequel les commandes sont récupérées.
     * @param object $commandesCreate L'objet responsable de la création des commandes.
     * @param string|null $produitsId L'identifiant d'un produit, utilisé pour créer une commande (optionnel).
     *
     * @return void
     */
    public function commandesAction($api, $commandesCheck, $userName, $commandesCreate, $produitsId = null)
    {
        if ( $produitsId === null ) {
            // Récupère toutes les commandes de l'utilisateur
            $commandesCheck->getAllCommandes($api, $userName);
        }
        else {
            // Crée une nouvelle commande pour l'utilisateur
            $commandesCreate->createCommande($api, $userName, $produitsId);
        }
    }
}