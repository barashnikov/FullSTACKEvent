package be.bt.businesstraining.service;

import be.bt.businesstraining.domain.Authority;

import be.bt.businesstraining.domain.User;
import be.bt.businesstraining.repository.AuthorityRepository;
import be.bt.businesstraining.repository.EventRepository;

import be.bt.businesstraining.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service

public class UserServiceImpl implements UsersService {

    UserRepository userRepository;
    EventRepository eventRepository;
    AuthorityRepository authorityRepository;
    public UserServiceImpl(UserRepository userRepository,EventRepository eventRepository,AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.authorityRepository = authorityRepository;
    }
    @Override
    public ResponseEntity<?> register(User user) {
        List<Authority> authorities = new ArrayList<>();
        try {
            if (userRepository.findByNickname(user.getNickname()) == null) {
                authorities.add(authorityRepository.findById(1L).orElse(null));
               // authorities.add(authorityRepository.findById(1L).orElse(null));
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                user.setEnabled(true);
                user.setAuthorities(authorities);
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(user, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            return new ResponseEntity<String>("Erreur lors de l'enregistrement : " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @Override
    public User findUserByNickName(String nickname) {
        return userRepository.findByNickname(nickname);
    }


}
