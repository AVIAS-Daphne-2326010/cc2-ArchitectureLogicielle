<?php

namespace gui;

include_once "View.php";

class ViewPaniers extends View
{
    public function __construct($layout, $login, $presenter) {
        parent::__construct($layout, $login);

        $this->title = 'Paniers';

        $this->content = $presenter->getAllPaniersHTML();
    }
}