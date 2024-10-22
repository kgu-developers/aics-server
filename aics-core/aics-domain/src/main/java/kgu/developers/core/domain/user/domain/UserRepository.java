package kgu.developers.core.domain.user.domain;

import java.util.Optional;

public interface UserRepository {
	User save(User user);

	boolean existsByPersonalId(String personalId);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findByPersonalId(String personalId);
}
