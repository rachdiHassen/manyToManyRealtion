package ma.enset.manytomany;

import ma.enset.manytomany.entities.Roles;
import ma.enset.manytomany.entities.Users;
import ma.enset.manytomany.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.util.stream.Stream;

@SpringBootApplication
public class ManyTomanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyTomanyApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return (args)->{
            Users user=new Users();
            user.setUserName("admin");
            user.setPassword("12345");
            userService.addNewUser(user);

            Users user2=new Users();
            user2.setUserName("user2");
            user2.setPassword("98745");
            userService.addNewUser(user2);

            Stream.of("ETUDIANT","ADMIN","USER")
                    .forEach(r->{
                        Roles role=new Roles();
                        role.setRoleName(r);
                        userService.addNewRole(role);
                    });
            // pour tester les méthodes
            /*
            Users us=userService.findUserByUsername("user2");
            System.out.println(us);
            Roles rol=userService.findRoleByRolename("ETUDIANT");
            System.out.println(rol);
            */
            userService.addRoleToUser("admin","ADMIN");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("user2","USER");
            userService.addRoleToUser("user2","ETUDIANT");

            try{
                Users userAuth= userService.authentification("user2","98745");
                System.out.println(userAuth.getUserId());
                System.out.println("les roles");
                userAuth.getRoles().forEach(r -> {
                    System.out.println("role" +r);
                });
            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }
}
