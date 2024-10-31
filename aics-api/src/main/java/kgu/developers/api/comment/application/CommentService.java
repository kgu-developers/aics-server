package kgu.developers.api.comment.application;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kgu.developers.api.comment.presentation.request.CommentListRequest;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.post.application.PostService;
import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;

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

	public CommentListResponse readComments(CommentListRequest request) {
		List<Comment> comments = commentRepository.findByPostId(request.postId());

		return CommentListResponse.from(comments);
	}
}
