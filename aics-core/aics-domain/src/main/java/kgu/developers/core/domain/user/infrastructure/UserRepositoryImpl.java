package kgu.developers.core.domain.user.infrastructure;

import kgu.developers.core.domain.user.domain.User;
import kgu.developers.core.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public boolean existsByUserId(String userId) {
		return jpaUserRepository.existsByUserId(userId);
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
	public Optional<User> findByUserId(String userId) {
		return jpaUserRepository.findByUserId(userId);
	}
}
