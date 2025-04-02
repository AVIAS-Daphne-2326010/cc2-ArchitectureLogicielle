<?php
namespace data;

use domain\Commande;
include_once "domain/Commande.php";

use service\CommandesAccessInterface;
include_once "service/CommandesAccessInterface.php";

use service\CommandesCreateInterface;
include_once "service/CommandesCreateInterface.php";

/**
 * Classe ApiCommandes permettant d'accéder et de créer des commandes via une API.
 * Implémente les interfaces CommandesAccessInterface et CommandesCreateInterface.
 */
class ApiCommandes implements CommandesAccessInterface, CommandesCreateInterface
{
    /**
     * Récupère toutes les commandes d'un utilisateur via l'API.
     *
     * @param string $userName Le nom de l'utilisateur pour lequel récupérer les commandes.
     *
     * @return Commande[]|null Un tableau d'objets Commande, ou null en cas d'échec.
     */
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

    /**
     * Crée une nouvelle commande pour un utilisateur via l'API.
     *
     * @param string $userName Le nom de l'utilisateur pour lequel créer la commande.
     * @param array $produitsId Tableau des identifiants des produits à inclure dans la commande.
     *
     * @return bool Retourne true si la commande a été créée (bien que la fonctionnalité ne soit pas implémentée).
     */
    public function createCommande($userName, $produitsId)
    {
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

        // TODO quand le côté API sera implémenté
        //file_get_contents($url, false, $context);
        echo '<p>Fonctionnalité pas implémentée</p>';
        exit();

        return true;
    }
}
