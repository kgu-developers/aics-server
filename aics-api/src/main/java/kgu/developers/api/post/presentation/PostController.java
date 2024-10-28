package kgu.developers.api.post.presentation;

import static org.springframework.http.HttpStatus.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.api.post.application.PostService;
import kgu.developers.api.post.presentation.request.PostRequest;
import kgu.developers.api.post.presentation.response.PostPersistResponse;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "게시글 API")
public class PostController {
	private final PostService postService;

	@Operation(summary = "게시글 생성 API", description = """
			- Description : 이 API는 게시글을 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = PostPersistResponse.class)))
	@PostMapping
	public ResponseEntity<PostPersistResponse> createPost(
		@RequestBody PostRequest request
	) {
		PostPersistResponse response = postService.createPost(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "게시글 조회 API", description = """
		    - Description : 이 API는 키워드를 이용하여 게시글을 조회합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostSummaryPageResponse.class)))
	@GetMapping
	public ResponseEntity<PostSummaryPageResponse> getPostsByKeyword(
		@Parameter(description = "페이지 인덱스", example = "0", required = true) @RequestParam(defaultValue = "0") @PositiveOrZero int page,
		@Parameter(description = "응답 개수", example = "10", required = true) @RequestParam(defaultValue = "10") @Positive int size,
		@Parameter(description = "검색 키워드", example = "컴퓨터공학과") @RequestParam(required = false) String keyword
	) {
		PostSummaryPageResponse response = postService.getPostsByKeyword(PageRequest.of(page, size), keyword);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "게시글 수정 API", description = """
		    - Description : 이 API는 게시글을 수정합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{postId}")
	public ResponseEntity<Void> updatePost(
		@Parameter(description = "수정할 게시글의 ID", example = "19", required = true) @PathVariable @Positive Long postId,
		@RequestBody PostRequest request
	) {
		postService.updatePost(postId, request);
		return ResponseEntity.noContent().build();
	}
}
