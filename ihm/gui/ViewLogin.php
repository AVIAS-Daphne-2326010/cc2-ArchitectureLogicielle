<?php
namespace gui;

include_once "View.php";

/**
 * Classe ViewLogin représentant la vue de la page de connexion.
 * Cette classe est responsable de l'affichage du formulaire de connexion.
 */
class ViewLogin extends View
{
    /**
     * Constructeur de la classe ViewLogin.
     * Initialise la vue avec le formulaire de connexion.
     *
     * @param Layout $layout L'instance de la classe Layout utilisée pour afficher la page.
     */
    public function __construct($layout)
    {
        // Appel du constructeur parent (classe View)
        parent::__construct($layout);

        // Définition du titre de la page
        $this->title = 'Connexion';

        // Définition du contenu de la page avec le formulaire de connexion
        $this->content = '
            <form method="post" action="/index.php/paniers">
                <label for="login"> Votre identifiant </label> :
                <input type="text" name="login" id="login" placeholder="defaut" maxlength="12" required />
                <br />
                <label for="password"> Votre mot de passe </label> :
                <input type="password" name="password" id="password" minlength="4" required />
        
                <input type="submit" value="Envoyer">
            </form>
            ';
    }
}
