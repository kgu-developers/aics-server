package kgu.developers.domain.user.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;
	private final QueryUserRepository queryUserRepository;

	@Override
	public User save(User user) {
		UserJpaEntity entity = UserJpaEntity.toEntity(user);
		UserJpaEntity savedEntity = jpaUserRepository.save(entity);
		return UserJpaEntity.toDomain(savedEntity);
	}

	@Override
	public boolean existsById(String id) {
		return jpaUserRepository.existsById(id);
	}

	@Override
	public Optional<User> findById(String id) {
		return jpaUserRepository.findById(id)
			.map(UserJpaEntity::toDomain);
	}

	@Override
	public PaginatedListResponse findAllByNameOrderByIdDesc(Pageable pageable, String name) {
		return queryUserRepository.findAllByNameOrderByIdDesc(pageable, name);
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {
		queryUserRepository.deleteAllByDeletedAtBefore(retentionDays);
	}

	@Override
	public List<User> findAllById(List<String> ids) {
		return jpaUserRepository.findAllById(ids).stream()
			.map(UserJpaEntity::toDomain).toList();
	}
}
