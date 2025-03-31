<?php

namespace service;

class UserChecking
{
    public function authenticate($login, $password, $api)
    {
        return ($api->getUser($login, $password) != null);
    }
}