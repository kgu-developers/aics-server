package kgu.developers.api.professor.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		Professor professor = Professor.create(request.name(), request.role(), request.contact(), request.email());
		professorRepository.save(professor);

		return ProfessorPersistResponse.of(professor.getId());
	}

	@Transactional
	public void updateProfessor(Long id, ProfessorRequest request) {
		Professor professor = getProfessor(id);
		professor.updateName(request.name());
		professor.updateEmail(request.email());
		professor.updateContact(request.contact());
		professor.updateRole(request.role());
	}

	@Transactional
	public void deleteProfessor(Long id) {
		Professor professor = getProfessor(id);
		professorRepository.delete(professor);
	}

	@Transactional(readOnly = true)
	public List<Professor> getSortedProfessorList() {
		return professorRepository.findAllOrderByRoleAndName();
	}

	private Professor getProfessor(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);
	}
}
