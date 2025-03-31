<?php

namespace service;
class ProduitsChecking
{
    protected $produitTxt;

    public function getProduitTxt()
    {
        return $this->produitTxt;
    }

    public function getProduit($id, $api)
    {
        // TODO
        // $post = $api->getProduit($id);

        // $this->annoncesTxt[] = array('id' => $post->getId(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate());
    }
}