package kgu.developers.domain.professor.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.professor.exception.ProfessorNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorQueryService {
	private final ProfessorRepository professorRepository;

	public List<Professor> getSortedProfessorList() {
		return professorRepository.findAllOrderByRoleAndName();
	}

	public Professor getProfessorById(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(ProfessorNotFoundException::new);
	}
}
