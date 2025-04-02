<?php

// charge et initialise les bibliothèques globales
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

// ajout des apis
$apiPanier = new ApiPaniers();
$apiCommande = new ApiCommandes();
$apiUserProduct = new ApiUtilisateursProduits();

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\CommandesChecking
$commandesCheck = new CommandesChecking() ;

// intialisation du cas d'utilisation service\CommandesCreate
$commandesCreate = new CommandesCreate();

// intialisation du cas d'utilisation service\PaniersChecking
$paniersCheck = new PaniersChecking();

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($commandesCheck, $paniersCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri  and '/index.php/create' != $uri){

    $error = $controller->authenticateAction($userCheck, $apiUserProduct);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected' or $error = 'Erreur systeme - contacter admin')
            $redirect = '/index.php';
    }
}

// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/' == $uri || '/index.php' == $uri || '/index.php/logout' == $uri) {
    // affichage de la page de connexion

    session_destroy();
    $layout = new Layout("gui/layout.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}
elseif ('/index.php/paniers' == $uri) {

    $controller->paniersAction($apiPanier, $paniersCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePaniers = new ViewPaniers( $layout,  $_SESSION['login'], $presenter);

    $vuePaniers->display();
}
elseif ('/index.php/commandes' == $uri) {
    if (!isset($_POST['produitsId'])) {
        $controller->commandesAction($apiCommande, $commandesCheck, $_SESSION['login'], $commandesCreate);
    }
    else {
        $controller->commandesAction($apiCommande, $commandesCheck, $_SESSION['login'], $commandesCreate, $_POST['produitsId']);
    }

    $layout = new Layout("gui/layoutLogged.html" );

    $vueCommandes = new ViewCommandes( $layout,  $_SESSION['login'], $presenter);

    $vueCommandes->display();
}
elseif ( '/index.php/error' == $uri ){
    // Affichage d'un message d'erreur

    $layout = new Layout("gui/layout.html" );
    $vueError = new ViewError( $layout, $error, $redirect );

    $vueError->display();
}
else {
    header('Status: 404 Not Found');
    echo '<html lang="en"><body><h1>My Page NotFound</h1></body></html>';
}

?>
