package kgu.developers.api.lab.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.api.lab.application.LabFacade;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/labs")
public class LabControllerImpl implements LabController {
	private final LabFacade labFacade;

	@Override
	@GetMapping
	public ResponseEntity<LabListResponse> getLabs() {
		LabListResponse response = labFacade.getLabs();
		return ResponseEntity.ok(response);
	}
}
