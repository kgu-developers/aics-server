package kgu.developers.admin.post.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import kgu.developers.admin.post.application.PostAdminFacade;
import kgu.developers.admin.post.presentation.request.PostRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "게시글 관리자 API")
public class PostAdminController {
	private final PostAdminFacade postAdminFacade;

	@Operation(summary = "게시글 생성 API", description = """
			- Description : 이 API는 게시글을 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = PostPersistResponse.class)))
	@PostMapping
	public ResponseEntity<PostPersistResponse> createPost(
		@Valid @RequestBody PostRequest request
	) {
		PostPersistResponse response = postAdminFacade.createPost(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "게시글 수정 API", description = """
		    - Description : 이 API는 게시글을 수정합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{postId}")
	public ResponseEntity<Void> updatePost(
		@Parameter(description = "수정할 게시글의 id", example = "1", required = true) @PathVariable @Positive Long postId,
		@Valid @RequestBody PostRequest request
	) {
		postAdminFacade.updatePost(postId, request);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "게시글 상단 고정 상태 토글 API", description = """
		    - Description : 이 API는 지정된 게시글의 고정 여부를 토글하여 고정 또는 해제합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{postId}/pin")
	public ResponseEntity<Void> togglePostPinStatus(
		@Parameter(description = "고정 상태를 변경할 게시글의 id", example = "1", required = true) @PathVariable @Positive Long postId
	) {
		postAdminFacade.togglePostPinStatus(postId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "게시글 삭제 API", description = """
		    - Description : 이 API는 게시글을 삭제합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{postId}/delete")
	public ResponseEntity<Void> deletePostById(
		@Parameter(description = "조회할 게시글의 id", example = "1", required = true) @PathVariable @Positive Long postId
	) {
		postAdminFacade.deletePost(postId);
		return ResponseEntity.noContent().build();
	}

	@Hidden
	@Operation(summary = "삭제된 게시글 정리 API", description = """
		    - Description : 이 API는 삭제된 지 일정 기간 지난 게시글을 영구적으로 삭제 합니다.
		    - Assignee : 박민준
		""")
	@GetMapping("/cleanup-last-run")
	public ResponseEntity<String> getLastCleanupRunTime() {
		String response = postAdminFacade.getLastCleanupRunTime();
		return ResponseEntity.ok(response);
	}
}
