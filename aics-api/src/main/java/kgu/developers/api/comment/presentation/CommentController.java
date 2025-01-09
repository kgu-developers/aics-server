package kgu.developers.api.comment.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.request.CommentUpdateRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;

@Tag(name = "Comment", description = "댓글 API")
public interface CommentController {

	@Operation(summary = "댓글 생성 API", description = """
			- Description : 이 API는 댓글을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = CommentPersistResponse.class)))
	ResponseEntity<CommentPersistResponse> createComment(
		@Parameter(
			description = "댓글 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody CommentRequest commentRequest
	);

	@Operation(summary = "댓글 수정 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateComment(
		@Parameter(
			description = "댓글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long commentId,
		@Parameter(
			description = "댓글 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody CommentUpdateRequest request
	);

	@Operation(summary = "댓글 조회 API", description = """
			- Description : 이 API는 해당 게시글의 댓글을 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommentListResponse.class)))
	ResponseEntity<CommentListResponse> getComments(
		@Parameter(
			description = "해당 게시글 ID의 댓글을 조회합니다. 쿼리 파라미터 입니다.",
			example = "1",
			required = true
		) @Positive @RequestParam Long postId
	);

	@Operation(summary = "댓글 삭제 API", description = """
			- Description : 이 API는 해당 댓글을 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{commentId}/delete")
	ResponseEntity<Void> deleteComment(
		@Parameter(
			description = "댓글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long commentId
	);

	@Hidden
	@GetMapping("/cleanup-last-run")
	ResponseEntity<String> getLastCleanupRunTime();
}
