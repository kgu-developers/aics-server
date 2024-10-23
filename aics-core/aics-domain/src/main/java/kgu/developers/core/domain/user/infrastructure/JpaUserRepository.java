package kgu.developers.core.domain.user.infrastructure;

import kgu.developers.core.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, String> {

	boolean existsByUserId(String userId);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findByUserId(String userId);
}
