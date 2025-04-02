<?php

namespace domain;

/**
 * Classe User représentant un utilisateur.
 */
class User
{
    /**
     * @var string Le login de l'utilisateur.
     */
    protected $login;

    /**
     * @var string Le mot de passe de l'utilisateur.
     */
    protected $password;

    /**
     * @var string Le nom de l'utilisateur.
     */
    protected $name;

    /**
     * @var string Le prénom de l'utilisateur.
     */
    protected $firstName;

    /**
     * @var string La date d'enregistrement de l'utilisateur.
     */
    protected $date;

    /**
     * Constructeur de la classe User.
     *
     * @param string $login Le login de l'utilisateur.
     * @param string $password Le mot de passe de l'utilisateur.
     * @param string $name Le nom de l'utilisateur.
     * @param string $firstName Le prénom de l'utilisateur.
     * @param string $date La date d'enregistrement de l'utilisateur.
     */
    public function __construct($login, $password, $name, $firstName, $date )
    {
        $this->login = $login;
        $this->password = $password;
        $this->name = $name;
        $this->firstName = $firstName;
        $this->date = $date;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     *
     * @return string Le mot de passe de l'utilisateur.
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * Récupère le nom de l'utilisateur.
     *
     * @return string Le nom de l'utilisateur.
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     *
     * @return string Le prénom de l'utilisateur.
     */
    public function getFirstName()
    {
        return $this->firstName;
    }

    /**
     * Récupère la date d'enregistrement de l'utilisateur.
     *
     * @return string La date d'enregistrement de l'utilisateur.
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Récupère le login de l'utilisateur.
     *
     * @return string Le login de l'utilisateur.
     */
    public function getLogin()
    {
        return $this->login;
    }
}