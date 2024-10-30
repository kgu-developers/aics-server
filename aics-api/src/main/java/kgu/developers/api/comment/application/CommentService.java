package kgu.developers.api.comment.application;

import jakarta.transaction.Transactional;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentResponse;
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
	public CommentResponse createComment(Long postId, CommentRequest request) {
		Comment comment = Comment.create(
			request.content(),
			userService.me(),
			postService.getById(postId)
		);
		return CommentResponse.from(commentRepository.save(comment));
	}
}
