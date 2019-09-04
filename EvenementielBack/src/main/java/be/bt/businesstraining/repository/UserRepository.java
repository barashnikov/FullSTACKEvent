package be.bt.businesstraining.repository;

import be.bt.businesstraining.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String username);
    User findByNickname(String nickname);


}
