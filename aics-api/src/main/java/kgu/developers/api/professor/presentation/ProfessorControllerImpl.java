package kgu.developers.api.professor.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.api.professor.application.ProfessorFacade;
import kgu.developers.api.professor.presentation.response.ProfessorListResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professors")
public class ProfessorControllerImpl implements ProfessorController{
	private final ProfessorFacade professorFacade;

	@Override
	@GetMapping
	public ResponseEntity<ProfessorListResponse> getSortedProfessorList() {
		ProfessorListResponse response = professorFacade.getSortedProfessorList();
		return ResponseEntity.ok().body(response);
	}
}
