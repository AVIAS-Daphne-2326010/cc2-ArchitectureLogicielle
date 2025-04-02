<?php
namespace control;
class Presenter
{
    protected $annoncesCheck;
    protected $paniersCheck;

    public function __construct($annoncesCheck, $paniersCheck)
    {
        $this->annoncesCheck = $annoncesCheck;
        $this->paniersCheck = $paniersCheck;
    }

    public function getAllCommandesHTML() {
        $content = null;
        if ($this->annoncesCheck->getCommandesTxt() != null) {
            $content = '<h2>Liste des commandes</h2>';
            $content .= '<div class="commandes-list">';
            foreach ($this->annoncesCheck->getCommandesTxt() as $commande) {
                $content .= '<div class="commande">';
                $content .= '<h3>Commande #' . $commande['id'] . '</h3>';
                $content .= '<p><strong>Relai:</strong> ' . $commande['relai'] . '</p>';
                $content .= '<p><strong>Client:</strong> ' . $commande['userName'] . '</p>';
                $content .= '<p><strong>Date:</strong> ' . $commande['date'] . '</p>';

                if (!empty($commande['paniers'])) {
                    $content .= '<p><strong>Paniers:</strong></p>';
                    $content .= '<ul>';
                    print_r($commande);
                    foreach ($commande['paniers'] as $panier) {
                        $content .= '<li>Panier#' . $panier . '</li>';
                    }
                    $content .= '</ul>';
                }

                $content .= '</div>';
            }
            $content .= '</div>';
        } else {
            $content = '<p>Aucune commande trouvée.</p>';
        }
        return $content;
    }

    public function getAllPaniersHTML() {
        $content = null;
        if ($this->paniersCheck->getPaniersTxt() != null) {
            $content = '<h2>Liste des paniers</h2>';
            $content .= '<ul>';
            foreach ($this->paniersCheck->getPaniersTxt() as $panier) {
                $content .= '<li>';
                $content .= '<h3>Panier ' . $panier['id'] . '</h3>';
                $content .= '<p>Mise à jour: ' . $panier['mise_a_jour'] . '</p>';
                $content .= '<p>Quantité disponible: ' . $panier['n_panier_dispo'] . '</p>';
                $content .= '<p>Prix: ' . $panier['prix'] . ' €</p>';
                $content .= '<p>Produits:</p><ul>';

                if (is_array($panier['produits'])) {
                    foreach ($panier['produits'] as $produit) {
                        $content .= '<li>' . $produit->getNomProduit() . ' (' . $produit->getQuantite() . ' ' . $produit->getUnite() . ')</li>';
                    }
                } else {
                    $produit = $panier['produits'];
                    $content .= '<li>' . $produit->getNomProduit() . ' (' . $produit->getQuantite() . ' ' . $produit->getUnite() . ')</li>';
                }

                $content .= '</ul>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        else {
            return '<p>Aucune panier trouvé.</p>';
        }
    }
}