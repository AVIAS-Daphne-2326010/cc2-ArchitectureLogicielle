<?php

namespace service;
class CommandesChecking
{
    protected $annoncesTxt;

    public function getCommandesTxt()
    {
        return $this->annoncesTxt;
    }

      public function getAllCommandes($data)
    {
        $annonces = $data->getAllCommandes();

        $this->annoncesTxt = array();
        foreach ($annonces as $post) {
            $this->annoncesTxt[] = ['id' => $post->getId(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate()];
        }
    }

    public function getSingleCommande($id, $data)
    {
        $post = $data->getSingleCommande($id);

        $this->annoncesTxt[] = array('id' => $post->getId(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate());
    }
}