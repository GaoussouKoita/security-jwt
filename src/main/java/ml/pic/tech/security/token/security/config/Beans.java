package ml.pic.tech.security.token.security.config;

import ml.pic.tech.security.token.security.entity.Role;
import ml.pic.tech.security.token.security.entity.Utilisateur;
import ml.pic.tech.security.token.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class Beans {

    @Bean
    CommandLineRunner commandLineRunner(AccountService service) {
        return args -> {
            Role role1 = new Role(1L, "ADMINISTRATEUR");
            Role role2 = new Role(2L, "UTILISATEUR");
            Role role3 = new Role(3L, "INVITE");
            Collection<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);

            roles.forEach(role -> service.addRole(role));
            service.addUtilisateur(new Utilisateur(1L, "Gaoussou", "KOITA", "76 68 47 88",
                    "Bamako-Mali", "admin", "1234", roles));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
