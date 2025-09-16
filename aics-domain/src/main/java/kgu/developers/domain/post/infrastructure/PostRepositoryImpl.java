package kgu.developers.domain.post.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
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
		PostJpaEntity entity = PostJpaEntity.toEntity(post);
		PostJpaEntity savedEntity = jpaPostRepository.save(entity);
		return PostJpaEntity.toDomain(savedEntity);
	}

	@Override
	public Optional<Post> findByIdAndDeletedAtIsNull(Long postId) {
		return jpaPostRepository.findByIdAndDeletedAtIsNull(postId)
			.map(PostJpaEntity::toDomain);
	}

	@Override
	public PaginatedListResponse<Post> findAllByTitleContainingAndCategoryOrderByCreatedAtDescIdDesc(List<String> keywords,
		Category category, Pageable pageable) {
		return queryPostRepository.findAllByTitleContainingAndCategoryOrderByCreatedAtDescIdDesc(keywords, category, pageable);
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {
		queryPostRepository.deleteAllByDeletedAtBefore(retentionDays);
	}

	@Override
	public Optional<Post> findByPrevPost(Long postId, LocalDateTime createdAt, Category category) {
		return queryPostRepository.findPreviousPost(postId, createdAt, category);
	}

	@Override
	public Optional<Post> findByNextPost(Long postId, LocalDateTime createdAt, Category category) {
		return queryPostRepository.findNextPost(postId, createdAt, category);
	}

}
