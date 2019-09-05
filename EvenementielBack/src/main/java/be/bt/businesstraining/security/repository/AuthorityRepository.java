package be.bt.businesstraining.security.repository;


import be.bt.businesstraining.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  
}
