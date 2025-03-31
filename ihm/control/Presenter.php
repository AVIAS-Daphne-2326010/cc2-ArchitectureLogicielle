<?php
namespace control;
class Presenter
{
    protected $annoncesCheck;
    protected $paniersCheck;

    public function __construct($annoncesCheck, $paniersCheck)
    {
        $this->annoncesCheck = $annoncesCheck;
        $this->paniersCheck = $paniersCheck;
    }

    public function getAllCommandesHTML() {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $content = '<h1>Liste des commandes</h1>';
            $content .= '<ul>';
            foreach ($this->annoncesCheck->getCommandesTxt() as $post) {
                $content .= ' <li>';
                $content .= '<p>' . $post['title'] . '</p>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getAllPaniersHTML() {
        $content = null;
        if ($this->paniersCheck->getPaniersTxt() != null) {
            $content = '<h1>Liste des paniers</h1>';
            $content .= '<ul>';
            foreach ($this->paniersCheck->getPaniersTxt() as $post) {
                $content .= ' <li>';
                $content .= '<p>' . $post['title'] . '</p>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }
}