package kgu.developers.admin.post.presentation;

import org.springframework.http.ResponseEntity;
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
import kgu.developers.admin.post.presentation.request.PostCreateRequest;
import kgu.developers.admin.post.presentation.request.PostUpdateRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;

@Tag(name = "Post", description = "게시글 관리자 API")
public interface PostAdminController {

	@Operation(summary = "게시글 생성 API", description = """
			- Description : 이 API는 게시글을 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = PostPersistResponse.class)))
	ResponseEntity<PostPersistResponse> createPost(
		@Parameter(
			description = "게시글에 저장할 파일의 ID 입니다.",
			example = "1"
		) @RequestParam(required = false) Long fileId,
		@Parameter(
			description = "게시글 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody PostCreateRequest request
	);

	@Operation(summary = "게시글 수정 API", description = """
		    - Description : 이 API는 게시글을 수정합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updatePost(
		@Parameter(
			description = "게시글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long postId,
		@Parameter(
			description = "게시글 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody PostUpdateRequest request
	);

	@Operation(summary = "게시글 상단 고정 상태 토글 API", description = """
		    - Description : 이 API는 지정된 게시글의 고정 여부를 토글하여 고정 또는 해제합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> togglePostPinStatus(
		@Parameter(
			description = "고정 상태를 변경할 게시글의 ID 입니다. URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long postId
	);

	@Operation(summary = "게시글 삭제 API", description = """
		    - Description : 이 API는 게시글을 삭제합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deletePostById(
		@Parameter(
			description = "게시글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long postId
	);

	@Hidden
	@Operation(summary = "삭제된 게시글 정리 API", description = """
		    - Description : 이 API는 삭제된 지 일정 기간 지난 게시글을 영구적으로 삭제 합니다.
		    - Assignee : 박민준
		""")
	ResponseEntity<String> getLastCleanupRunTime();
}
