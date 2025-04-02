<?php
namespace gui;

include_once "View.php";

/**
 * Classe ViewError représentant une vue d'erreur.
 * Cette classe est responsable de l'affichage des messages d'erreur et de la redirection après un délai.
 */
class ViewError extends View
{
    /**
     * Constructeur de la classe ViewError.
     * Affiche un message d'erreur et redirige l'utilisateur après un délai spécifié.
     *
     * @param Layout $layout L'instance de la classe Layout utilisée pour afficher la page.
     * @param string $error Le message d'erreur à afficher à l'utilisateur.
     * @param string $redirect L'URL vers laquelle l'utilisateur sera redirigé après 5 secondes.
     */
    public function __construct($layout, $error, $redirect)
    {
        // Appel du constructeur parent (classe View)
        parent::__construct($layout);

        // En-tête HTTP pour rediriger l'utilisateur après 5 secondes
        header("refresh:5;url=$redirect");

        // Affichage du message d'erreur
        echo $error;
    }
}
