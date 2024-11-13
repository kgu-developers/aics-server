package kgu.developers.domain.professor.domain;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
	Professor save(Professor professor);

	Optional<Professor> findById(Long id);

	List<Professor> findAllOrderByRoleAndName();

	void delete(Professor professor);
}
