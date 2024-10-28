package kgu.developers.domain.post.domain;

import kgu.developers.common.response.PaginatedListResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
	Post save(Post post);

	PaginatedListResponse<Post> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);

	Optional<Post> findById(Long postId);
}
