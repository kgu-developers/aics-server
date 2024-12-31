package kgu.developers.api.post.presentation;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import kgu.developers.api.post.application.PostFacade;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.domain.post.domain.Category;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "게시글 API")
public class PostController {
	private final PostFacade postFacade;

	@Operation(summary = "게시글 페이징 조회 API", description = """
		    - Description : 이 API는 키워드를 이용하여 게시글을 페이징 조회합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostSummaryPageResponse.class)))
	@GetMapping
	public ResponseEntity<PostSummaryPageResponse> getPostsByKeywordAndCategory(
		@Parameter(description = "게시글 카테고리", example = "DEPT_INFO") @RequestParam(required = false) Category category,
		@Parameter(description = "페이지 인덱스", example = "0", required = true) @RequestParam(defaultValue = "0") @PositiveOrZero int page,
		@Parameter(description = "응답 개수", example = "10", required = true) @RequestParam(defaultValue = "10") @Positive int size,
		@Parameter(description = "검색 키워드", example = "컴퓨터공학과") @RequestParam(required = false) String keyword
	) {
		PostSummaryPageResponse response = postFacade.getPostsByKeywordAndCategory(PageRequest.of(page, size), keyword,
			category);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "게시글 상세 조회 API", description = """
		    - Description : 이 API는 게시글의 상세 정보를 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PostDetailResponse.class)))
	@GetMapping("/{postId}")
	public ResponseEntity<PostDetailResponse> getPostById(
		@Parameter(description = "조회할 게시글의 id", example = "1", required = true) @PathVariable @Positive Long postId
	) {
		PostDetailResponse response = postFacade.getPostByIdWithPrevAndNext(postId);
		return ResponseEntity.ok(response);
	}
}
