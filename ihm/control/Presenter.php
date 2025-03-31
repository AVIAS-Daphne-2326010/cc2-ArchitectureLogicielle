<?php
namespace control;
class Presenter
{
    protected $annoncesCheck;

    public function __construct($annoncesCheck)
    {
        $this->annoncesCheck = $annoncesCheck;
    }


    public function getAllEmploiHTML() {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $content = '<h1>List of Jobs</h1>';
            foreach ($this->annoncesCheck->getCommandesTxt() as $post) {
                $content .= ' <li>';
                $content .= '<a href="/index.php/offreEmploi?id=' . $post['id'] . '">' . $post['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }
    public function getAllAlternanceHTML() {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $content = '<h1>List of Companies</h1>';
            foreach ($this->annoncesCheck->getCommandesTxt() as $post) {
                $content .= ' <li>';
                $content .= '<a href="/index.php/companyAlternance?id=' . $post['id'] . '">' . $post['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getAllAnnoncesHTML()
    {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $content = '<h1>List of Posts</h1>  <ul>';
            foreach ($this->annoncesCheck->getCommandesTxt() as $post) {
                $content .= ' <li>';
                $content .= '<a href="/index.php/post?id=' . $post['id'] . '">' . $post['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getCurrentPostHTML()
    {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $post = $this->annoncesCheck->getCommandesTxt()[0];

            $content = '<h1>' . $post['title'] . '</h1>';
            $content .= '<div class="date">' . $post['date'] . '</div>';
            $content .= '<div class="body">' . $post['body'] . '</div>';
        }
        return $content;
    }
}