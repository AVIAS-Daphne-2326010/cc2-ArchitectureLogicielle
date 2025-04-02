<?php

namespace service;

class CommandesCreate
{
    public function createCommande($api, $userName, $produitsId) {
        return ($api->createCommande($userName, $produitsId) != false);
    }
}