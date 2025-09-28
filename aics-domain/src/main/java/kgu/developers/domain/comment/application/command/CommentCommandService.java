package kgu.developers.domain.comment.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentCommandService {
	private final PostQueryService postQueryService;
	private final UserQueryService userQueryService;
	private final CommentRepository commentRepository;

	public Long createComment(String content, Long postId) {
		Post post = postQueryService.getById(postId);
		User user = userQueryService.me();
		Comment comment = Comment.create(content, user.getId(), post.getId());
		return commentRepository.save(comment).getId();
	}

	public void updateComment(Comment comment, String content) {
		comment.updateContent(content);
		commentRepository.save(comment);
	}

	public void deleteComment(Comment comment) {
		comment.delete();
		commentRepository.save(comment);
	}
}