package kgu.developers.domain.post.domain;

import org.springframework.data.domain.Pageable;

import kgu.developers.common.response.PaginatedListResponse;

public interface PostRepository {
	Post save(Post post);

	PaginatedListResponse<Post> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);
}
