package kgu.developers.api.lab.presentation;

import static org.springframework.http.HttpStatus.CREATED;

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
import kgu.developers.api.lab.application.LabService;
import kgu.developers.api.lab.presentation.request.LabRequest;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.api.lab.presentation.response.LabPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/labs")
@Tag(name = "Lab", description = "연구실 API")
public class LabController {

	private final LabService labService;

	@Operation(summary = "연구실 생성 API", description = """
			- Description : 이 API는 연구실을 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = LabPersistResponse.class)))
	@PostMapping
	public ResponseEntity<LabPersistResponse> createComment(
		@Valid @RequestBody LabRequest request
	) {
		LabPersistResponse response = labService.createLab(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "연구실 조회 API", description = """
			- Description : 이 API는 연구실을 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LabListResponse.class)))
	@GetMapping
	public ResponseEntity<LabListResponse> getLabs() {
		LabListResponse response = labService.getLabs();
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "연구실 수정 API", description = """
			- Description : 이 API는 연구실 정보를 수정합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateComment(
		@Parameter(description = "수정할 연구실 ID", example = "19", required = true) @PathVariable @Positive Long id,
		@Valid @RequestBody LabRequest request
	) {
		labService.updateLab(id, request);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "연구실 삭제 API", description = """
			- Description : 이 API는 해당 연구실을 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(
		@Parameter(description = "삭제할 연구실의 ID", example = "19", required = true) @PathVariable @Positive Long id
	) {
		labService.deleteLab(id);
		return ResponseEntity.noContent().build();
	}
}
