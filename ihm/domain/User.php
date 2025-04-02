<?php

namespace domain;
class User
{
    protected $login;
    protected $password;
    protected $name;
    protected $firstName;
    protected  $date;

    public function __construct($login, $password, $name, $firstName, $date )
    {
        $this->login = $login;
        $this->password = $password;
        $this->name = $name;
        $this->firstName = $firstName;
        $this->date = $date;
    }

    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getFirstName()
    {
        return $this->firstName;
    }

    /**
     * @return mixed
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @return mixed
     */
    public function getLogin()
    {
        return $this->login;
    }
}