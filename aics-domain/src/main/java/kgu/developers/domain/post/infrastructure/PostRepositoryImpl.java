package kgu.developers.domain.post.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
	private final JpaPostRepository jpaPostRepository;
	private final QueryPostRepository queryPostRepository;

	@Override
	public Post save(Post post) {
		return jpaPostRepository.save(post);
	}

	@Override
	public Optional<Post> findById(Long postId) {
		return jpaPostRepository.findById(postId);
	}

	@Override
	public PaginatedListResponse findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable) {
		return queryPostRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword, pageable);
	}
}
