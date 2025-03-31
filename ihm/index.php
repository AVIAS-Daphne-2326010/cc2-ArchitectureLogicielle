<?php

// charge et initialise les bibliothèques globales
include_once 'data/UserSqlAccess.php';
include_once 'data/ApiAlternance.php';
include_once 'data/ApiEmploi.php';
include_once 'data/ApiPaniers.php';
include_once 'data/ApiCommandes.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/CommandesChecking.php';
include_once 'service/PaniersChecking.php';
include_once 'service/UserChecking.php';
include_once 'service/UserCreation.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewLogin.php';
include_once 'gui/ViewAnnoncesAlternance.php';
include_once 'gui/ViewCompanyAlternance.php';
include_once 'gui/ViewOffreEmploi.php';
include_once 'gui/ViewPaniers.php';
include_once 'gui/ViewCommandes.php';
include_once 'gui/ViewError.php';
include_once 'gui/ViewCreate.php';

use gui\{
    ViewLogin,
    ViewAnnoncesAlternance,
    ViewCompanyAlternance,
    ViewOffreEmploi,
    ViewError,
    ViewCreate,
    ViewPaniers,
    ViewCommandes,
    Layout};
use control\{Controllers, Presenter};
use data\{ApiAlternance, ApiEmploi, ApiCommandes, UserSqlAccess, ApiPaniers};
use service\{CommandesChecking, PaniersChecking, UserChecking, UserCreation};

$data = null;
try {
    $bd = new PDO('mysql:host=mysql-islem.alwaysdata.net;dbname=islem_annonces_db', 'islem_annonces', 'bJK8jHyz4FRphJ');
    // construction du modèle
    $dataUsers = new UserSqlAccess($bd);
    // ajout de l'api alternance
    $apiAlternance = new ApiAlternance();
    $apiEmploi = new ApiEmploi();
    $apiPanier = new ApiPaniers();
    $apiCommande = new ApiCommandes();

} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\CommandesChecking
$annoncesCheck = new CommandesChecking() ;

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation du cas d'utilisation service\UserCreation
$userCreation = new UserCreation() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($annoncesCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri  and '/index.php/create' != $uri){

    $error = $controller->authenticateAction($userCreation, $userCheck, $dataUsers);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/index.php';

        if( $error == 'creation impossible')
            $redirect = '/index.php/create';
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
elseif ( '/index.php/create' == $uri ) {
    // Affichage du fromulaire de création de compte

    $layout = new Layout("gui/layout.html" );
    $vueCreate = new ViewCreate( $layout );

    $vueCreate->display();
}
elseif ( '/index.php/annoncesAlternance' == $uri ){
    // Affichage de toutes les entreprises offrant de l'alternance

    $controller->annoncesAction($apiAlternance, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnoncesAlternance= new ViewAnnoncesAlternance( $layout,  $_SESSION['login'], $presenter);

    $vueAnnoncesAlternance->display();
}
elseif ( '/index.php/companyAlternance' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une entreprise offrant de l'alternance

    $controller->postAction($_GET['id'], $apiAlternance, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePostAlternance = new ViewCompanyAlternance( $layout,  $_SESSION['login'], $presenter );

    $vuePostAlternance->display();
}
elseif ( '/index.php/offreEmploi' == $uri && isset($_GET['id'])) {
    // Affichage d'une entreprise offrant de l'alternance

    $controller->postAction($_GET['id'], $apiEmploi, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePostAlternance = new ViewOffreEmploi( $layout,  $_SESSION['login'], $presenter );

    $vuePostAlternance->display();
}
elseif ('/index.php/paniers' == $uri) {

    $controller->annoncesAction($apiPanier, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePaniers = new ViewPaniers( $layout,  $_SESSION['login'], $presenter);

    $vuePaniers->display();
}
elseif ('/index.php/commandes' == $uri) {

    $controller->annoncesAction($apiCommande, $annoncesCheck);

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
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}

?>
