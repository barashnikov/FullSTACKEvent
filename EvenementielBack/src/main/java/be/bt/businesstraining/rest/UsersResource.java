package be.bt.businesstraining.rest;

import be.bt.businesstraining.domain.User;

import be.bt.businesstraining.repository.UserRepository;
import be.bt.businesstraining.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@CrossOrigin(origins = "*")
public class UsersResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersResource.class);

    private UsersService usersService;
    private UserRepository usersRepository;



    // =================================
    // ========= Constructors ==========
    // =================================

    public UsersResource(UserRepository usersRepository, UsersService usersService) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    // =================================
    // ======= Getters & Setters =======
    // =================================


    // =================================
    // =========== Methods =============
    // =================================


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return usersService.register(user);
    }



    @GetMapping("/checklogin")
    public ResponseEntity<User> login(Principal user) {

        User player= null;

        if (user != null) {

            player = usersService.findUserByNickName(user.getName());


            return new ResponseEntity<User>(player, HttpStatus.OK);
        }


        else
            return new ResponseEntity<User>(player, HttpStatus.UNAUTHORIZED);
    }






}
