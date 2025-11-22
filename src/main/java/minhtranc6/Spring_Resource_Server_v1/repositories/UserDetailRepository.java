package minhtranc6.Spring_Resource_Server_v1.repositories;

import minhtranc6.Spring_Resource_Server_v1.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUserName(String username);
}
