package kgu.developers.admin.about.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.admin.about.application.AboutAdminFacade;
import kgu.developers.admin.about.presentation.request.AboutRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/abouts")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "About", description = "소개글 관리자 API")
public class AboutAdminController {
	private final AboutAdminFacade aboutAdminFacade;

	@Operation(summary = "소개글 생성 API", description = """
			- Description : 이 API는 소개글을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = AboutPersistResponse.class)))
	@PostMapping
	public ResponseEntity<AboutPersistResponse> createAbout(
		@RequestBody AboutRequest request
	) {
		AboutPersistResponse response = aboutAdminFacade.createAbout(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "소개글 수정 API", description = """
		    - Description : 이 API는 소개글을 수정합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateAbout(
		@PathVariable Long id,
		@RequestBody AboutUpdateRequest request
	) {
		aboutAdminFacade.updateAbout(id, request);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
