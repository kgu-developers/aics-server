package kgu.developers.api.professor.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.professor.application.ProfessorService;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professor")
@Tag(name = "Professor", description = "교수 API")
public class ProfessorController {
	private final ProfessorService professorService;

	@Operation(summary = "교수 생성 API", description = """
		    - Description : 이 API는 교수를 생성합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ProfessorPersistResponse.class)))
	@PostMapping
	public ResponseEntity<ProfessorPersistResponse> createProfessor(
		@RequestBody ProfessorRequest request
	) {
		ProfessorPersistResponse response = professorService.createProfessor(request);
		return ResponseEntity.status(CREATED).body(response);
	}

}
