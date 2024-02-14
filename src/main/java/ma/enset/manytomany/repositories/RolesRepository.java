package ma.enset.manytomany.repositories;

import ma.enset.manytomany.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String rolename);
}
