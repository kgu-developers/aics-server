package kgu.developers.admin.lab.presentation;

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

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.lab.application.LabAdminFacade;
import kgu.developers.admin.lab.presentation.request.LabRequest;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/labs")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class LabAdminControllerImpl implements LabAdminController{
	private final LabAdminFacade labAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<LabPersistResponse> createLab(
		@Valid @RequestBody LabRequest request
	) {
		LabPersistResponse response = labAdminFacade.createLab(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateLab(
		@Parameter(description = "수정할 연구실 id", example = "1", required = true) @PathVariable @Positive Long id,
		@Valid @RequestBody LabRequest request
	) {
		labAdminFacade.updateLab(id, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLab(
		@Parameter(description = "삭제할 연구실의 id", example = "1", required = true) @PathVariable @Positive Long id
	) {
		labAdminFacade.deleteLab(id);
		return ResponseEntity.noContent().build();
	}
}
