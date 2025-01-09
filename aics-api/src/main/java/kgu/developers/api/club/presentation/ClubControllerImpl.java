package kgu.developers.api.club.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.api.club.application.ClubFacade;
import kgu.developers.api.club.presentation.response.ClubListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clubs")
public class ClubControllerImpl implements ClubController {
	private final ClubFacade clubFacade;

	@Override
	@GetMapping
	public ResponseEntity<ClubListResponse> getClubs() {
		ClubListResponse response = clubFacade.getClubs();
		return ResponseEntity.ok(response);
	}
}
