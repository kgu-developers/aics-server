package kgu.developers.api.about.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.about.application.AboutFacade;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/abouts")
@Tag(name = "About", description = "소개글 API")
public class AboutController {
	private final AboutFacade aboutFacade;

	@Operation(summary = "소개글 조회 API", description = """
		    - Description : 이 API는 소개글을 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AboutResponse.class)))
	@GetMapping
	public ResponseEntity<AboutResponse> getAbout(
		@Parameter(description = "메인 카테고리", example = "EDU_ACTIVITIES") @RequestParam(name = "main") MainCategory main,
		@Parameter(description = "보조 카테고리", example = "CURRICULUM") @RequestParam(name = "sub") SubCategory sub,
		@Parameter(description = "세부 카테고리", example = "2019") @RequestParam(name = "detail", required = false) String detail
	) {
		AboutResponse response = aboutFacade.getAbout(main, sub, detail);
		return ResponseEntity.ok(response);
	}
}
