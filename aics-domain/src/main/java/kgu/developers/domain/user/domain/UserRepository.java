package kgu.developers.domain.user.domain;

import java.util.Optional;

public interface UserRepository {
	User save(User user);

	boolean existsById(String userId);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findById(String userId);
}
