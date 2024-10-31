package kgu.developers.domain.comment.domain;

import java.util.Optional;

public interface CommentRepository {
	Comment save(Comment comment);

	Optional<Comment> findById(Long commentId);
}
