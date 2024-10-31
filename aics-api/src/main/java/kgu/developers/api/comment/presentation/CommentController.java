package kgu.developers.api.comment.presentation;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.comment.application.CommentService;
import kgu.developers.api.comment.presentation.request.CommentListRequest;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.comment.presentation.response.CommentResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Tag(name = "Comment", description = "댓글 API")
public class CommentController {
	private final CommentService commentService;

	@Operation(summary = "댓글 생성 API", description = """
			- Description : 이 API는 댓글을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CommentResponse.class)))
	@PostMapping
	public ResponseEntity<CommentPersistResponse> createComment(
		@RequestBody CommentRequest commentRequest
	) {
		CommentPersistResponse response = commentService.createComment(commentRequest);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "댓글 수정 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@Parameter(description = "수정할 게시글의 ID", example = "19", required = true) @PathVariable @Positive Long commentId,
		@RequestBody CommentRequest commentRequest
	) {
		commentService.updateComment(commentId, commentRequest);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "댓글 조회 API", description = """
			- Description : 이 API는 해당 게시글의 댓글을 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommentListResponse.class)))
	@GetMapping
	public ResponseEntity<CommentListResponse> getComments(
		@RequestBody CommentListRequest commentListRequest
	) {
		CommentListResponse response = commentService.getComments(commentListRequest);
		return ResponseEntity.ok(response);
	}

}
