package kgu.developers.api.professor.application;

import kgu.developers.api.professor.presentation.exception.ProfessorNotFoundException;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
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
//		일단 name으로 구현, Order로 조회시, 순서를 변경하는 엔드포인트 구성 필요
//		return professorRepository.findAllByOrder();

		return professorRepository.findAll();
	}

	private Professor getProfessor(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);
	}
}
