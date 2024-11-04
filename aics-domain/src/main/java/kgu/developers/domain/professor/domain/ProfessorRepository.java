package kgu.developers.domain.professor.domain;

import java.util.Optional;

public interface ProfessorRepository {
	Professor save(Professor professor);

	Optional<Professor> findById(Long id);
}
