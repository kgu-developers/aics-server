package kgu.developers.api.club.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.club.application.ClubFacade;
import kgu.developers.api.club.presentation.response.ClubListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clubs")
@Tag(name = "Club", description = "동아리 API")
public class ClubController {
	private final ClubFacade clubFacade;

	@Operation(summary = "동아리 조회 API", description = """
			- Description : 이 API는 동아리를 조회합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ClubListResponse.class)))
	@GetMapping
	public ResponseEntity<ClubListResponse> getClubs() {
		ClubListResponse response = clubFacade.getClubs();
		return ResponseEntity.ok(response);
	}
}