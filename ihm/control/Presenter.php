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
            $prix = 0;

            foreach ($this->annoncesCheck->getCommandesTxt() as $commande) {
                $content .= '<div class="commande">';
                $content .= '<h3>Commande #' . $commande['id'] . '</h3>';
                $content .= '<p><strong>Relai:</strong> ' . $commande['relai'] . '</p>';
                $content .= '<p><strong>Date:</strong> ' . $commande['date'] . '</p>';

                if (!empty($commande['paniers'])) {
                    $content .= '<p><strong>Paniers:</strong></p>';
                    $content .= '<ul>';

                    foreach ($commande['paniers'] as $panier) {
                        $content .= '<li>Panier # ' . $panier['id_type_panier'];
                        $prix += $panier['prix'];

                        if (!empty($panier['produits'])) {
                            $content .= '<ul>';
                            foreach ($panier['produits'] as $produit) {
                                $content .= '<li>' . $produit['quantite'] . ' ' . $produit['unite'] . ' of ' . $produit['nomProduit'] . '</li>';
                            }
                            $content .= '</ul>';
                        }
                        $content .= '</li>';
                    }

                    $content .= '</ul>';
                }
                $content .= '<p><strong>Prix :</strong>' . $prix . '€' . '</p>';
                $content .= '</div>';
            }
            $content .= '</div>';
        } else {
            $content = '<p>Aucune commande trouvée.</p>';
        }
        return $content;
    }


    public function getAllPaniersHTML() {
        $paniers = $this->paniersCheck->getPaniersTxt();
        if ($paniers === null) {
            return '<p>Aucun panier trouvé.</p>';
        }

        $content = '<h2>Liste des paniers</h2>';
        $content .= '<form method="POST" action="http://localhost:8081/index.php/commandes">';
        $content .= '<ul>';

        foreach ($paniers as $panier) {
            $content .= '<li>';
            $content .= '<h3>Panier ' . htmlspecialchars($panier['id']) . '</h3>';
            $content .= '<p>Mise à jour: ' . htmlspecialchars($panier['mise_a_jour']) . '</p>';
            $content .= '<p>Quantité disponible: ' . htmlspecialchars($panier['n_panier_dispo']) . '</p>';
            $content .= '<p>Prix: ' . htmlspecialchars($panier['prix']) . ' €</p>';
            $content .= '<p>Produits:</p><ul>';

            foreach ($panier['produits'] as $produit) {
                $content .= '<li>' . htmlspecialchars($produit->getNomProduit()) . ' (' . htmlspecialchars($produit->getQuantite()) . ' ' . htmlspecialchars($produit->getUnite()) . ')</li>';
            }

            $content .= '</ul>';

            $content .= '<label>';
            $content .= '<input type="checkbox" name="paniersId[]" value="' . htmlspecialchars($panier['id']) . '">';
            $content .= ' Sélectionner ce panier';
            $content .= '</label>';

            $content .= '</li>';
        }

        $content .= '</ul>';
        $content .= '<button type="submit">Commander</button>';
        $content .= '</form>';

        return $content;
    }
}