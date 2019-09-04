package be.bt.businesstraining.service;
import be.bt.businesstraining.domain.User;
import org.springframework.http.ResponseEntity;



public interface UsersService {

    ResponseEntity<?> register (User user);
    User findUserByNickName(String nickname);
}
