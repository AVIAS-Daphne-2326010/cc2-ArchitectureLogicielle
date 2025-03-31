<?php

namespace service;
interface CommandesAccessInterface
{
    public function getAllCommandes();

    public function getSingleCommande($id);
}