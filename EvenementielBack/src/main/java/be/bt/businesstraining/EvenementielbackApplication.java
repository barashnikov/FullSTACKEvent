package be.bt.businesstraining;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class EvenementielbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvenementielbackApplication.class, args);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(EvenementielbackApplication.class);
    @Bean
    BCryptPasswordEncoder encoder() {
        return 	new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner runIt(RoleRepository rolesRepository, UserRepository userRepository) {
//        return args -> {
//            try {
//
//
//
//                Role user = new Role();
//                user.setRole("USER");
//                Role admin = new Role();
//                admin.setRole("ADMIN");
//                rolesRepository.saveAll(Arrays.asList(user, admin));
//
//
//
//                System.out.println("Fin de l'initialisation par CommandLineRunner ...");
//            } catch (Exception ex) {
//                LOGGER.error("Exception rencontrée lors de l'initialisation par CommandLineRunner : "+ex);
//            }
//        };
//    }


}
