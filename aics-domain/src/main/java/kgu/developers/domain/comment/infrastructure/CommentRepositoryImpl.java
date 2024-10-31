package kgu.developers.domain.comment.infrastructure;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
	public final JpaCommentRepository jpaCommentRepository;

	@Override
	public Comment save(Comment comment) {
		return jpaCommentRepository.save(comment);
	}

	@Override
	public Optional<Comment> findById(Long commentId) {
		return jpaCommentRepository.findById(commentId);
	}
}
