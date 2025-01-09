package kgu.developers.admin.about.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.about.application.AboutAdminFacade;
import kgu.developers.admin.about.presentation.request.AboutCreateRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/abouts")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AboutAdminControllerImpl implements AboutAdminController {
	private final AboutAdminFacade aboutAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<AboutPersistResponse> createAbout(
		@Valid @RequestBody AboutCreateRequest request
	) {
		AboutPersistResponse response = aboutAdminFacade.createAbout(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateAbout(
		@Positive @PathVariable Long id,
		@Valid @RequestBody AboutUpdateRequest request
	) {
		aboutAdminFacade.updateAbout(id, request);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
