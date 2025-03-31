<?php
namespace gui;

include_once "View.php";

class ViewAnnoncesAlternance extends View
{
    public function __construct($layout, $login, $presenter)
    {
        parent::__construct($layout, $login);

        $this->title= 'Alternance';

        $this->content = $presenter->getAllAlternanceHTML();
    }
}