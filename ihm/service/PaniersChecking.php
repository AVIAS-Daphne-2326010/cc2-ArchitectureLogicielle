<?php

namespace service;
class PaniersChecking
{
    protected $paniersTxt;

    public function getPaniersTxt()
    {
        return $this->paniersTxt;
    }

    public function getAllPaniers($api)
    {
        $annonces = $api->getAllPaniers();

        $this->paniersTxt = array();
        foreach ($annonces as $post) {
            $this->paniersTxt[] = ['id' => $post->getId(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate()];
        }
    }
}