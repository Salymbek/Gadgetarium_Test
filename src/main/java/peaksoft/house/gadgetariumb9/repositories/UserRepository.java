package peaksoft.house.gadgetariumb9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.house.gadgetariumb9.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String username);

    boolean existsByEmail(String username);
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

}
