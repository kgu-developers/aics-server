package kgu.developers.admin.professor.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import kgu.developers.admin.professor.application.ProfessorAdminFacade;
import kgu.developers.admin.professor.presentation.request.ProfessorRequest;
import kgu.developers.admin.professor.presentation.response.ProfessorPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professors")
@Tag(name = "Professor", description = "교수 관리자 API")
public class ProfessorAdminController {
	private final ProfessorAdminFacade professorAdminFacade;

	@Operation(summary = "교수 생성 API", description = """
		    - Description : 이 API는 교수를 생성합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ProfessorPersistResponse.class)))
	@PostMapping
	public ResponseEntity<ProfessorPersistResponse> createProfessor(
		@Valid @RequestBody ProfessorRequest request
	) {
		ProfessorPersistResponse response = professorAdminFacade.createProfessor(request);
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
		professorAdminFacade.updateProfessor(id, request);
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
		professorAdminFacade.deleteProfessor(id);
		return ResponseEntity.noContent().build();
	}
}
