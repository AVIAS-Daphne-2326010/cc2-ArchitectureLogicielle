<?php
namespace gui;

use control\Presenter;
include_once "control/Presenter.php";

include_once "View.php";

/**
 * Classe ViewCommandes représentant la vue des commandes dans l'interface utilisateur.
 * Cette classe étend la classe abstraite View et est responsable de l'affichage des commandes.
 */
class ViewCommandes extends View
{
    /**
     * Constructeur de la classe ViewCommandes.
     * Initialise le titre de la page et le contenu des commandes à afficher.
     *
     * @param Layout $layout L'instance de la classe Layout utilisée pour afficher la page.
     * @param string $login Le nom de l'utilisateur connecté.
     * @param Presenter $presenter L'instance de la classe Presenter qui génère le contenu des commandes.
     */
    public function __construct($layout, $login, $presenter) {
        // Appel du constructeur parent (classe View)
        parent::__construct($layout);

        // Définition du titre de la page
        $this->title = 'Commandes';

        // Définition du contenu de la page en utilisant le Presenter pour obtenir la liste des commandes
        $this->content = $presenter->getAllCommandesHTML();
    }
}
