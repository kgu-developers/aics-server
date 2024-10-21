package kgu.developers.core.domain.user.infrastructure;

import java.util.Optional;
import kgu.developers.core.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {

	boolean existsByPersonalId(String PersonalId);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findByPersonalId(String PersonalId);
}
