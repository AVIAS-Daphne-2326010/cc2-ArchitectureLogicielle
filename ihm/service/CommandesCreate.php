<?php

namespace service;

class CommandesCreate
{
    public function createCommande($userName, $produitsId, $api) {
        return ($api->createCommande($userName, $produitsId) != false);
    }
}