package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service pour la gestion des commandes.
 */
public class CommandeService {

    private CommandeRepositoryInterface commandeRepo;

    /**
     * Constructeur du service de commande.
     *
     * @param commandeRepo Le repository des commandes.
     */
    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    /**
     * Récupère toutes les commandes sous forme de JSON.
     *
     * @return Une chaîne JSON représentant toutes les commandes.
     */
    public String getAllCommandesJSON() {
        ArrayList<Commande> allCommandes = commandeRepo.getAllCommandes();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère une commande spécifique sous forme de JSON.
     *
     * @param id L'identifiant de la commande.
     * @return Une chaîne JSON représentant la commande, ou null si elle n'existe pas.
     */
    public String getCommandeJSON(int id) {
        String result = null;
        Commande mycommande = commandeRepo.getCommande(id);

        if (mycommande != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(mycommande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Crée une nouvelle commande.
     *
     * @param commande La commande à ajouter.
     * @return true si l'ajout a réussi, sinon false.
     */
    public boolean createCommande(Commande commande) {
        return commandeRepo.createCommande(commande);
    }

    /**
     * Supprime une commande.
     *
     * @param id L'identifiant de la commande à supprimer.
     * @return true si la suppression a réussi, sinon false.
     */
    public boolean deleteCommande(int id) {
        return commandeRepo.deleteCommande(id);
    }

    /**
     * Met à jour une commande existante.
     *
     * @param id       L'identifiant de la commande.
     * @param commande La commande contenant les nouvelles informations.
     * @return true si la mise à jour a réussi, sinon false.
     */
    public boolean updateCommande(int id, Commande commande) {
        return commandeRepo.updateCommande(id, commande.user_name, commande.relai, commande.date);
    }

    /**
     * Récupère les composants d'une commande sous forme de JSON.
     *
     * @param id_commande L'identifiant de la commande.
     * @return Une chaîne JSON représentant les composants de la commande.
     */
    public String getCompoCommandesJSON(int id_commande) {
        ArrayList<CompoCommande> compoCommandes = commandeRepo.getCompoCommandes(id_commande);
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(compoCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Ajoute un composant à une commande.
     *
     * @param id_commande    L'identifiant de la commande.
     * @param id_type_panier L'identifiant du type de panier.
     * @param quantite       La quantité à ajouter.
     * @return true si l'ajout a réussi, sinon false.
     */
    public boolean addCompoCommande(int id_commande, int id_type_panier, int quantite) {
        return commandeRepo.addCompoCommande(id_commande, id_type_panier, quantite);
    }

    /**
     * Met à jour un composant d'une commande.
     *
     * @param id_commande    L'identifiant de la commande.
     * @param id_type_panier L'identifiant du type de panier.
     * @param quantite       La nouvelle quantité.
     * @return true si la mise à jour a réussi, sinon false.
     */
    public boolean updateCompoCommande(int id_commande, int id_type_panier, int quantite) {
        return commandeRepo.updateCompoCommande(id_commande, id_type_panier, quantite);
    }

    /**
     * Supprime un composant d'une commande.
     *
     * @param id_commande    L'identifiant de la commande.
     * @param id_type_panier L'identifiant du type de panier.
     * @return true si la suppression a réussi, sinon false.
     */
    public boolean removeCompoCommande(int id_commande, int id_type_panier) {
        return commandeRepo.removeCompoCommande(id_commande, id_type_panier);
    }

    /**
     * Récupère les commandes d'un utilisateur spécifique sous forme de JSON.
     *
     * @param user_name Le nom d'utilisateur pour lequel récupérer les commandes
     * @return Une chaîne JSON représentant les commandes de l'utilisateur,
     *         ou null si aucune commande n'est trouvée
     */
    public String getCommandesByUserJSON(String user_name) {
        ArrayList<Commande> allCommandes = commandeRepo.getAllCommandes();
        ArrayList<Commande> userCommandes = new ArrayList<>();

        for (Commande commande : allCommandes) {
            if (user_name.equals(commande.getUser_name())) {
                userCommandes.add(commande);
            }
        }

        if (userCommandes.isEmpty()) {
            return null;
        }

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(userCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}