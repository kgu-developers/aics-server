package kgu.developers.domain.post.infrastructure;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Category;
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
	public PaginatedListResponse findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(String keyword,
		Category category, Pageable pageable) {
		return queryPostRepository.findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(keyword, category, pageable);
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {
		queryPostRepository.deleteAllByDeletedAtBefore(retentionDays);
	}

	@Override
	public Optional<Post> findByPrevPost(LocalDateTime createdAt, Category category) {
		return jpaPostRepository.findFirstByCreatedAtLessThanAndDeletedAtIsNullAndCategoryOrderByCreatedAtDesc(
			createdAt, category);
	}

	@Override
	public Optional<Post> findByNextPost(LocalDateTime createdAt, Category category) {
		return jpaPostRepository.findFirstByCreatedAtGreaterThanAndDeletedAtIsNullAndCategoryOrderByCreatedAtAsc(
			createdAt, category);
	}

}
