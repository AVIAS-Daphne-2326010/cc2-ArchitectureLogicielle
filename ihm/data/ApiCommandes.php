<?php

namespace data;

use domain\Commande;
include_once "domain/Commande.php";

use service\CommandesAccessInterface;
include_once "service/CommandesAccessInterface.php";

use service\CommandesCreateInterface;
include_once "service/CommandesCreateInterface.php";

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

    public function createCommande($userName, $produitsId) {
        $url = "http://localhost:8080/apiCommandes-1.0-SNAPSHOT/api/commandes/user/{$userName}";

        $data = [
            'user_name' => $userName,
            'produits_id' => $produitsId
        ];

        $jsonData = json_encode($data);

        $options = [
            'http' => [
                'header'  => "Content-type: application/json\r\n",
                'method'  => 'POST',
                'content' => $jsonData
            ]
        ];

        $context = stream_context_create($options);

        // TODO quand le côté api sera implémenté
        //file_get_contents($url, false, $context);
        echo '<p>Fonctionnalité pas implémentée</p>';
        exit();

        return true;
    }
}