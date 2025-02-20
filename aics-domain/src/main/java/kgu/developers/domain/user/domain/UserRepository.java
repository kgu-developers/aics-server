package kgu.developers.domain.user.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface UserRepository {
	User save(User user);

	boolean existsById(String userId);

	Optional<User> findById(String userId);

	PaginatedListResponse findAllByNameOrderByIdDesc(Pageable pageable, String name);

	void deleteAllByDeletedAtBefore(int retentionDays);

	List<User> findAllById(List<String> ids);
}
