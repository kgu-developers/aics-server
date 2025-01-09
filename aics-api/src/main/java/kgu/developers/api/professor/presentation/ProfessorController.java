package kgu.developers.api.professor.presentation;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.professor.presentation.response.ProfessorListResponse;

@Tag(name = "Professor", description = "교수 API")
public interface ProfessorController {

	@Operation(summary = "교수 조회 API", description = """
		    - Description : 이 API는 교수를 모두 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = ProfessorListResponse.class)))
	ResponseEntity<ProfessorListResponse> getSortedProfessorList();
}

