package kgu.developers.api.comment.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kgu.developers.api.comment.presentation.exception.CommentNotFoundException;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.post.application.PostService;
import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostService postService;
	private final UserService userService;

	public static final int COMMENT_RETENTION_DAYS = 60 * 60 * 24 * 30;

	private LocalDateTime lastScheduledRun;

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

	public CommentListResponse getComments(Long postId) {
		Post post = postService.getById(postId);
		List<Comment> comments = commentRepository.findAllByPostIdAndDeletedAtIsNull(post.getId());
		return CommentListResponse.from(comments);
	}

	@Transactional
	public void updateComment(Long commentId, CommentRequest commentRequest) {
		Comment comment = getById(commentId);
		comment.updateContent(commentRequest.content());
	}

	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = getById(commentId);
		comment.delete();
	}

	private Comment getById(Long commentId) {
		return commentRepository.findById(commentId)
			.filter(comment -> comment.getDeletedAt() == null)
			.orElseThrow(CommentNotFoundException::new);
	}

	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void cleanupOldDeletedComments() {
		commentRepository.deleteAllByDeletedAtBefore(COMMENT_RETENTION_DAYS);
		lastScheduledRun = LocalDateTime.now();
	}

	public String getFormattedLastCleanupRunTime() {
		if (lastScheduledRun == null) {
			return "아직 클린업 작업이 실행되지 않았습니다.";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return "최근 삭제된 댓글 정리 시간: " + lastScheduledRun.format(formatter);
	}
}
