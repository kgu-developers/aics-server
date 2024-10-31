package kgu.developers.domain.comment.domain;

import java.util.List;

public interface CommentRepository {
	Comment save(Comment comment);

	List<Comment> findByPostId(Long postId);
}
