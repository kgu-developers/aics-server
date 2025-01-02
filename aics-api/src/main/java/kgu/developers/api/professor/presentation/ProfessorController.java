package kgu.developers.api.professor.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.professor.application.ProfessorFacade;
import kgu.developers.api.professor.presentation.response.ProfessorListResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professors")
@Tag(name = "Professor", description = "교수 API")
public class ProfessorController {
	private final ProfessorFacade professorFacade;

	@Operation(summary = "교수 조회 API", description = """
		    - Description : 이 API는 교수를 모두 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorListResponse.class)))
	@GetMapping
	public ResponseEntity<ProfessorListResponse> getSortedProfessorList() {
		ProfessorListResponse response = professorFacade.getSortedProfessorList();
		return ResponseEntity.ok().body(response);
	}
}

