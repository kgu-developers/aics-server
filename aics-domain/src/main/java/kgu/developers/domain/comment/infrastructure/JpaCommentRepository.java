package kgu.developers.domain.comment.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<CommentJpaEntity, Long> {
	List<CommentJpaEntity> findAllByPostIdAndDeletedAtIsNull(Long postId);

	Optional<CommentJpaEntity> findByIdAndDeletedAtIsNull(Long commentId);
}
