package be.bt.businesstraining.repository;

import be.bt.businesstraining.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findFirstByName(String authority);

}
