<?php

namespace domain;

class Commande
{
    protected $id;
    protected $date;
    protected $paniers;
    protected $relai;
    protected $userName;

    public function __construct($id, $date, $paniers, $relai, $userName)
    {
        $this->id = $id;
        $this->date = $date;
        $this->paniers = $paniers;
        $this->relai = $relai;
        $this->userName = $userName;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getDate()
    {
        return $this->date;
    }

    public function getPaniers()
    {
        return $this->paniers;
    }

    public function getRelai()
    {
        return $this->relai;
    }

    public function getUserName()
    {
        return $this->userName;
    }
}
