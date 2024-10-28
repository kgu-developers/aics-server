package kgu.developers.domain.post.domain;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface PostRepository {
	Post save(Post post);

	Optional<Post> findById(Long id);

	PaginatedListResponse<Post> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);
}
