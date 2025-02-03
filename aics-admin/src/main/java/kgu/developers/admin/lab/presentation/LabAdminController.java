package kgu.developers.admin.lab.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.lab.presentation.request.LabRequest;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;

@Tag(name = "Lab", description = "연구실 관리자 API")
public interface LabAdminController {

	@Operation(summary = "연구실 생성 API", description = """
			- Description : 이 API는 연구실을 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = LabPersistResponse.class)))
	ResponseEntity<LabPersistResponse> createLab(
		@Parameter(
			description = "이미지 파일 ID는 URL 쿼리 파라미터 입니다.",
			example = "1"
		) @Positive @RequestParam(required = false) Long fileId,
		@Parameter(
			description = "연구실 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody LabRequest request
	);

	@Operation(summary = "연구실 수정 API", description = """
			- Description : 이 API는 연구실 정보를 수정합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateLab(
		@Parameter(
			description = "연구실 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id,
		@Parameter(
			description = "연구실 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody LabRequest request
	);

	@Operation(summary = "연구실 삭제 API", description = """
			- Description : 이 API는 해당 연구실을 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deleteLab(
		@Parameter(
			description = "연구실 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id
	);
}
