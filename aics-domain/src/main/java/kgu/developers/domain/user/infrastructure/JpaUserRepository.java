package kgu.developers.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, String> {

	boolean existsById(String id);

	Optional<User> findById(String id);
}
