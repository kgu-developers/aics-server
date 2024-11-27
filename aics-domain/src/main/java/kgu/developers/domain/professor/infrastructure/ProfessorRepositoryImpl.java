package kgu.developers.domain.professor.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProfessorRepositoryImpl implements ProfessorRepository {
	private final JpaProfessorRepository jpaProfessorRepository;
	private final QueryProfessorRepository queryProfessorRepository;

	@Override
	public Professor save(Professor professor) {
		return jpaProfessorRepository.save(professor);
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return jpaProfessorRepository.findById(id);
	}

	@Override
	public List<Professor> findAllOrderByRoleAndName() {
		return queryProfessorRepository.findAllOrderByRoleAndName();
	}

	@Override
	public void deleteById(Long id) {
		jpaProfessorRepository.deleteById(id);
	}
}
