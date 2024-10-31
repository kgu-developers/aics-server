package kgu.developers.domain.comment.domain;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
	Comment save(Comment comment);

	List<Comment> findByPostId(Long postId);

	Optional<Comment> findById(Long commentId);
}
