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

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Comment", description = "댓글 API")
public class CommentController {
	private final CommentFacade commentFacade;

	@Operation(summary = "댓글 생성 API", description = """
			- Description : 이 API는 댓글을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CommentPersistResponse.class)))
	@PostMapping
	public ResponseEntity<CommentPersistResponse> createComment(
		@RequestBody @Valid CommentRequest commentRequest
	) {
		CommentPersistResponse response = commentFacade.createComment(commentRequest);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "댓글 수정 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@Parameter(description = "수정할 댓글의 id", example = "1", required = true) @PathVariable @Positive Long commentId,
		@RequestBody @Valid CommentUpdateRequest request
	) {
		commentFacade.updateComment(commentId, request);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "댓글 조회 API", description = """
			- Description : 이 API는 해당 게시글의 댓글을 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommentListResponse.class)))
	@GetMapping
	public ResponseEntity<CommentListResponse> getComments(
		@Parameter(description = "게시글의 id", example = "1", required = true) @RequestParam @Positive Long postId
	) {
		CommentListResponse response = commentFacade.getComments(postId);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "댓글 삭제 API", description = """
			- Description : 이 API는 해당 댓글을 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{commentId}/delete")
	public ResponseEntity<Void> deleteComment(
		@Parameter(description = "삭제할 댓글의 id", example = "1", required = true) @PathVariable @Positive Long commentId
	) {
		commentFacade.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}

	@Hidden
	@GetMapping("/cleanup-last-run")
	public ResponseEntity<String> getLastCleanupRunTime() {
		String response = commentFacade.getLastCleanupRunTime();
		return ResponseEntity.ok(response);
	}
}
