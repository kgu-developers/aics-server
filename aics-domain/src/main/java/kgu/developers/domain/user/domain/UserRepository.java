package kgu.developers.domain.user.domain;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface UserRepository {
	User save(User user);

	boolean existsById(String userId);

	Optional<User> findById(String userId);

	PaginatedListResponse findAllOrderByIdDesc(Pageable pageable);
}
