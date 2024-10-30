package kgu.developers.domain.post.domain;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface PostRepository {
	Post save(Post post);

	PaginatedListResponse<Post> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);

	Optional<Post> findById(Long postId);

	void deleteAllByDeletedAtBefore(int retentionDays);
}
