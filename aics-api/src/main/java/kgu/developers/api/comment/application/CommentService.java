package kgu.developers.api.comment.application;

import jakarta.transaction.Transactional;
import kgu.developers.api.comment.presentation.exception.CommentNotFoundException;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.post.application.PostService;
import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostService postService;
	private final UserService userService;

	@Transactional
	public CommentPersistResponse createComment(CommentRequest request) {
		Comment createComment = Comment.create(
			request.content(),
			userService.me(),
			postService.getById(request.postId())
		);
		Long id = commentRepository.save(createComment).getId();
		return CommentPersistResponse.of(id);
	}

	@Transactional
	public void updateComment(Long commentId, CommentRequest commentRequest) {
		Comment comment = getById(commentId);
		comment.updateContent(commentRequest.content());
	}

	private Comment getById(Long commentId) {
		return commentRepository.findById(commentId)
			.filter(comment -> comment.getDeletedAt() == null)
			.orElseThrow(CommentNotFoundException::new);
	}
}
