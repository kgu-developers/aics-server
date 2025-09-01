package kgu.developers.api.comment.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kgu.developers.domain.user.application.query.UserQueryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.request.CommentUpdateRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.domain.comment.application.command.CommentCommandService;
import kgu.developers.domain.comment.application.command.CommentSchedulingService;
import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.Comment;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class CommentFacade {
	private final CommentCommandService commentCommandService;
	private final CommentQueryService commentQueryService;
	private final CommentSchedulingService commentSchedulingService;
	private final UserQueryService userQueryService;

	public CommentPersistResponse createComment(CommentRequest request) {
		Long id = commentCommandService.createComment(request.content(), request.postId());
		return CommentPersistResponse.of(id);
	}

	public CommentListResponse getComments(Long postId) {
		List<Comment> comments = commentQueryService.getComments(postId);

		List<String> authorIds = comments.stream().map(Comment::getAuthorId).collect(Collectors.toList());

		Map<String, String> nameMap = userQueryService.getUserNameMapByIds(authorIds);
		return CommentListResponse.from(comments,nameMap);
	}

	public void updateComment(Long commentId, CommentUpdateRequest request) {
		Comment comment = commentQueryService.getById(commentId);
		commentCommandService.updateComment(comment, request.content());
	}

	// TODO: 본인 댓글만 삭제 가능하도록 수정
	public void deleteComment(Long commentId) {
		Comment comment = commentQueryService.getById(commentId);
		commentCommandService.deleteComment(comment);
	}

	public String getLastCleanupRunTime() {
		return commentSchedulingService.getFormattedLastCleanupRunTime();
	}
}
