<?php

namespace gui;

include_once "View.php";

class ViewCommandes extends View
{
    public function __construct($layout, $login, $presenter) {
        parent::__construct($layout, $login);

        $this->title = 'Commandes';

        $this->content = $presenter->getAllEmploiHTML();
    }
}