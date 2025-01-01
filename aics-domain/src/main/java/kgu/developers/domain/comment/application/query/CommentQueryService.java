package kgu.developers.domain.comment.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import kgu.developers.domain.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentQueryService {
	private final CommentRepository commentRepository;

	public List<Comment> getComments(Long postId) {
		return commentRepository.findAllByPostIdAndDeletedAtIsNull(postId);
	}

	public Comment getById(Long commentId) {
		return commentRepository.findById(commentId)
			.filter(comment -> comment.getDeletedAt() == null)
			.orElseThrow(CommentNotFoundException::new);
	}
}
