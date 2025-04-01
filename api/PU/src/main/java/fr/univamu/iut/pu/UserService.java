package fr.univamu.iut.pu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


@ApplicationScoped
public class UserService {

    private UserRepositoryInterface userRepo;

    public UserService() {}

    @Inject
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getUserJSON(String login) {
        User myUser = userRepo.getUser(login);

        if (myUser != null) {

            String result = null;
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return result;
        } else {
            return null;
        }
    }
}