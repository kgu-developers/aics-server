package kgu.developers.domain.professor.domain;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
	Professor save(Professor professor);

	Optional<Professor> findById(Long id);

	void deleteById(Long id);

	boolean existsById(Long id);

	List<Professor> findAll();
}
