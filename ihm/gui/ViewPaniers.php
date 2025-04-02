<?php
namespace gui;

use control\Presenter;
include_once "control/Presenter.php";

include_once "View.php";

/**
 * Classe ViewPaniers représentant la vue des paniers.
 * Cette classe est responsable de l'affichage des paniers disponibles à l'utilisateur.
 */
class ViewPaniers extends View
{
    /**
     * Constructeur de la classe ViewPaniers.
     * Initialise la vue avec la liste des paniers à afficher.
     *
     * @param Layout $layout L'instance de la classe Layout utilisée pour afficher la page.
     * @param string $login Le nom d'utilisateur actuellement connecté.
     * @param Presenter $presenter L'instance du Presenter pour récupérer les données des paniers.
     */
    public function __construct($layout, $login, $presenter) {
        // Appel du constructeur parent (classe View)
        parent::__construct($layout, $login);

        // Définition du titre de la page
        $this->title = 'Paniers';

        // Récupération du contenu des paniers via le Presenter et définition de la propriété $content
        $this->content = $presenter->getAllPaniersHTML();
    }
}
