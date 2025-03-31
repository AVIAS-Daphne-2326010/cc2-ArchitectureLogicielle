package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

@ApplicationScoped
public class CommandesService {

    private CommandesRepositoryInterface commandesRepo;

    @Inject
    public CommandesService(CommandesRepositoryInterface commandesRepo) {
        this.commandesRepo = commandesRepo;
    }

    public String getAllCommandesJSON() {
        ArrayList<Commandes> allCommandes = commandesRepo.getAllCommandes();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create() ) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getCommandesJSON (int id){
        String result = null;
        Commandes myCommandes = commandesRepo.getCommandes(id);

        if (myCommandes != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCommandes);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
