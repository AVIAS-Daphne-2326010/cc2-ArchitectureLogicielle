package fr.univamu.iut.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {
    @Produces
    private UserRepositoryInterface openDbConnection(){
        UserRepositoryPostgres db = null;

        try{
            db = new UserRepositoryPostgres("jdbc:postgresql://postgresql-pub.alwaysdata.net/pub_cooperative", "pub_admin_coop", "admin_coop");}
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    private void closeDbConnection(@Disposes UserRepositoryInterface UserRepo ) {
        UserRepo.close();
    }
}