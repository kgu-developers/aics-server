package kgu.developers.api.lab.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.lab.application.LabFacade;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/labs")
@Tag(name = "Lab", description = "연구실 API")
public class LabController {

	private final LabFacade labFacade;

	@Operation(summary = "연구실 조회 API", description = """
			- Description : 이 API는 연구실을 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LabListResponse.class)))
	@GetMapping
	public ResponseEntity<LabListResponse> getLabs() {
		LabListResponse response = labFacade.getLabs();
		return ResponseEntity.ok(response);
	}
}
