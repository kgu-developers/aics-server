package kgu.developers.api.professor.application;

import jakarta.validation.constraints.Positive;
import kgu.developers.api.professor.presentation.exception.ProfessorNotFoundException;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.api.professor.presentation.response.ProfessorResponse;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository professorRepository;

	@Transactional
	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		Professor professor = Professor.create(
			request.name(), request.officeLoc(), request.contact(), request.email(), request.course()
		);
		Long id = professorRepository.save(professor).getId();
		return ProfessorPersistResponse.of(id);
	}

	@Transactional
	public void updateProfessor(@Positive Long id, ProfessorRequest request) {
		Professor professor = professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);

		professor.updateProfessor(
			request.name(), request.officeLoc(), request.contact(), request.email(), request.course()
		);
	}

	// TODO
	public void deleteProfessor(@Positive Long id) {

	}

	public List<ProfessorResponse> getProfessorList() {
		return null;
	}
}
