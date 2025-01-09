package kgu.developers.api.comment.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.comment.application.CommentFacade;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.request.CommentUpdateRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentControllerImpl implements CommentController {
	private final CommentFacade commentFacade;

	@Override
	@PostMapping
	public ResponseEntity<CommentPersistResponse> createComment(
		@Valid @RequestBody CommentRequest commentRequest
	) {
		CommentPersistResponse response = commentFacade.createComment(commentRequest);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@Positive @PathVariable Long commentId,
		@Valid @RequestBody CommentUpdateRequest request
	) {
		commentFacade.updateComment(commentId, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping
	public ResponseEntity<CommentListResponse> getComments(
		@Positive @RequestParam Long postId
	) {
		CommentListResponse response = commentFacade.getComments(postId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PatchMapping("/{commentId}/delete")
	public ResponseEntity<Void> deleteComment(
		@Positive @PathVariable Long commentId
	) {
		commentFacade.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/cleanup-last-run")
	public ResponseEntity<String> getLastCleanupRunTime() {
		String response = commentFacade.getLastCleanupRunTime();
		return ResponseEntity.ok(response);
	}
}
