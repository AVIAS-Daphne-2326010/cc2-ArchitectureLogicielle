package fr.univamu.iut.apicommandes;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

public class CommandeService {
    protected CommandeRepositoryInterface commandeRepo;

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

    public String getCommmandeJSON (int id){
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

    public boolean updateCommande(int id, Commande commande){
        return commandeRepo.updateCommande(id, commande.user_name, commande.relai, commande.date);
    }
}
