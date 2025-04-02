<?php
namespace gui;

/**
 * Classe Layout représentant la gestion d'une page avec un modèle (template).
 */
class Layout
{
    /**
     * @var string Le chemin du fichier de modèle (template) utilisé pour générer la page.
     */
    protected $templateFile;

    /**
     * Constructeur de la classe Layout.
     *
     * @param string $templateFile Le chemin du fichier de modèle (template) à utiliser.
     */
    public function __construct($templateFile)
    {
        $this->templateFile = $templateFile;
    }

    /**
     * Affiche la page en remplissant les espaces réservés (%title%, %connexion%, %content%) dans le modèle.
     *
     * @param string $title Le titre de la page.
     * @param string $connexion Le contenu représentant la connexion (par exemple, un message de connexion ou de déconnexion).
     * @param string $content Le contenu principal de la page.
     *
     * @return void
     */
    public function display($title, $connexion, $content)
    {
        // Charger le fichier de modèle
        $page = file_get_contents($this->templateFile);

        // Remplacer les espaces réservés dans le modèle par les valeurs fournies
        $page = str_replace(['%title%', '%connexion%', '%content%'], [$title, $connexion, $content], $page);

        // Afficher la page finale
        echo $page;
    }
}
