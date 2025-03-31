<?php

namespace gui;

include_once "View.php";

class ViewPaniers extends View
{
    public function __construct($layout, $login, $presenter) {
        parent::__construct($layout, $login);

        $this->title = 'Paniers';

        $this->content = '<h2>Bienvenue ' . $login . '</h2>';
        $this->content .= $presenter->getAllEmploiHTML();
    }
}