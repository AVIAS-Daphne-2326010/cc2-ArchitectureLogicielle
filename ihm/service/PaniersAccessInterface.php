<?php
namespace service;

/**
 * Interface PaniersAccessInterface
 *
 * Cette interface définit la méthode nécessaire pour accéder aux paniers disponibles.
 * Elle est utilisée par les classes qui implémentent la logique pour récupérer des informations sur les paniers dans un système.
 */
interface PaniersAccessInterface
{
    /**
     * Récupère tous les paniers disponibles.
     *
     * Cette méthode permet d'obtenir la liste de tous les paniers disponibles dans le système.
     *
     * @return array|null Retourne un tableau de paniers si des paniers sont trouvés, sinon `null` si aucun panier n'est disponible.
     */
    public function getAllPaniers();
}
