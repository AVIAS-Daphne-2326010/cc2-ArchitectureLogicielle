<?php
namespace gui;

/**
 * Classe abstraite View représentant une vue générique dans l'architecture propre.
 * Les classes dérivées peuvent définir leur propre titre et contenu à afficher.
 */
abstract class View
{
    /**
     * @var string Le titre de la page.
     */
    protected $title = '';

    /**
     * @var string Le contenu de la page.
     */
    protected $content = '';

    /**
     * @var Layout L'instance de la classe Layout utilisée pour l'affichage de la page.
     */
    protected $layout;

    /**
     * Constructeur de la classe View.
     *
     * @param Layout $layout L'instance de la classe Layout utilisée pour afficher la page.
     */
    public function __construct($layout)
    {
        $this->layout = $layout;
    }

    /**
     * Affiche la vue en utilisant le modèle (template) et les propriétés de titre et de contenu.
     *
     * Cette méthode appelle la méthode `display` de la classe `Layout` pour rendre la page.
     *
     * @return void
     */
    public function display()
    {
        $this->layout->display($this->title, "", $this->content);
    }
}
