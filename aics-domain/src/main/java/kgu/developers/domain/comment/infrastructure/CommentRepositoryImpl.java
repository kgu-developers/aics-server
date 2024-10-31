package kgu.developers.domain.comment.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
	public final JpaCommentRepository jpaCommentRepository;

	@Override
	public Comment save(Comment comment) {
		return jpaCommentRepository.save(comment);
	}

	@Override
	public List<Comment> findByPostId(Long postId) {
		return jpaCommentRepository.findByPostId(postId);
	}
}
