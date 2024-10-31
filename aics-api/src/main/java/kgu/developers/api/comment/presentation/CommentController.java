package kgu.developers.api.comment.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.comment.application.CommentService;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.comment.presentation.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

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
	@PostMapping()
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


}
