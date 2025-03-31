<?php

namespace data;

use domain\User;
include_once "domain/User.php";

use service\ProduitsAccessInterface;
use service\UserAccessInterface;
include_once "service/ProduitsAccessInterface.php";
include_once "service/UserAccessInterface.php";

class ApiUtilisateursProduits implements UserAccessInterface, ProduitsAccessInterface
{
    public function getUser($login, $password)
    {
        $user = null;

        return new User( 'test', 'test', 'test', 'test', '10-10-2005' );

        if ( $row = $result->fetch() )
            $user = new User( $row['login'] , $row['password'], $row['name'], $row['firstName'], $row['date'] );

        $result->closeCursor();

        return $user;
    }

    public function getProduit($id)
    {
        // TODO: Implement getProduit() method.
    }
}