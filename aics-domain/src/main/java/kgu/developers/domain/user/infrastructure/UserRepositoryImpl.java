package kgu.developers.domain.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public boolean existsById(String id) {
		return jpaUserRepository.existsById(id);
	}

	@Override
	public Optional<User> findById(String id) {
		return jpaUserRepository.findById(id);
	}
}
