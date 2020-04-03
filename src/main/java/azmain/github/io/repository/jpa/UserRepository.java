package azmain.github.io.repository.jpa;

import azmain.github.io.repository.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
