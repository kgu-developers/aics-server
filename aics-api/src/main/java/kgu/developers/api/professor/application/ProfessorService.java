package kgu.developers.api.professor.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.priority.application.PriorityService;
import kgu.developers.api.professor.presentation.exception.ProfessorNotFoundException;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	private final PriorityService<Professor> priorityService;

	@Transactional
	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		int adjustedPriority = priorityService.adjustToMaxPlusOne(Professor.class, request.priority());
		priorityService.updatePriority(Professor.class, adjustedPriority);

		Professor professor = Professor.create(
			request.name(), request.officeLoc(), request.contact(), request.email(), request.course(), adjustedPriority
		);
		professorRepository.save(professor);

		return ProfessorPersistResponse.of(professor.getId());
	}

	@Transactional
	public void updateProfessor(Long id, ProfessorRequest request) {
		Professor professor = getProfessor(id);
		professor.updateProfessor(
			request.name(), request.officeLoc(), request.contact(), request.email(), request.course()
		);
	}

	@Transactional
	public void deleteProfessor(Long id) {
		Professor professor = getProfessor(id);
		professorRepository.delete(professor);
	}

	@Transactional(readOnly = true)
	public List<Professor> getProfessorList() {
		return professorRepository.findAllByOrderByPriority();
	}

	private Professor getProfessor(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);
	}
}
