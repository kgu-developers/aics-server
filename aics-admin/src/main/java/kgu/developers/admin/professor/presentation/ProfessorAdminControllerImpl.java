package kgu.developers.admin.professor.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.professor.application.ProfessorAdminFacade;
import kgu.developers.admin.professor.presentation.request.ProfessorRequest;
import kgu.developers.admin.professor.presentation.response.ProfessorPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professors")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProfessorAdminControllerImpl implements ProfessorAdminController {
	private final ProfessorAdminFacade professorAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<ProfessorPersistResponse> createProfessor(
		@Valid @RequestBody ProfessorRequest request
	) {
		ProfessorPersistResponse response = professorAdminFacade.createProfessor(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateProfessor(
		@Positive @PathVariable Long id,
		@Valid @RequestBody ProfessorRequest request
	) {
		professorAdminFacade.updateProfessor(id, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProfessor(
		@Positive @PathVariable Long id
	) {
		professorAdminFacade.deleteProfessor(id);
		return ResponseEntity.noContent().build();
	}
}
