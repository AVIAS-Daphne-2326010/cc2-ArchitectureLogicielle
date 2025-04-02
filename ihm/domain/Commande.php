<?php
namespace domain;

/**
 * Classe Commande représentant une commande effectuée par un utilisateur.
 */
class Commande
{
    /**
     * @var int L'ID de la commande.
     */
    protected $id;

    /**
     * @var string La date de la commande.
     */
    protected $date;

    /**
     * @var array Liste des paniers associés à cette commande.
     */
    protected $paniers;

    /**
     * @var string Le relais où la commande sera récupérée.
     */
    protected $relai;

    /**
     * @var string Le nom de l'utilisateur ayant passé la commande.
     */
    protected $userName;

    /**
     * Constructeur de la classe Commande.
     *
     * @param int $id L'ID de la commande.
     * @param string $date La date de la commande.
     * @param array $paniers Les paniers associés à la commande.
     * @param string $relai Le relais où la commande sera récupérée.
     * @param string $userName Le nom de l'utilisateur ayant passé la commande.
     */
    public function __construct($id, $date, $paniers, $relai, $userName)
    {
        $this->id = $id;
        $this->date = $date;
        $this->paniers = $paniers;
        $this->relai = $relai;
        $this->userName = $userName;
    }

    /**
     * Récupère l'ID de la commande.
     *
     * @return int L'ID de la commande.
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Récupère la date de la commande.
     *
     * @return string La date de la commande.
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Récupère les paniers associés à cette commande.
     *
     * @return array Les paniers associés à la commande.
     */
    public function getPaniers()
    {
        return $this->paniers;
    }

    /**
     * Récupère le relais où la commande sera récupérée.
     *
     * @return string Le relais où la commande sera récupérée.
     */
    public function getRelai()
    {
        return $this->relai;
    }

    /**
     * Récupère le nom de l'utilisateur ayant passé la commande.
     *
     * @return string Le nom de l'utilisateur.
     */
    public function getUserName()
    {
        return $this->userName;
    }
}