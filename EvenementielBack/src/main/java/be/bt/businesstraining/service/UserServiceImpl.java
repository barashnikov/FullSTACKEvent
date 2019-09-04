package be.bt.businesstraining.service;

import be.bt.businesstraining.domain.Role;
import be.bt.businesstraining.domain.User;
import be.bt.businesstraining.repository.RoleRepository;
import be.bt.businesstraining.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service

public class UserServiceImpl implements UsersService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Transactional
    @Override
    public ResponseEntity<?> register(User user) {
        try {
            if (userRepository.findByNickname(user.getNickname()) == null) {
                Role role1 = roleRepository.findFirstByRole("USER");
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                user.setEnabled(true);
                user.setRoles(new HashSet<>(Arrays.asList(role1)));

                Set<User> theUsers =  role1.getUsers();
                theUsers.add(user);
                role1.setUsers(theUsers);

                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(user, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            return new ResponseEntity<String>("Erreur lors de l'enregistrement : " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @Transactional(readOnly = true)
    @Override
    public User findUserByNickName(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
