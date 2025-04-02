<?php

namespace data;

use domain\Commande;
include_once "domain/Commande.php";

use service\CommandesAccessInterface;
include_once "service/CommandesAccessInterface.php";

use service\CommandesCreateInterface;
include_once "domain/CommandesCreateInterface.php";

class ApiCommandes implements CommandesAccessInterface, CommandesCreateInterface
{
    public function getAllCommandes($userName): ?array
    {
        $url = "http://localhost:8080/apiCommandes-1.0-SNAPSHOT/api/commandes/user/{$userName}";

        $json = file_get_contents($url);

        if ($json === false) {
            return null;
        }

        $data = json_decode($json, true);

        $commandes = [];

        foreach ($data as $item) {
            $paniers = $item['paniers'];
            $commande = new Commande(
                $item['id'],
                $item['date'],
                $paniers,
                $item['relai'],
                $item['user_name']
            );

            $commandes[] = $commande;
        }

        return $commandes;
    }

    public function createCommandes($userName, $produitsId) {
        $url = "http://localhost:8080/apiCommandes-1.0-SNAPSHOT/api/commandes/user/{$userName}";
        $data = ['produitsId' => $produitsId];

        $options = [
            'http' => [
                'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
                'method'  => 'POST',
                'content' => http_build_query($data)
            ]
        ];
        $context = stream_context_create($options);
        file_get_contents($url, false, $context);

        return true;
    }
}