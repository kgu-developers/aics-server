package kgu.developers.api.professor.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.professor.presentation.exception.ProfessorNotFoundException;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository professorRepository;

	@Transactional
	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		Professor professor = Professor.create(request.name(), request.role(), request.contact(), request.email());
		Long id = professorRepository.save(professor).getId();

		return ProfessorPersistResponse.of(id);
	}

	@Transactional
	public void updateProfessor(Long id, ProfessorRequest request) {
		Professor professor = getProfessorById(id);
		professor.updateName(request.name());
		professor.updateEmail(request.email());
		professor.updateContact(request.contact());
		professor.updateRole(request.role());
	}

	@Transactional
	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Professor> getSortedProfessorList() {
		return professorRepository.findAllOrderByRoleAndName();
	}

	@Transactional(readOnly = true)
	public Professor getProfessorById(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);
	}
}
