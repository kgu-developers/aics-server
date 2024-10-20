package kgu.developers.core.domain.user.infrastructure;

import kgu.developers.core.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {

	boolean existsByPersonalId(String PersonalId);
}
