package securitycrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securitycrudapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Optional<Role> findByName(String name);
}
