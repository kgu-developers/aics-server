package kgu.developers.admin.club.presentation;

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
import kgu.developers.admin.club.application.ClubAdminFacade;
import kgu.developers.admin.club.presentation.request.ClubRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clubs")
@Tag(name = "Club", description = "동아리 관리자 API")
public class ClubAdminController {
	private final ClubAdminFacade clubAdminFacade;

	@Operation(summary = "동아리 생성 API", description = """
			- Description : 이 API는 동아리를 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ClubPersistResponse.class)))
	@PostMapping
	public ResponseEntity<ClubPersistResponse> createClub(
		@Valid @RequestBody ClubRequest request
	) {
		ClubPersistResponse response = clubAdminFacade.createClub(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "동아리 수정 API", description = """
			- Description : 이 API는 동아리 정보를 수정합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateClub(
		@Parameter(description = "수정할 동아리 id", example = "1", required = true) @PathVariable @Positive Long id,
		@Valid @RequestBody ClubRequest request
	) {
		clubAdminFacade.updateClub(id, request);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "동아리 삭제 API", description = """
			- Description : 이 API는 해당 동아리를 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClub(
		@Parameter(description = "삭제할 동아리의 id", example = "1", required = true) @PathVariable @Positive Long id
	) {
		clubAdminFacade.deleteClub(id);
		return ResponseEntity.noContent().build();
	}
}
