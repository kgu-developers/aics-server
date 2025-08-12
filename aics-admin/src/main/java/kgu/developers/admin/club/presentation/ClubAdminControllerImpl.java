package kgu.developers.admin.club.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.club.application.ClubAdminFacade;
import kgu.developers.admin.club.presentation.request.ClubCreateRequest;
import kgu.developers.admin.club.presentation.request.ClubUpdateRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/clubs")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClubAdminControllerImpl implements ClubAdminController {
	private final ClubAdminFacade clubAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<ClubPersistResponse> createClub(
		@RequestParam(required = false) Long fileId,
		@Valid @RequestBody ClubCreateRequest request
	) {
		ClubPersistResponse response = clubAdminFacade.createClub(fileId, request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateClub(
		@Positive @PathVariable Long id,
		@Valid @RequestBody ClubUpdateRequest request
	) {
		clubAdminFacade.updateClub(id, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClub(
		@Positive @PathVariable Long id
	) {
		clubAdminFacade.deleteClub(id);
		return ResponseEntity.noContent().build();
	}
}
