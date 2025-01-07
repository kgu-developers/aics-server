package kgu.developers.admin.professor.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.professor.presentation.request.ProfessorRequest;
import kgu.developers.admin.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.Professor;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class ProfessorAdminFacade {
	private final ProfessorCommandService professorCommandService;
	private final ProfessorQueryService professorQueryService;

	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		Long id = professorCommandService.createProfessor(request.name(), request.role(), request.contact(),
			request.email(), request.img(), request.officeLoc());
		return ProfessorPersistResponse.of(id);
	}

	public void updateProfessor(Long id, ProfessorRequest request) {
		Professor professor = professorQueryService.getProfessorById(id);
		professorCommandService.updateProfessor(professor, request.name(), request.role(), request.contact(),
			request.email(), request.img(), request.officeLoc());
	}

	public void deleteProfessor(Long id) {
		Professor professor = professorQueryService.getProfessorById(id);
		professorCommandService.deleteProfessor(professor);
	}
}
