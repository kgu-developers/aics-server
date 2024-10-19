package kgu.developers.core.domain.user.infrastructure;

import org.springframework.stereotype.Repository;

import kgu.developers.core.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private JpaUserRepository jpaUserRepository;
}
