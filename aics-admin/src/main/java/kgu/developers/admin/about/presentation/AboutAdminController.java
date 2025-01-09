package kgu.developers.admin.about.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.about.presentation.request.AboutCreateRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;

@Tag(name = "About", description = "소개글 관리자 API")
public interface AboutAdminController {

	@Operation(summary = "소개글 생성 API", description = """
			- Description : 이 API는 소개글을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = AboutPersistResponse.class)))
	ResponseEntity<AboutPersistResponse> createAbout(
		@Parameter(
			description = "소개글 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody AboutCreateRequest request
	);

	@Operation(summary = "소개글 수정 API", description = """
		    - Description : 이 API는 소개글을 수정합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateAbout(
		@Parameter(
			description = "소개글 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id,
		@Parameter(
			description = "소개글 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody AboutUpdateRequest request
	);
}
