package kgu.developers.admin.professor.presentation;

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
import kgu.developers.admin.professor.presentation.request.ProfessorRequest;
import kgu.developers.admin.professor.presentation.response.ProfessorPersistResponse;

@Tag(name = "Professor", description = "교수 관리자 API")
public interface ProfessorAdminController {

	@Operation(summary = "교수 생성 API", description = """
		    - Description : 이 API는 교수를 생성합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = ProfessorPersistResponse.class)))
	ResponseEntity<ProfessorPersistResponse> createProfessor(
		@Parameter(
			description = "교수 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody ProfessorRequest request
	);

	@Operation(summary = "교수 수정 API", description = """
		    - Description : 이 API는 교수를 수정합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateProfessor(
		@Parameter(
			description = "교수 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @PathVariable @Positive Long id,
		@Parameter(
			description = "교수 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody ProfessorRequest request
	);

	@Operation(summary = "교수 삭제 API", description = """
		    - Description : 이 API는 교수를 삭제합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deleteProfessor(
		@Parameter(
			description = "교수 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id
	);
}
