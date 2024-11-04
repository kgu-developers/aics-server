package kgu.developers.domain.professor.infrastructure;

import kgu.developers.domain.professor.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProfessorRepository extends JpaRepository<Professor, Long> {

	List<Professor> findAllByOrderByNameAsc();

//	List<Professor> findAllByOrderByOrder();
}
