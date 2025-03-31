<?php

namespace service;
class PaniersChecking
{
    protected $annoncesTxt;

    public function getPaniersTxt()
    {
        return $this->annoncesTxt;
    }

    public function getAllPaniers($data)
    {
        $annonces = $data->getAllAnnonces();

        $this->annoncesTxt = array();
        foreach ($annonces as $post) {
            $this->annoncesTxt[] = ['id' => $post->getId(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate()];
        }
    }
}