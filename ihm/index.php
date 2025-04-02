<?php

// Chargement et initialisation des bibliothèques globales
include_once 'data/ApiPaniers.php';
include_once 'data/ApiCommandes.php';
include_once 'data/ApiUtilisateursProduits.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/CommandesChecking.php';
include_once 'service/CommandesCreate.php';
include_once 'service/PaniersChecking.php';
include_once 'service/UserChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewLogin.php';
include_once 'gui/ViewPaniers.php';
include_once 'gui/ViewCommandes.php';
include_once 'gui/ViewError.php';

use gui\{
    ViewLogin,
    ViewError,
    ViewPaniers,
    ViewCommandes,
    Layout};
use control\{Controllers, Presenter};
use data\{ApiCommandes, ApiPaniers, ApiUtilisateursProduits};
use service\{CommandesChecking, CommandesCreate, PaniersChecking, UserChecking};

// Initialisation des APIs
$apiPanier = new ApiPaniers(); // API pour gérer les paniers
$apiCommande = new ApiCommandes(); // API pour gérer les commandes
$apiUserProduct = new ApiUtilisateursProduits(); // API pour gérer les utilisateurs et produits

// Initialisation du controller
$controller = new Controllers();

// Initialisation des cas d'utilisation (services)
$commandesCheck = new CommandesChecking(); // Service pour vérifier les commandes
$commandesCreate = new CommandesCreate(); // Service pour créer des commandes
$paniersCheck = new PaniersChecking(); // Service pour vérifier les paniers
$userCheck = new UserChecking(); // Service pour vérifier l'authentification des utilisateurs

// Initialisation du Presenter avec l'accès aux données de CommandesChecking et PaniersChecking
$presenter = new Presenter($commandesCheck, $paniersCheck);

// Récupération du chemin de l'URL demandée par le navigateur
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// Définition de la durée de vie de la session (une heure)
ini_set('session.gc_maxlifetime', 3600); // Durée maximale de vie de la session (en secondes)
session_set_cookie_params(3600); // Durée de vie des cookies de session
session_start(); // Démarre la session PHP

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri  and '/index.php/create' != $uri){

    // Authentification de l'utilisateur via l'action de contrôle
    $error = $controller->authenticateAction($userCheck, $apiUserProduct);

    // Gestion des erreurs d'authentification
    if( $error != null )
    {
        $uri='/index.php/error'; // Redirection vers la page d'erreur
        if( $error == 'bad login or pwd' or $error == 'not connected' or $error = 'Erreur systeme - contacter admin')
            $redirect = '/index.php'; // Redirection après erreur
    }
}

// Route la requête en fonction de l'URL demandée
// Affiche la page de connexion si l'URL correspond à la page d'accueil
if ( '/' == $uri || '/index.php' == $uri || '/index.php/logout' == $uri) {
    session_destroy();
    $layout = new Layout("gui/layout.html");
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}
// Affiche la page des paniers
elseif ('/index.php/paniers' == $uri) {

    $controller->paniersAction($apiPanier, $paniersCheck);

    $layout = new Layout("gui/layoutLogged.html");
    $vuePaniers = new ViewPaniers( $layout,  $_SESSION['login'], $presenter);

    $vuePaniers->display(); // Affiche la vue des paniers
}
// Affiche la page des commandes
elseif ('/index.php/commandes' == $uri) {
    if (isset($_POST['paniersId'])) {
        // Crée une commande si un panier est sélectionné
        $controller->commandesAction($apiCommande, $commandesCheck, $_SESSION['login'], $commandesCreate, $_POST['paniersId']);
    }
    else {
        // Affiche les commandes sans création
        $controller->commandesAction($apiCommande, $commandesCheck, $_SESSION['login'], $commandesCreate);
    }

    $layout = new Layout("gui/layoutLogged.html");

    $vueCommandes = new ViewCommandes( $layout,  $_SESSION['login'], $presenter);

    $vueCommandes->display();
}
// Affiche une page d'erreur
elseif ( '/index.php/error' == $uri ){
    // Affichage d'un message d'erreur
    $layout = new Layout("gui/layout.html");
    $vueError = new ViewError( $layout, $error, $redirect );

    $vueError->display();
}
// Si aucune des routes ci-dessus ne correspond, affiche une erreur 404
else {
    header('Status: 404 Not Found');
    echo '<html lang="en"><body><h1>My Page NotFound</h1></body></html>';
}

?>
