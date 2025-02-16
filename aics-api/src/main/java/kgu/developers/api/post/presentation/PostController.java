package kgu.developers.api.post.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.domain.Category;

@Tag(name = "Post", description = "게시글 API")
public interface PostController {

	@Operation(summary = "게시글 페이징 조회 API", description = """
		    - Description : 이 API는 키워드를 이용하여 게시글을 페이징 조회합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = PostSummaryPageResponse.class)))
	ResponseEntity<PostSummaryPageResponse> getPostsByKeywordAndCategory(
		@Parameter(
			description = "페이지 인덱스",
			example = "0",
			required = true
		) @PositiveOrZero @RequestParam(defaultValue = "0") int page,
		@Parameter(
			description = "응답 개수",
			example = "10",
			required = true
		) @Positive @RequestParam(defaultValue = "10") int size,
		@Parameter(
			description = "검색 키워드 입니다. 미 입력 시 전체 게시글을 조회합니다.",
			example = "컴퓨터공학과"
		) @RequestParam(required = false) List<String> keywords,
		@Parameter(
			description = "게시글 카테고리 입니다. 미 지정 시 전체 게시글을 조회합니다.",
			example = "NOTIFICATION"
		) @RequestParam(required = false) Category category
	);

	@Operation(summary = "게시글 상세 조회 API", description = """
		    - Description : 이 API는 게시글의 상세 정보를 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = PostDetailResponse.class)))
	ResponseEntity<PostDetailResponse> getPostById(
		@Parameter(
			description = "게시글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long postId
	);
}
