package kgu.developers.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, String> {

	boolean existsByid(String id);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findByid(String id);
}
