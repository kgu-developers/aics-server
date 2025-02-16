package kgu.developers.domain.post.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface PostRepository {
	Post save(Post post);

	PaginatedListResponse<Post> findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(List<String> keywords,
		Category category, Pageable pageable);

	Optional<Post> findByIdAndDeletedAtIsNull(Long postId);

	void deleteAllByDeletedAtBefore(int retentionDays);

	Optional<Post> findByPrevPost(LocalDateTime createdAt, Category category);

	Optional<Post> findByNextPost(LocalDateTime createdAt, Category category);
}
