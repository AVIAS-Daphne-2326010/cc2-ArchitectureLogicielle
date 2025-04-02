<?php
namespace control;

class Controllers
{

    public function  authenticateAction($userCheck, $dataUsers){

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
                    $_SESSION['login'] = $_POST['login'] ;
                }
            }
            else{
                // retourne une erreur si la personne ne passe pas par le forumlaire de création ou de connexion
                $error = 'not connected';
                return $error;
            }

        }
    }

    public function paniersAction($api, $paniersCheck)
    {
         $paniersCheck->getAllPaniers($api);
    }

    public function commandesAction($api, $commandesCheck, $userName, $commandesCreate, $produitsId = null)
    {
        if ( $produitsId === null ) {
            $commandesCheck->getAllCommandes($api, $userName);
        }
        else {
            $commandesCreate->createCommande($api, $userName, $produitsId);
        }
    }
}