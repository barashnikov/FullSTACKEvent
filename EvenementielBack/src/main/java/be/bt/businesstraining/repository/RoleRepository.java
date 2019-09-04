package be.bt.businesstraining.repository;

import be.bt.businesstraining.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findFirstByRole(String role);
}
