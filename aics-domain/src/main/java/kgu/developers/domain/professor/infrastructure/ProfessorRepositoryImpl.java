package kgu.developers.domain.professor.infrastructure;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfessorRepositoryImpl implements ProfessorRepository {
	private final JpaProfessorRepository jpaProfessorRepository;

	@Override
	public Professor save(Professor professor) {
		return jpaProfessorRepository.save(professor);
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return jpaProfessorRepository.findById(id);
	}

	@Override
	public List<Professor> findAll() {
		return jpaProfessorRepository.findAllByOrderByNameAsc();
	}

	@Override
	public void delete(Professor professor) {
		jpaProfessorRepository.delete(professor);
	}

//	@Override
//	public List<Professor> findAllByOrder() {
//		return jpaProfessorRepository.findAllByOrderByOrder();
//	}
}
