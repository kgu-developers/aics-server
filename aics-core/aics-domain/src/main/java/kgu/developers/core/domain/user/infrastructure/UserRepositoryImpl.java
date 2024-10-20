package kgu.developers.core.domain.user.infrastructure;

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
}
