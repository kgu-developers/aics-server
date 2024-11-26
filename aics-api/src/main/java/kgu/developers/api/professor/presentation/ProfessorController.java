package kgu.developers.api.professor.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.professor.application.ProfessorService;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorListResponse;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professors")
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
		@Valid @RequestBody ProfessorRequest request
	) {
		ProfessorPersistResponse response = professorService.createProfessor(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "교수 수정 API", description = """
		    - Description : 이 API는 교수를 수정합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateProfessor(
		@Parameter(description = "수정할 교수 id", example = "1", required = true) @PathVariable @Positive Long id,
		@RequestBody ProfessorRequest request
	) {
		professorService.updateProfessor(id, request);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "교수 삭제 API", description = """
		    - Description : 이 API는 교수를 삭제합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProfessor(
		@Parameter(description = "삭제할 교수 id", example = "1", required = true) @PathVariable @Positive Long id
	) {
		professorService.deleteProfessor(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "교수 조회 API", description = """
		    - Description : 이 API는 교수를 모두 조회합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorListResponse.class)))
	@GetMapping
	public ResponseEntity<ProfessorListResponse> getSortedProfessorList() {
		List<Professor> list = professorService.getSortedProfessorList();
		ProfessorListResponse response = ProfessorListResponse.from(list);
		return ResponseEntity.ok().body(response);
	}

}

