package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

public class CommandeService {

    private CommandeRepositoryInterface commandeRepo;

    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public String getAllCommandesJSON(){
        ArrayList<Commande> allCommandes = commandeRepo.getAllCommandes();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getCommandeJSON (int id){
        String result = null;
        Commande mycommande = commandeRepo.getCommande(id);

        if (mycommande!=null){
            try (Jsonb jsonb = JsonbBuilder.create()){
                result = jsonb.toJson(mycommande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean createCommande(Commande commande) {
        return commandeRepo.createCommande(commande);
    }

    public boolean deleteCommande(int id) {
        return commandeRepo.deleteCommande(id);
    }

    public boolean updateCommande(int id, Commande commande){
        return commandeRepo.updateCommande(id, commande.user_name, commande.relai, commande.date);
    }

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

    public boolean addCompoCommande(int id_commande, int id_type_panier, int quantite) {
        return commandeRepo.addCompoCommande(id_commande, id_type_panier, quantite);
    }

    public boolean updateCompoCommande(int id_commande, int id_type_panier, int quantite) {
        return commandeRepo.updateCompoCommande(id_commande, id_type_panier, quantite);
    }

    public boolean removeCompoCommande(int id_commande, int id_type_panier) {
        return commandeRepo.removeCompoCommande(id_commande, id_type_panier);
    }
}
