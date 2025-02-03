package kgu.developers.api.about.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.Category;

@Tag(name = "About", description = "소개글 API")
public interface AboutController {

	@Operation(summary = "소개글 조회 API", description = """
		    - Description : 이 API는 소개글을 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = AboutResponse.class)))
	ResponseEntity<AboutResponse> getAbout(
		@Parameter(
			description = "카테고리 ENUM 타입 입니다.",
			example = "DEPT_INTRO",
			required = true
		) @RequestParam(name = "category") Category category
	);
}
