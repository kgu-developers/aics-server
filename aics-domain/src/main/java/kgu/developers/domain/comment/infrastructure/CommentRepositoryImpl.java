package kgu.developers.domain.comment.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
	public final JpaCommentRepository jpaCommentRepository;
	private final QueryCommentRepository queryCommentRepository;

	@Override
	public Comment save(Comment comment) {
		CommentJpaEntity entity = CommentJpaEntity.toEntity(comment);
		CommentJpaEntity savedEntity = jpaCommentRepository.save(entity);
		return CommentJpaEntity.toDomain(savedEntity);
	}

	@Override
	public Optional<Comment> findByIdAndDeletedAtIsNull(Long commentId) {

		return jpaCommentRepository.findByIdAndDeletedAtIsNull(commentId)
			.map(CommentJpaEntity::toDomain);
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {
		queryCommentRepository.deleteAllByDeletedAtBefore(retentionDays);
	}

	@Override
	public List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId) {
		return jpaCommentRepository.findAllByPostIdAndDeletedAtIsNull(postId).stream()
			.map(CommentJpaEntity::toDomain)
			.toList();
	}

}
