package be.bt.businesstraining;

import be.bt.businesstraining.domain.Role;
import be.bt.businesstraining.domain.User;
import be.bt.businesstraining.repository.RoleRepository;
import be.bt.businesstraining.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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

    @Bean
    CommandLineRunner runIt(RoleRepository rolesRepository, UserRepository userRepository) {
        return args -> {
            try {



                Role user = new Role();
                user.setRole("USER");
                Role admin = new Role();
                admin.setRole("ADMIN");
                User user1 = new User();
                User user2 = new User();

                /*--------------------------------------------------*/
                user1.setName("Fakiri");
                user1.setSurname("Mohamed");
                user1.setDateBirth("19-10-1993");
                user1.setNickname("barash");
                user1.setPassword("mdp");
                user1.setMail("fakiri");
                user1.setEnabled(true);



                /*---------------------------------------------------*/
                user2.setName("Test");
                user2.setSurname("Mohamed");
                user2.setDateBirth("19-10-1993");
                user2.setNickname("zadzadzad");
                user2.setPassword("mdp");
                user2.setMail("fakiri");
                user2.setEnabled(true);


                userRepository.saveAll(Arrays.asList(user1,user2));
                rolesRepository.saveAll(Arrays.asList(user, admin));



                System.out.println("Fin de l'initialisation par CommandLineRunner ...");
            } catch (Exception ex) {
                LOGGER.error("Exception rencontrée lors de l'initialisation par CommandLineRunner : "+ex);
            }
        };
    }


}
