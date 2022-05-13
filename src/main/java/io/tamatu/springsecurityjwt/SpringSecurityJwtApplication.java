package io.tamatu.springsecurityjwt;

import io.tamatu.springsecurityjwt.model.AppRole;
import io.tamatu.springsecurityjwt.model.AppUser;
import io.tamatu.springsecurityjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService service){
        return args -> {
            service.saveAppRole(new AppRole(null, "ROLE_USER"));
            service.saveAppRole(new AppRole(null, "ROLE_MANAGER"));
            service.saveAppRole(new AppRole(null, "ROLE_ADMIN"));
            service.saveAppRole(new AppRole(null, "ROLE_SUPER_ADMIN"));

            service.saveAppUser(new AppUser(null, "user1", "user1", "123456", new ArrayList<>()));
            service.saveAppUser(new AppUser(null, "user2", "user2", "123456", new ArrayList<>()));
            service.saveAppUser(new AppUser(null, "user3", "user3", "123456", new ArrayList<>()));
            service.saveAppUser(new AppUser(null, "user4", "user4", "123456", new ArrayList<>()));

            service.addRoleToUSer("user1", "ROLE_USER");
            service.addRoleToUSer("user1", "ROLE_MANAGER");
            service.addRoleToUSer("user2", "ROLE_SUPER_ADMIN");
            service.addRoleToUSer("user2", "ROLE_MANAGER");
            service.addRoleToUSer("user4", "ROLE_ADMIN");


        };
    }

}
