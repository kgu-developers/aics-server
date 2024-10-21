package kgu.developers.core.domain.user.infrastructure;

import java.util.Optional;
import kgu.developers.core.domain.user.domain.User;
import kgu.developers.core.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public boolean existsByPersonalId(String personalId) {
		return jpaUserRepository.existsByPersonalId(personalId);
	}

	@Override
	public boolean existsByEmail(String email) {
		return jpaUserRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return jpaUserRepository.existsByPhoneNumber(phoneNumber);
	}

	@Override
	public Optional<User> findByPersonalId(String personalId) {
		return jpaUserRepository.findByPersonalId(personalId);
	}
}
